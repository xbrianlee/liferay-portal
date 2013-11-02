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

<%@ page import="com.liferay.portlet.iframe.util.IFrameUtil" %>

<%
String src = portletPreferences.getValue("src", StringPool.BLANK);
boolean relative = GetterUtil.getBoolean(portletPreferences.getValue("relative", StringPool.BLANK));

boolean auth = GetterUtil.getBoolean(portletPreferences.getValue("auth", StringPool.BLANK));
String authType = portletPreferences.getValue("authType", StringPool.BLANK);
String formMethod = portletPreferences.getValue("formMethod", StringPool.BLANK);
String userNameField = portletPreferences.getValue("userNameField", StringPool.BLANK);
String passwordField = portletPreferences.getValue("passwordField", StringPool.BLANK);

String userName = null;
String password = null;

if (authType.equals("basic")) {
	userName = portletPreferences.getValue("basicUserName", StringPool.BLANK);
	password = portletPreferences.getValue("basicPassword", StringPool.BLANK);
}
else {
	userName = portletPreferences.getValue("formUserName", StringPool.BLANK);
	password = portletPreferences.getValue("formPassword", StringPool.BLANK);
}

String hiddenVariables = portletPreferences.getValue("hiddenVariables", StringPool.BLANK);
boolean resizeAutomatically = GetterUtil.getBoolean(portletPreferences.getValue("resizeAutomatically", StringPool.TRUE));
String heightMaximized = GetterUtil.getString(portletPreferences.getValue("heightMaximized", "600"));
String heightNormal = GetterUtil.getString(portletPreferences.getValue("heightNormal", "600"));
String width = GetterUtil.getString(portletPreferences.getValue("width", "100%"));

String alt = portletPreferences.getValue("alt", StringPool.BLANK);
String border = portletPreferences.getValue("border", "0");
String bordercolor = portletPreferences.getValue("bordercolor", "#000000");
String frameborder = portletPreferences.getValue("frameborder", "0");
String hspace = portletPreferences.getValue("hspace", "0");
String longdesc = portletPreferences.getValue("longdesc", StringPool.BLANK);
String scrolling = portletPreferences.getValue("scrolling", "auto");
String title = portletPreferences.getValue("title", StringPool.BLANK);
String vspace = portletPreferences.getValue("vspace", "0");

List<String> iframeVariables = new ArrayList<String>();

Enumeration<String> enu = request.getParameterNames();

while (enu.hasMoreElements()) {
	String name = enu.nextElement();

	if (name.startsWith(_IFRAME_PREFIX)) {
		iframeVariables.add(name.substring(_IFRAME_PREFIX.length()).concat(StringPool.EQUAL).concat(request.getParameter(name)));
	}
}
%>

<%@ include file="/html/portlet/iframe/init-ext.jsp" %>

<%!
private static final String _IFRAME_PREFIX = "iframe_";
%>