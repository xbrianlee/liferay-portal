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

<%@ include file="/html/portlet/journal/init.jsp" %>

<html>

<head>
	<liferay-util:include page="/html/common/themes/top_head.jsp" />
</head>

<body>

<%= request.getAttribute(WebKeys.JOURNAL_ARTICLE_CONTENT) %>

<liferay-util:include page="/html/common/themes/bottom.jsp" />

</body>

</html>