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
String facebookDisplayStyle = "button_count";

if (displayStyle.equals("simple")) {
	facebookDisplayStyle = "standard";
}
else if (displayStyle.equals("vertical")) {
	facebookDisplayStyle = "box_count";
}
%>

<liferay-util:html-bottom outputKey="taglib_ui_social_bookmark_facebook">
	<script src="<%= HttpUtil.getProtocol(request) %>://connect.facebook.net/<%= locale.getLanguage() %>_<%= locale.getCountry() %>/all.js#xfbml=1"></script>
</liferay-util:html-bottom>

<div id="fb-root"></div>

<div class="fb-like"
	data-font=""
	data-height="<%= (facebookDisplayStyle.equals("standard") || facebookDisplayStyle.equals("button_count")) ? 20 : StringPool.BLANK %>"
	data-href="<%= url %>"
	data-layout="<%= facebookDisplayStyle %>"
	data-send="false"
	data-show_faces="true"
>
</div>