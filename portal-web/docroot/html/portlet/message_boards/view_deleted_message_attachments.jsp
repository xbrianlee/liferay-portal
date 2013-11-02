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
String redirect = ParamUtil.getString(request, "redirect");

MBMessage message = (MBMessage)request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE);

long messageId = BeanParamUtil.getLong(message, request, "messageId");

long categoryId = MBUtil.getCategoryId(request, message);

MBUtil.addPortletBreadcrumbEntries(message, request, renderResponse);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/message_boards/edit_message");
portletURL.setParameter("messageId", String.valueOf(message.getMessageId()));

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), portletURL.toString());

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "removed-attachments"), currentURL);

PortletURL iteratorURL = renderResponse.createRenderURL();

iteratorURL.setParameter("struts_action", "/message_boards/view_deleted_message_attachments");
iteratorURL.setParameter("redirect", currentURL);
iteratorURL.setParameter("messageId", String.valueOf(messageId));
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="removed-attachments"
/>

<portlet:actionURL var="emptyTrashURL">
	<portlet:param name="struts_action" value="/message_boards/edit_message_attachments" />
	<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
</portlet:actionURL>

<liferay-ui:trash-empty
	confirmMessage="are-you-sure-you-want-to-remove-the-attachments-for-this-message"
	emptyMessage="remove-the-attachments-for-this-message"
	infoMessage="attachments-that-have-been-removed-for-more-than-x-will-be-automatically-deleted"
	portletURL="<%= emptyTrashURL.toString() %>"
	totalEntries="<%= message.getDeletedAttachmentsFileEntriesCount() %>"
/>

<liferay-ui:search-container
	emptyResultsMessage="this-message-does-not-have-file-attachments-in-the-recycle-bin"
	iteratorURL="<%= iteratorURL %>"
	total="<%= message.getDeletedAttachmentsFileEntriesCount() %>"
>

	<liferay-ui:search-container-results
		results="<%= message.getDeletedAttachmentsFileEntries(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.repository.model.FileEntry"
		escapedModel="<%= true %>"
		keyProperty="fileEntryId"
		modelVar="fileEntry"
	>

		<%
		String rowHREF = PortletFileRepositoryUtil.getPortletFileEntryURL(themeDisplay, fileEntry, "status=" + WorkflowConstants.STATUS_IN_TRASH);
		%>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="file-name"
		>
			<liferay-ui:icon
				image='<%= "../file_system/small/" + DLUtil.getFileIcon(fileEntry.getExtension()) %>'
				label="<%= true %>"
				message="<%= TrashUtil.getOriginalTitle(fileEntry.getTitle()) %>"
			/>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="size"
			value="<%= TextFormatter.formatStorageSize(fileEntry.getSize(), locale) %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/portlet/message_boards/deleted_message_attachment_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<liferay-ui:restore-entry
	duplicateEntryAction="/message_boards/restore_entry"
	overrideMessage="overwrite-the-existing-attachment-with-the-removed-one"
	renameMessage="keep-both-attachments-and-rename-the-removed-attachment-as"
	restoreEntryAction="/message_boards/restore_message_attachment"
/>