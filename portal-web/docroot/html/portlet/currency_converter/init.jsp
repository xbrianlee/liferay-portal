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

<%@ page import="com.liferay.portlet.currencyconverter.util.CurrencyUtil" %>

<%
String[] symbols = portletPreferences.getValues("symbols", new String[0]);

Map<String, String> allSymbols = CurrencyUtil.getAllSymbols(pageContext);
%>

<%@ include file="/html/portlet/currency_converter/init-ext.jsp" %>