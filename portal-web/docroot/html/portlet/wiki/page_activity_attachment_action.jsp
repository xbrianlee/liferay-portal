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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SocialActivity socialActivity = (SocialActivity)row.getObject();

JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(HtmlUtil.unescape(socialActivity.getExtraData()));

FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(extraDataJSONObject.getLong("fileEntryId"));

WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);
%>

<liferay-ui:icon-menu>
	<c:if test="<%= fileEntry.isInTrash() && (socialActivity.getType() == SocialActivityConstants.TYPE_MOVE_ATTACHMENT_TO_TRASH) && WikiNodePermission.contains(permissionChecker, wikiPage.getNodeId(), ActionKeys.ADD_ATTACHMENT) %>">

		<%
		TrashEntry trashEntry = TrashEntryLocalServiceUtil.getEntry(DLFileEntry.class.getName(), fileEntry.getFileEntryId());
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
			message="restore-attachment"
			onClick="<%= taglibOnClick %>"
			url="javascript:;"
		/>
	</c:if>

	<c:if test="<%= !fileEntry.isInTrash() && (socialActivity.getType() == SocialActivityConstants.TYPE_ADD_ATTACHMENT || socialActivity.getType() == SocialActivityConstants.TYPE_RESTORE_ATTACHMENT_FROM_TRASH) && WikiPagePermission.contains(permissionChecker, wikiPage.getNodeId(), wikiPage.getTitle(), ActionKeys.DELETE) %>">
		<portlet:actionURL var="deleteURL">
			<portlet:param name="struts_action" value="/wiki/edit_page_attachment" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= TrashUtil.isTrashEnabled(scopeGroupId) ? Constants.MOVE_TO_TRASH : Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="nodeId" value="<%= String.valueOf(wikiPage.getNodeId()) %>" />
			<portlet:param name="title" value="<%= wikiPage.getTitle() %>" />
			<portlet:param name="fileName" value="<%= fileEntry.getTitle() %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			message='<%= TrashUtil.isTrashEnabled(scopeGroupId) ? "remove-attachment" : "delete-attachment" %>'
			trash="<%= TrashUtil.isTrashEnabled(scopeGroupId) %>"
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>