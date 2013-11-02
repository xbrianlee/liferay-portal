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
JournalArticle article = (JournalArticle)request.getAttribute(WebKeys.JOURNAL_ARTICLE);

long groupId = BeanParamUtil.getLong(article, request, "groupId", scopeGroupId);
String articleId = ParamUtil.getString(request, "articleId");
String languageId = LanguageUtil.getLanguageId(request);
int articlePage = ParamUtil.getInteger(renderRequest, "page", 1);
String xmlRequest = PortletRequestUtil.toXML(renderRequest, renderResponse);

JournalArticleDisplay articleDisplay = JournalContentUtil.getDisplay(groupId, articleId, null, null, languageId, themeDisplay, articlePage, xmlRequest);

try {
	article = JournalArticleLocalServiceUtil.getLatestArticle(groupId, articleId, WorkflowConstants.STATUS_ANY);

	boolean expired = article.isExpired();

	if (!expired) {
		Date expirationDate = article.getExpirationDate();

		if ((expirationDate != null) && expirationDate.before(new Date())) {
			expired = true;
		}
	}
%>

	<c:choose>
		<c:when test="<%= (articleDisplay != null) && !expired %>">

			<div class="journal-content-article">
				<%= RuntimePageUtil.processXML(request, response, articleDisplay.getContent()) %>
			</div>

		</c:when>
		<c:otherwise>
			<div class="alert alert-error">
				<liferay-ui:message key="this-content-has-expired-or-you-do-not-have-the-required-permissions-to-access-it" />
			</div>
		</c:otherwise>
	</c:choose>

<%
} catch (NoSuchArticleException nsae) {
%>

	<div class="alert alert-error">
		<%= LanguageUtil.get(pageContext, "the-selected-web-content-no-longer-exists") %>
	</div>

<%
}
%>