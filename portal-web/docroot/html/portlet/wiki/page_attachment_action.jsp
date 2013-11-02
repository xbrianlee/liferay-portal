<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/html/portlet/wiki/init.jsp" %>

<%
boolean viewTrashAttachments = ParamUtil.getBoolean(request, "viewTrashAttachments");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

FileEntry attachmentsFileEntry = (FileEntry)row.getObject();

WikiPage wikiPage = WikiPageAttachmentsUtil.getPage(attachmentsFileEntry.getFileEntryId());
%>

<liferay-ui:icon-menu>
	<c:choose>
		<c:when test="<%= viewTrashAttachments %>">
			<c:if test="<%= WikiNodePermission.contains(permissionChecker, wikiPage.getNodeId(), ActionKeys.ADD_ATTACHMENT) %>">

				<%
				TrashEntry trashEntry = TrashEntryLocalServiceUtil.getEntry(DLFileEntry.class.getName(), attachmentsFileEntry.getFileEntryId());
				%>

				<portlet:actionURL var="restoreEntryURL">
					<portlet:param name="struts_action" value="/wiki/restore_page_attachment" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="trashEntryId" value="<%= String.valueOf(trashEntry.getEntryId()) %>" />
				</portlet:actionURL>

				<%
				String taglibOnClick = "Liferay.fire('" + renderResponse.getNamespace() + "checkEntry', {trashEntryId: " + trashEntry.getEntryId() + ", uri: '" + restoreEntryURL.toString() + "'});";
				%>

				<liferay-ui:icon
					image="undo"
					message="restore"
					onClick="<%= taglibOnClick %>"
					url="javascript:;"
				/>
			</c:if>

			<c:if test="<%= WikiPagePermission.contains(permissionChecker, wikiPage.getNodeId(), wikiPage.getTitle(), ActionKeys.DELETE) %>">
				<portlet:actionURL var="deleteURL">
					<portlet:param name="struts_action" value="/wiki/edit_page_attachment" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="nodeId" value="<%= String.valueOf(wikiPage.getNodeId()) %>" />
					<portlet:param name="title" value="<%= wikiPage.getTitle() %>" />
					<portlet:param name="fileName" value="<%= attachmentsFileEntry.getTitle() %>" />
				</portlet:actionURL>

				<liferay-ui:icon-delete
					url="<%= deleteURL %>"
				/>
			</c:if>
		</c:when>
		<c:otherwise>
			<c:if test="<%= WikiPagePermission.contains(permissionChecker, wikiPage.getNodeId(), wikiPage.getTitle(), ActionKeys.DELETE) %>">
				<portlet:actionURL var="deleteURL">
					<portlet:param name="struts_action" value="/wiki/edit_page_attachment" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= TrashUtil.isTrashEnabled(scopeGroupId) ? Constants.MOVE_TO_TRASH : Constants.DELETE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="nodeId" value="<%= String.valueOf(wikiPage.getNodeId()) %>" />
					<portlet:param name="title" value="<%= wikiPage.getTitle() %>" />
					<portlet:param name="fileName" value="<%= attachmentsFileEntry.getTitle() %>" />
				</portlet:actionURL>

				<liferay-ui:icon-delete
					message='<%= TrashUtil.isTrashEnabled(scopeGroupId) ? "remove" : "delete" %>'
					trash="<%= TrashUtil.isTrashEnabled(scopeGroupId) %>"
					url="<%= deleteURL %>"
				/>
			</c:if>
		</c:otherwise>
	</c:choose>
</liferay-ui:icon-menu>