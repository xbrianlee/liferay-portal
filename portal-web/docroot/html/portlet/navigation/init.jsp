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
String portletResource = ParamUtil.getString(request, "portletResource");

String bulletStyle = PrefsParamUtil.getString(portletPreferences, renderRequest, "bulletStyle", GetterUtil.getString(themeDisplay.getThemeSetting("bullet-style"), "dots"));
String displayStyle = PrefsParamUtil.getString(portletPreferences, renderRequest, "displayStyle", PropsValues.NAVIGATION_DISPLAY_STYLE_DEFAULT);
String headerType = PrefsParamUtil.getString(portletPreferences, renderRequest, "headerType", "root-layout");
String includedLayouts = PrefsParamUtil.getString(portletPreferences, renderRequest, "includedLayouts", "current");
boolean nestedChildren = PrefsParamUtil.getBoolean(portletPreferences, renderRequest, "nestedChildren", true);
boolean preview = PrefsParamUtil.getBoolean(portletPreferences, renderRequest, "preview");
int rootLayoutLevel = PrefsParamUtil.getInteger(portletPreferences, renderRequest, "rootLayoutLevel", 1);
String rootLayoutType = PrefsParamUtil.getString(portletPreferences, renderRequest, "rootLayoutType", "absolute");
%>

<%@ include file="/html/portlet/navigation/init-ext.jsp" %>