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

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portlet.journal.NoSuchArticleException" %><%@
page import="com.liferay.portlet.journal.service.permission.JournalArticlePermission" %><%@
page import="com.liferay.portlet.journal.service.permission.JournalPermission" %>

<%
String portletResource = ParamUtil.getString(request, "portletResource");

long articleGroupId = ParamUtil.getLong(renderRequest, "articleGroupId");

if (articleGroupId <= 0) {
	articleGroupId = GetterUtil.getLong(portletPreferences.getValue("groupId", String.valueOf(scopeGroupId)));
}

String articleId = ParamUtil.getString(renderRequest, "articleId");
String ddmTemplateKey = ParamUtil.getString(renderRequest, "ddmTemplateKey");

if (Validator.isNull(articleId)) {
	articleId = GetterUtil.getString(portletPreferences.getValue("articleId", StringPool.BLANK));
	ddmTemplateKey = GetterUtil.getString(portletPreferences.getValue("ddmTemplateKey", StringPool.BLANK));
}

boolean showAvailableLocales = GetterUtil.getBoolean(portletPreferences.getValue("showAvailableLocales", StringPool.BLANK));
String[] extensions = portletPreferences.getValues("extensions", null);
boolean enablePrint = GetterUtil.getBoolean(portletPreferences.getValue("enablePrint", null));
boolean enableRelatedAssets = GetterUtil.getBoolean(portletPreferences.getValue("enableRelatedAssets", null), true);
boolean enableRatings = GetterUtil.getBoolean(portletPreferences.getValue("enableRatings", null));
boolean enableComments = PropsValues.JOURNAL_ARTICLE_COMMENTS_ENABLED && GetterUtil.getBoolean(portletPreferences.getValue("enableComments", null));
boolean enableCommentRatings = GetterUtil.getBoolean(portletPreferences.getValue("enableCommentRatings", null));
boolean enableViewCountIncrement = GetterUtil.getBoolean(portletPreferences.getValue("enableViewCountIncrement", null), PropsValues.ASSET_ENTRY_BUFFERED_INCREMENT_ENABLED);

String[] conversions = DocumentConversionUtil.getConversions("html");

boolean openOfficeServerEnabled = PrefsPropsUtil.getBoolean(PropsKeys.OPENOFFICE_SERVER_ENABLED, PropsValues.OPENOFFICE_SERVER_ENABLED);
boolean enableConversions = openOfficeServerEnabled && (extensions != null) && (extensions.length > 0);
%>

<%@ include file="/html/portlet/journal_content/init-ext.jsp" %>