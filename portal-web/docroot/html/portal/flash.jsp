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
String title = ParamUtil.getString(request, "title");

String height = ParamUtil.getString(request, "height", "768");
String width = ParamUtil.getString(request, "width", "1024");

String movie = ParamUtil.getString(request, "movie");
%>

<html>

<head>
	<title><%= HtmlUtil.escape(title) %></title>
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />

	<script src="<%= themeDisplay.getCDNHost() + themeDisplay.getPathJavaScript() %>/misc/swfobject.js" type="text/javascript"></script>
</head>

<body leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">

<center>

<c:if test="<%= Validator.isNotNull(movie) %>">
	<div id="flashMovie"></div>

	<script type="text/javascript">
		var so = new SWFObject("<%= HtmlUtil.escapeJS(movie) %>", "flashMovie", "<%= HtmlUtil.escapeJS(width) %>", "<%= HtmlUtil.escapeJS(height) %>", "6", "#FFFFFF");

		so.write("flashMovie");
	</script>
</c:if>

</center>

</body>

</html>