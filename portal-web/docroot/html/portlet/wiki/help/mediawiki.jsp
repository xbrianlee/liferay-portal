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
''italics''
'''bold'''
</pre>

<h4>
	<liferay-ui:message key="headers" />
</h4>

<pre>
== Large heading ==
=== Medium heading ===
==== Small heading ====
</pre>

<h4>
	<liferay-ui:message key="links" />
</h4>

<pre>
[[Internal Link | Optional Label]]
[http://example.com External Link]
</pre>

<h4>
	<liferay-ui:message key="lists" />
</h4>

<pre>
* Item
** Subitem
# Ordered Item
## Ordered Subitem
</pre>

<h4>
	<liferay-ui:message key="images" />
</h4>

<pre>
[[Image:attached-image.png]]
[[Image:Page Name/image.jpg]]
</pre>

<h4>
	<liferay-ui:message key="other" />
</h4>

<pre>
Table of contents:
	Added automatically
Code block:
	Start each line with a space
Inline code:
	&lt;code&gt;Test.java&lt;/code&gt;
</pre>