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

<%@ include file="/html/portal/init.jsp" %>

<%
String redirect = themeDisplay.getPathMain() + "/portal/protected";

response.setHeader(HttpHeaders.CACHE_CONTROL, HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);
response.setHeader(HttpHeaders.LOCATION, redirect);
response.setHeader(HttpHeaders.PRAGMA, HttpHeaders.PRAGMA_NO_CACHE_VALUE);

response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
%>

<html dir="<liferay-ui:message key="lang.dir" />">

<head>
	<meta content="<%= ContentTypes.TEXT_HTML_UTF8 %>" http-equiv="content-type" />
	<meta content="no-cache" http-equiv="Cache-Control" />
	<meta content="no-cache" http-equiv="Pragma" />
	<meta content="0" http-equiv="Expires" />
	<meta content="1; url=<%= redirect %>" http-equiv="refresh" />
	<script src="<%= themeDisplay.getCDNHost() + themeDisplay.getPathJavaScript() %>/misc/xp_progress.js" type="text/javascript"></script>
</head>

<body onLoad="javascript:location.replace('<%= redirect %>')">

<center>

<table border="0" cellpadding="0" cellspacing="0" height="100%" width="600">
<tr>
	<td align="center" valign="middle">
		<strong><liferay-ui:message key="processing-login" /></strong>

		<br /><br />

		<script type="text/javascript">
			var progressBar = createBar(300, 15, "#FFFFFF", 1, "#000000", "", 85, 7, 3, "");
		</script>
	</td>
</tr>
</table>

</center>

</body>

</html>