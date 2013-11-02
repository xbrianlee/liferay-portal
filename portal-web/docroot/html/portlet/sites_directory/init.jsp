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

<%@ page import="com.liferay.taglib.ui.SitesDirectoryTag" %>

<%
String portletResource = ParamUtil.getString(request, "portletResource");

String displayStyle = PrefsParamUtil.getString(portletPreferences, renderRequest, "displayStyle", "descriptive");
String sites = PrefsParamUtil.getString(portletPreferences, renderRequest, "sites", SitesDirectoryTag.SITES_TOP_LEVEL);
%>

<%@ include file="/html/portlet/sites_directory/init-ext.jsp" %>