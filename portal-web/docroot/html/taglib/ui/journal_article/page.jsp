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

<%@ include file="/html/taglib/ui/journal_article/init.jsp" %>

<%
long articleResourcePrimKey = GetterUtil.getLong((String)request.getAttribute("liferay-ui:journal-article:articleResourcePrimKey"));
long groupId = GetterUtil.getLong((String)request.getAttribute("liferay-ui:journal-article:groupId"));
String articleId = GetterUtil.getString((String)request.getAttribute("liferay-ui:journal-article:articleId"));
String templateId = GetterUtil.getString((String)request.getAttribute("liferay-ui:journal-article:templateId"));
String languageId = GetterUtil.getString((String)request.getAttribute("liferay-ui:journal-article:languageId"), LanguageUtil.getLanguageId(request));
int articlePage = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:journal-article:articlePage"));

String xmlRequest = GetterUtil.getString((String)request.getAttribute("liferay-ui:journal-article:xmlRequest"));

if (Validator.isNull(xmlRequest) && (portletRequest != null) && (portletResponse != null)) {
	xmlRequest = PortletRequestUtil.toXML(portletRequest, portletResponse);
}

boolean showTitle = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:journal-article:showTitle"));
boolean showAvailableLocales = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:journal-article:showAvailableLocales"));

if (articleResourcePrimKey > 0) {
	JournalArticleResource articleResource = JournalArticleResourceLocalServiceUtil.getArticleResource(articleResourcePrimKey);

	groupId = articleResource.getGroupId();
	articleId = articleResource.getArticleId();
}

JournalArticleDisplay articleDisplay = JournalContentUtil.getDisplay(groupId, articleId, templateId, null, languageId, themeDisplay, articlePage, xmlRequest);
%>

<c:if test="<%= articleDisplay != null %>">
	<c:if test="<%= showTitle %>">
		<h3 class="journal-content-title"><%= articleDisplay.getTitle() %></h3>
	</c:if>

	<c:if test="<%= showAvailableLocales %>">

		<%
		String[] availableLocales = articleDisplay.getAvailableLocales();
		%>

		<c:if test="<%= availableLocales.length > 1 %>">
			<div>
				<br />

				<liferay-ui:language displayStyle="<%= 0 %>" languageIds="<%= availableLocales %>" />
			</div>
		</c:if>
	</c:if>

	<%= RuntimePageUtil.processXML(request, response, articleDisplay.getContent()) %>
</c:if>