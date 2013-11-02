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
JournalArticle article = (JournalArticle)request.getAttribute("view_entries.jsp-article");

PortletURL tempRowURL = (PortletURL)request.getAttribute("view_entries.jsp-tempRowURL");

JournalArticle latestApprovedArticleVersion = null;

Date createDate = article.getCreateDate();

if (article.getVersion() > JournalArticleConstants.VERSION_DEFAULT) {
	JournalArticle firstArticleVersion = JournalArticleLocalServiceUtil.getArticle(article.getGroupId(), article.getArticleId(), JournalArticleConstants.VERSION_DEFAULT);

	createDate = firstArticleVersion.getCreateDate();

	if (article.getStatus() > WorkflowConstants.STATUS_APPROVED) {
		latestApprovedArticleVersion = JournalArticleLocalServiceUtil.fetchLatestArticle(article.getGroupId(), article.getArticleId(), WorkflowConstants.STATUS_APPROVED);
	}
}

String articleImageURL = article.getArticleImageURL(themeDisplay);
%>

<liferay-ui:app-view-entry
	actionJsp="/html/portlet/journal/article_action.jsp"
	assetCategoryClassName="<%= JournalArticle.class.getName() %>"
	assetCategoryClassPK="<%= article.getResourcePrimKey() %>"
	assetTagClassName="<%= JournalArticle.class.getName() %>"
	assetTagClassPK="<%= article.getResourcePrimKey() %>"
	author="<%= article.getUserName() %>"
	createDate="<%= createDate %>"
	description="<%= article.getDescription(locale) %>"
	displayDate="<%= article.getDisplayDate() %>"
	displayStyle="descriptive"
	expirationDate="<%= article.getExpirationDate() %>"
	groupId="<%= article.getGroupId() %>"
	latestApprovedVersion="<%= (latestApprovedArticleVersion != null) ? String.valueOf(latestApprovedArticleVersion.getVersion()) : null %>"
	latestApprovedVersionAuthor="<%= (latestApprovedArticleVersion != null) ? String.valueOf(latestApprovedArticleVersion.getUserName()) : null %>"
	modifiedDate="<%= article.getModifiedDate() %>"
	reviewDate="<%= article.getReviewDate() %>"
	rowCheckerId="<%= String.valueOf(article.getArticleId()) %>"
	rowCheckerName="<%= JournalArticle.class.getSimpleName() %>"
	showCheckbox="<%= JournalArticlePermission.contains(permissionChecker, article, ActionKeys.DELETE) || JournalArticlePermission.contains(permissionChecker, article, ActionKeys.EXPIRE) || JournalArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE) %>"
	status="<%= article.getStatus() %>"
	thumbnailDivStyle="height: 136px; width: 136px;"
	thumbnailSrc='<%= Validator.isNotNull(articleImageURL) ? articleImageURL : themeDisplay.getPathThemeImages() + "/file_system/large/article.png" %>'
	thumbnailStyle="max-height: 128px; max-width: 128px;"
	title="<%= article.getTitle(locale) %>"
	url="<%= tempRowURL.toString() %>"
	version="<%= String.valueOf(article.getVersion()) %>"
/>