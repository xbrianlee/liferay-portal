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

<%@ include file="/html/portlet/wiki/init.jsp" %>

<h4>
	<liferay-ui:message key="text-styles" />
</h4>

<pre>
'quoted'
''italics''
'''bold'''
monospaced
</pre>

<h4>
	<liferay-ui:message key="headers" />
</h4>

<pre>
= Header 1 =
== Header 2 ==
=== Header 3 ===
</pre>

<h4>
	<liferay-ui:message key="links" />
</h4>

<pre>
CamelCaseWordsAreLinksToPages
[http://www.liferay.com Liferay's Website]
</pre>

<h4>
	<liferay-ui:message key="lists" />
</h4>

<pre>
<img alt="<liferay-ui:message key="tab" />"src="<%= themeDisplay.getPathThemeImages() %>/wiki/tab.png" />* Item
<img alt="<liferay-ui:message key="tab" />" src="<%= themeDisplay.getPathThemeImages() %>/wiki/tab.png" />&nbsp;<img alt="<liferay-ui:message key="tab" />" src="<%= themeDisplay.getPathThemeImages() %>/wiki/tab.png" />* Subitem

<img alt="<liferay-ui:message key="tab" />" src="<%= themeDisplay.getPathThemeImages() %>/wiki/tab.png" />1 Ordered Item
<img alt="<liferay-ui:message key="tab" />" src="<%= themeDisplay.getPathThemeImages() %>/wiki/tab.png" />&nbsp;<img alt="<liferay-ui:message key="tab" />" src="<%= themeDisplay.getPathThemeImages() %>/wiki/tab.png" />1 Ordered Subitem
</pre>