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
String cmd = ParamUtil.getString(request, Constants.CMD);

JournalArticle article = (JournalArticle)request.getAttribute(WebKeys.JOURNAL_ARTICLE);

String toLanguageId = ParamUtil.getString(request, "toLanguageId");

String toLanguageDisplayName = StringPool.BLANK;

if (cmd.equals(Constants.TRANSLATE)) {
	Locale toLocale = LocaleUtil.fromLanguageId(toLanguageId);

	toLanguageDisplayName = toLocale.getDisplayName(locale);
}
%>

<aui:script use="aui-base">
	var openingWindow = Liferay.Util.getOpener();

	openingWindow.<portlet:namespace />postProcessTranslation('<%= System.currentTimeMillis() %>', '<%= HtmlUtil.escapeJS(cmd) %>', '<%= article.getVersion() %>', '<%= HtmlUtil.escapeJS(toLanguageId) %>', '<%= toLanguageDisplayName %>', '<%= WorkflowConstants.getStatusLabel(article.getStatus()) %>');

	Liferay.fire(
		'closeWindow',
		{
			id: '<%= HtmlUtil.escapeJS(renderResponse.getNamespace() + toLanguageId) %>'
		}
	);
</aui:script>