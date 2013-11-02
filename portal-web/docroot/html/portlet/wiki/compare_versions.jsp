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

<%
String backURL = ParamUtil.getString(request, "backURL");

String type = ParamUtil.getString(request, "type");
%>

<liferay-util:include page="/html/portlet/wiki/top_links.jsp" />

<liferay-util:include page="/html/portlet/wiki/page_tabs.jsp">
	<liferay-util:param name="tabs1" value="history" />
</liferay-util:include>

<liferay-util:include page="/html/portlet/wiki/page_tabs_history.jsp" />

<liferay-ui:header
	backURL="<%= backURL %>"
	title="compare-versions"
/>

<liferay-util:include page="/html/portlet/wiki/history_navigation.jsp">
	<liferay-util:param name="mode" value="<%= type %>" />
</liferay-util:include>

<c:choose>
	<c:when test='<%= type.equals("html") %>'>

		<%
		String diffHtmlResults = (String)request.getAttribute(WebKeys.DIFF_HTML_RESULTS);
		%>

		<liferay-ui:diff-html diffHtmlResults="<%= diffHtmlResults %>" />
	</c:when>
	<c:otherwise>

		<%
		String title = (String)request.getAttribute(WebKeys.TITLE);
		double sourceVersion = (Double)request.getAttribute(WebKeys.SOURCE_VERSION);
		double targetVersion = (Double)request.getAttribute(WebKeys.TARGET_VERSION);
		List[] diffResults = (List[])request.getAttribute(WebKeys.DIFF_RESULTS);
		%>

		<liferay-ui:diff
			diffResults="<%= diffResults %>"
			sourceName="<%= title + StringPool.SPACE + sourceVersion %>"
			targetName="<%= title + StringPool.SPACE + targetVersion %>"
		/>
	</c:otherwise>
</c:choose>