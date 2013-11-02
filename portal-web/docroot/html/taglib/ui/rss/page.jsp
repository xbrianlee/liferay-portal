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
String message = (String)request.getAttribute("liferay-ui:rss:message");
String url = (String)request.getAttribute("liferay-ui:rss:url");
%>

<liferay-ui:icon
	cssClass="taglib-rss"
	image="rss"
	label="<%= true %>"
	message="<%= message %>"
	target="_blank"
	url="<%= url %>"
/>

<liferay-util:html-top>
	<link href="<%= HtmlUtil.escape(url) %>" rel="alternate" title="RSS" type="application/rss+xml" />
</liferay-util:html-top>