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

<%@ page import="com.liferay.portlet.wiki.NoSuchNodeException" %><%@
page import="com.liferay.portlet.wiki.model.WikiNode" %><%@
page import="com.liferay.portlet.wiki.model.WikiPage" %><%@
page import="com.liferay.portlet.wiki.model.WikiPageConstants" %><%@
page import="com.liferay.portlet.wiki.service.WikiNodeServiceUtil" %><%@
page import="com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil" %>

<%
long nodeId = GetterUtil.getLong(portletPreferences.getValue("nodeId", StringPool.BLANK));
String title = GetterUtil.getString(portletPreferences.getValue("title", WikiPageConstants.FRONT_PAGE));
%>

<%@ include file="/html/portlet/wiki_display/init-ext.jsp" %>