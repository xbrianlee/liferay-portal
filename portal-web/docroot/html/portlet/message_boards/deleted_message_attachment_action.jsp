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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

FileEntry fileEntry = (FileEntry)row.getObject();

MBMessage message = MBMessageAttachmentsUtil.getMessage(fileEntry.getFileEntryId());
%>

<liferay-ui:icon-menu>
	<c:if test="<%= MBCategoryPermission.contains(permissionChecker, scopeGroupId, message.getCategoryId(), ActionKeys.ADD_FILE) %>">

		<%
		TrashEntry trashEntry = TrashEntryLocalServiceUtil.getEntry(DLFileEntry.class.getName(), fileEntry.getFileEntryId());
		%>

		<portlet:actionURL var="restoreEntryURL">
			<portlet:param name="struts_action" value="/message_boards/restore_message_attachment" />
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

		<portlet:actionURL var="deleteURL">
			<portlet:param name="struts_action" value="/message_boards/edit_message_attachments" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
			<portlet:param name="fileName" value="<%= fileEntry.getTitle() %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>