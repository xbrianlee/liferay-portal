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

boolean neverExpire = ParamUtil.getBoolean(request, "neverExpire", true);

if (article != null) {
	if ((article.getExpirationDate() != null) && !article.isExpired()) {
		neverExpire = false;
	}
}

boolean neverReview = ParamUtil.getBoolean(request, "neverReview", true);

if (article != null) {
	if (article.getReviewDate() != null) {
		neverReview = false;
	}
}
%>

<liferay-ui:error-marker key="errorSection" value="schedule" />

<aui:model-context bean="<%= article %>" model="<%= JournalArticle.class %>" />

<h3><liferay-ui:message key="schedule" /></h3>

<liferay-ui:error exception="<%= ArticleDisplayDateException.class %>" message="please-enter-a-valid-display-date" />
<liferay-ui:error exception="<%= ArticleExpirationDateException.class %>" message="please-enter-a-valid-expiration-date" />

<aui:fieldset>
	<aui:input formName="fm1" name="displayDate" />

	<aui:input dateTogglerCheckboxLabel="never-expire" disabled="<%= neverExpire %>" formName="fm1" name="expirationDate" />

	<aui:input dateTogglerCheckboxLabel="never-review" disabled="<%= neverReview %>" formName="fm1" name="reviewDate" />
</aui:fieldset>