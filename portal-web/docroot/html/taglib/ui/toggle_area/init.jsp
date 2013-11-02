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
String id = (String)request.getAttribute("liferay-ui:toggle-area:id");
String showImage = (String)request.getAttribute("liferay-ui:toggle-area:showImage");
String hideImage = (String)request.getAttribute("liferay-ui:toggle-area:hideImage");
String showMessage = (String)request.getAttribute("liferay-ui:toggle-area:showMessage");
String hideMessage = (String)request.getAttribute("liferay-ui:toggle-area:hideMessage");
boolean defaultShowContent = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:toggle-area:defaultShowContent"));
String stateVar = (String)request.getAttribute("liferay-ui:toggle-area:stateVar");
String align = (String)request.getAttribute("liferay-ui:toggle-area:align");
%>