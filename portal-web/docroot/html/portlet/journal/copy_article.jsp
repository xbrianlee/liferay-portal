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

long groupId = ParamUtil.getLong(request, "groupId");
String oldArticleId = ParamUtil.getString(request, "oldArticleId");
String newArticleId = ParamUtil.getString(request, "newArticleId");
double version = ParamUtil.getDouble(request, "version");
%>

<portlet:actionURL var="copyArticleURL">
	<portlet:param name="struts_action" value="/journal/copy_article" />
</portlet:actionURL>

<aui:form action="<%= copyArticleURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.COPY %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
	<aui:input name="oldArticleId" type="hidden" value="<%= oldArticleId %>" />
	<aui:input name="version" type="hidden" value="<%= version %>" />

	<liferay-ui:header
		backURL="<%= redirect %>"
		title="web-content"
	/>

	<liferay-ui:error exception="<%= ArticleIdException.class %>" message="please-enter-a-valid-id" />
	<liferay-ui:error exception="<%= DuplicateArticleIdException.class %>" message="please-enter-a-unique-id" />

	<aui:fieldset>
		<aui:field-wrapper label="id">
			<liferay-ui:input-resource url="<%= oldArticleId %>" />
		</aui:field-wrapper>

		<aui:field-wrapper label="new-id">
			<c:choose>
				<c:when test="<%= PropsValues.JOURNAL_ARTICLE_FORCE_AUTOGENERATE_ID %>">
					<liferay-ui:input-resource url='<%= LanguageUtil.get(pageContext, "autogenerate-id") %>' />

					<aui:input name="newArticleId" type="hidden" />
					<aui:input name="autoArticleId" type="hidden" value="<%= true %>" />
				</c:when>
				<c:otherwise>
					<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" bean="<%= null %>" cssClass="lfr-input-text-container" field="articleId" fieldParam="newArticleId" label="" model="<%= JournalArticle.class %>" name="newArticleId" value="<%= newArticleId %>" />
				</c:otherwise>
			</c:choose>
		</aui:field-wrapper>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="copy" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>