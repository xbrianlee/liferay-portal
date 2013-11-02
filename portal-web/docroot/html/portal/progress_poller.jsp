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
String sessionKey = ParamUtil.getString(request, "sessionKey");
String progressId = ParamUtil.getString(request, "progressId");

ProgressTracker progressTracker = (ProgressTracker)session.getAttribute(sessionKey);

if (progressTracker == null) {
	progressTracker = (ProgressTracker)session.getAttribute(sessionKey + progressId);
}

String message = StringPool.BLANK;
Integer percent = 0;

if (progressTracker != null) {
	message = progressTracker.getMessage();
	percent = progressTracker.getPercent();
}
%>

<html>

<body>

<script type="text/javascript">
	;(function() {
		var progressId = parent['<%= HtmlUtil.escapeJS(progressId) %>'];

		if (progressId && (typeof progressId.set == 'function')) {
			progressId.set('message', '<%= LanguageUtil.get(pageContext, message) %>');
			progressId.set('value', <%= percent %>);
		}
	}());
</script>

</body>

</html>