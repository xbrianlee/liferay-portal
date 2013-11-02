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

<%
String initUrl = portletPreferences.getValue("initUrl", StringPool.BLANK);
String scope = portletPreferences.getValue("scope", StringPool.BLANK);
String proxyHost = portletPreferences.getValue("proxyHost", StringPool.BLANK);
String proxyPort = portletPreferences.getValue("proxyPort", StringPool.BLANK);
String proxyAuthentication = portletPreferences.getValue("proxyAuthentication", StringPool.BLANK);
String proxyAuthenticationUsername = portletPreferences.getValue("proxyAuthenticationUsername", StringPool.BLANK);
String proxyAuthenticationPassword = portletPreferences.getValue("proxyAuthenticationPassword", StringPool.BLANK);
String proxyAuthenticationHost = portletPreferences.getValue("proxyAuthenticationHost", StringPool.BLANK);
String proxyAuthenticationDomain = portletPreferences.getValue("proxyAuthenticationDomain", StringPool.BLANK);
String stylesheet = portletPreferences.getValue("stylesheet", StringPool.BLANK);
%>

<%@ include file="/html/portlet/web_proxy/init-ext.jsp" %>