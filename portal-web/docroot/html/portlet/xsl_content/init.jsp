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

<%@ page import="com.liferay.portlet.asset.NoSuchTagException" %><%@
page import="com.liferay.portlet.asset.NoSuchTagPropertyException" %><%@
page import="com.liferay.portlet.asset.model.AssetTagProperty" %><%@
page import="com.liferay.portlet.asset.service.AssetTagPropertyLocalServiceUtil" %><%@
page import="com.liferay.portlet.xslcontent.util.XSLContentUtil" %>

<%@ page import="java.net.URL" %>

<%
String xmlUrl = portletPreferences.getValue("xmlUrl", XSLContentUtil.DEFAULT_XML_URL);
String xslUrl = portletPreferences.getValue("xslUrl", XSLContentUtil.DEFAULT_XSL_URL);
%>

<%@ include file="/html/portlet/xsl_content/init-ext.jsp" %>