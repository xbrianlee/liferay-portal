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

<%@ include file="/html/taglib/ui/social_bookmark/init.jsp" %>

<%
String twitterDisplayStyle = "none";

if (displayStyle.equals("horizontal") || displayStyle.equals("vertical")) {
	twitterDisplayStyle = displayStyle;
}
%>

<a class="twitter-share-button" data-count="<%= twitterDisplayStyle %>" data-lang="<%= locale.getDisplayLanguage() %>" data-text="<%= HtmlUtil.escapeAttribute(title) %>" data-url="<%= url %>" href="http://twitter.com/share"><liferay-ui:message key="tweet" /></a>

<liferay-util:html-bottom outputKey="twitter">
	<script src="<%= HttpUtil.getProtocol(request) %>://platform.twitter.com/widgets.js" type="text/javascript"></script>
</liferay-util:html-bottom>