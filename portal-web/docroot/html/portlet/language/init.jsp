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

<%@ page import="com.liferay.taglib.ui.LanguageTag" %>

<%
Locale[] availableLocales = LanguageUtil.getAvailableLocales(themeDisplay.getSiteGroupId());

String[] availableLanguageIds = LocaleUtil.toLanguageIds(availableLocales);

String[] languageIds = StringUtil.split(portletPreferences.getValue("languageIds", StringUtil.merge(availableLanguageIds)));
boolean displayCurrentLocale = GetterUtil.getBoolean(portletPreferences.getValue("displayCurrentLocale", null), true);
int displayStyle = GetterUtil.getInteger(portletPreferences.getValue("displayStyle", StringPool.BLANK));
%>

<%@ include file="/html/portlet/language/init-ext.jsp" %>