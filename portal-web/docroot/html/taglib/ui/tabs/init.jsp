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

// Names

String[] names = (String[])request.getAttribute("liferay-ui:tabs:names");
String namesJS = JS.toScript(names);

// Values

String[] values = (String[])request.getAttribute("liferay-ui:tabs:values");

if ((values == null) || (values.length < names.length)) {
	values = names;
}

// Form name

String formName = (String)request.getAttribute("liferay-ui:tabs:formName");

// Param

String param = (String)request.getAttribute("liferay-ui:tabs:param");

// Value

String value = (String)request.getAttribute("liferay-ui:tabs:value");

if (value == null) {
	value = ParamUtil.getString(request, param, values[0]);
}
%>