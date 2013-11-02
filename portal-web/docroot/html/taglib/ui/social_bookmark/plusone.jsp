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
String plusOneDisplayStyle = "medium";

if (displayStyle.equals("vertical")) {
	plusOneDisplayStyle = "tall";
}
%>

<liferay-util:html-bottom outputKey="taglib_ui_social_bookmark_plusone">
	<script type="text/javascript">
		window.___gcfg = {
			lang: '<%= locale.getLanguage() %>-<%= locale.getCountry() %>'
		};

		(function() {
			var script = document.createElement('script');

			script.async = true;
			script.type = 'text/javascript';

			script.src = 'https://apis.google.com/js/plusone.js';

			var firstScript = document.getElementsByTagName('script')[0];

			firstScript.parentNode.insertBefore(script, firstScript);
		})();
	</script>
</liferay-util:html-bottom>

<g:plusone
	count="<%= !displayStyle.equals("simple") %>"
	href="<%= url %>"
	size="<%= plusOneDisplayStyle %>"
>
</g:plusone>