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

<%@ include file="/html/portlet/iframe/init.jsp" %>

<%
List<String> hiddenVariablesList = ListUtil.toList(StringUtil.split(hiddenVariables, CharPool.SEMICOLON));

hiddenVariablesList.addAll(iframeVariables);
%>

<html dir="<liferay-ui:message key="lang.dir" />">

<head>
	<meta content="no-cache" http-equiv="Cache-Control" />
	<meta content="no-cache" http-equiv="Pragma" />
	<meta content="0" http-equiv="Expires" />
</head>

<body onLoad="setTimeout('document.fm.submit()', 100);">

<form action="<%= HtmlUtil.escapeAttribute(src) %>" method="<%= HtmlUtil.escapeAttribute(formMethod) %>" name="fm">

<%
for (String hiddenVariable : hiddenVariablesList) {
	String hiddenKey = StringPool.BLANK;
	String hiddenValue = StringPool.BLANK;

	int pos = hiddenVariable.indexOf(StringPool.EQUAL);

	if (pos != -1) {
		hiddenKey = hiddenVariable.substring(0, pos);
		hiddenValue = hiddenVariable.substring(pos + 1);
	}
%>

	<input name="<%= HtmlUtil.escapeAttribute(hiddenKey) %>" type="hidden" value="<%= HtmlUtil.escapeAttribute(hiddenValue) %>" />

<%
}

if (Validator.isNull(userNameField)) {
	int pos = userName.indexOf(StringPool.EQUAL);

	if (pos != -1) {
		String fieldValuePair = userName;

		userNameField = fieldValuePair.substring(0, pos);
		userName = fieldValuePair.substring(pos + 1);

		portletPreferences.setValue("userName", userName);
		portletPreferences.setValue("userNameField", userNameField);

		portletPreferences.store();
	}
}

if (Validator.isNotNull(userNameField)) {
	userName = IFrameUtil.getUserName(renderRequest, userName);
}
%>

<input name="<%= HtmlUtil.escapeAttribute(userNameField) %>" type="hidden" value="<%= HtmlUtil.escapeAttribute(userName) %>" />

<%
if (Validator.isNull(passwordField)) {
	int pos = password.indexOf(StringPool.EQUAL);

	if (pos != -1) {
		String fieldValuePair = password;

		passwordField = fieldValuePair.substring(0, pos);
		password = fieldValuePair.substring(pos + 1);

		portletPreferences.setValue("password", password);
		portletPreferences.setValue("passwordField", passwordField);

		portletPreferences.store();
	}
}

if (Validator.isNotNull(passwordField)) {
	password = IFrameUtil.getPassword(renderRequest, password);
}
%>

<input name="<%= HtmlUtil.escapeAttribute(passwordField) %>" type="hidden" value="<%= HtmlUtil.escapeAttribute(password) %>" />

</form>

</body>

</html>