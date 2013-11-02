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

<%@ include file="/html/taglib/init.jsp" %>

<%
String backLabel = (String)request.getAttribute("liferay-ui:header:backLabel");
String backURL = (String)request.getAttribute("liferay-ui:header:backURL");
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:header:cssClass"));
boolean escapeXml = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:header:escapeXml"));
boolean localizeTitle = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:header:localizeTitle"));
boolean showBackURL = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:header:showBackURL"));
String title = (String)request.getAttribute("liferay-ui:header:title");
%>