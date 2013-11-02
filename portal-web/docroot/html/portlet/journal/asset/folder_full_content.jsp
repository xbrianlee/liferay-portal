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

<%@ include file="/html/portlet/journal/init.jsp" %>

<%
JournalFolder folder = (JournalFolder)request.getAttribute(WebKeys.JOURNAL_FOLDER);
%>

<c:if test="<%= folder != null %>">

	<%
	int status = WorkflowConstants.STATUS_APPROVED;

	if (permissionChecker.isCompanyAdmin() || permissionChecker.isGroupAdmin(scopeGroupId)) {
		status = WorkflowConstants.STATUS_ANY;
	}

	int foldersCount = JournalFolderServiceUtil.getFoldersCount(scopeGroupId, folder.getFolderId(), status);
	int entriesCount = JournalArticleServiceUtil.getArticlesCount(scopeGroupId, folder.getFolderId(), status);
	%>

	<aui:row>
		<aui:col cssClass="lfr-asset-column lfr-asset-column-details" width="100">
			<c:if test="<%= Validator.isNotNull(folder.getDescription()) %>">
				<div class="lfr-asset-description">
					<%= HtmlUtil.escape(folder.getDescription()) %>
				</div>
			</c:if>

			<div class="lfr-asset-metadata">
				<div class="lfr-asset-icon lfr-asset-date">
					<%= LanguageUtil.format(pageContext, "last-updated-x", dateFormatDateTime.format(folder.getModifiedDate())) %>
				</div>

				<div class="lfr-asset-icon lfr-asset-subfolders">
					<%= foldersCount %> <liferay-ui:message key='<%= (foldersCount == 1) ? "subfolder" : "subfolders" %>' />
				</div>

				<div class="lfr-asset-icon lfr-asset-items last">
					<%= entriesCount %> <liferay-ui:message key='<%= (entriesCount == 1) ? "article" : "articles" %>' />
				</div>
			</div>

			<liferay-ui:custom-attributes-available className="<%= JournalFolder.class.getName() %>">
				<liferay-ui:custom-attribute-list
					className="<%= JournalFolder.class.getName() %>"
					classPK="<%= (folder != null) ? folder.getFolderId() : 0 %>"
					editable="<%= false %>"
					label="<%= true %>"
				/>
			</liferay-ui:custom-attributes-available>
		</aui:col>
	</aui:row>
</c:if>