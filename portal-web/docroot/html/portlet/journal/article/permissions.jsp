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
%>

<liferay-ui:error-marker key="errorSection" value="permissions" />

<aui:model-context bean="<%= article %>" model="<%= JournalArticle.class %>" />

<h3><liferay-ui:message key="permissions" /></h3>

<c:if test="<%= (article == null) || article.isNew() %>">
	<aui:field-wrapper cssClass="journal-article-permissions" label="permissions">
		<liferay-ui:input-permissions
			modelName="<%= JournalArticle.class.getName() %>"
		/>
	</aui:field-wrapper>
</c:if>