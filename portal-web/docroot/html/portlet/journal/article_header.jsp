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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL");

JournalArticle article = (JournalArticle)request.getAttribute(WebKeys.JOURNAL_ARTICLE);

long classNameId = BeanParamUtil.getLong(article, request, "classNameId");

String toLanguageId = ParamUtil.getString(request, "toLanguageId");

if ((article != null) && Validator.isNotNull(toLanguageId)) {
	redirect = null;
}

boolean localizeTitle = true;
String title = "new-web-content";

if (classNameId > JournalArticleConstants.CLASSNAME_ID_DEFAULT) {
	title = "structure-default-values";
}
else if ((article != null) && !article.isNew()) {
	localizeTitle = false;

	if (Validator.isNotNull(toLanguageId)) {
		title = article.getTitle(toLanguageId);
	}
	else {
		title = article.getTitle(locale);
	}
}
%>

<liferay-ui:header
	backURL="<%= article == null ? redirect : backURL %>"
	localizeTitle="<%= localizeTitle %>"
	title="<%= title %>"
/>