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

boolean showCurrentGroup = PrefsParamUtil.getBoolean(portletPreferences, renderRequest, "showCurrentGroup", true);
boolean showCurrentPortlet = PrefsParamUtil.getBoolean(portletPreferences, renderRequest, "showCurrentPortlet", true);
boolean showGuestGroup = PrefsParamUtil.getBoolean(portletPreferences, renderRequest, "showGuestGroup", PropsValues.BREADCRUMB_SHOW_GUEST_GROUP);
boolean showLayout = PrefsParamUtil.getBoolean(portletPreferences, renderRequest, "showLayout", true);
boolean showParentGroups = PrefsParamUtil.getBoolean(portletPreferences, renderRequest, "showParentGroups", PropsValues.BREADCRUMB_SHOW_PARENT_GROUPS);
boolean showPortletBreadcrumb = PrefsParamUtil.getBoolean(portletPreferences, renderRequest, "showPortletBreadcrumb", true);
%>

<%@ include file="/html/portlet/breadcrumb/init-ext.jsp" %>