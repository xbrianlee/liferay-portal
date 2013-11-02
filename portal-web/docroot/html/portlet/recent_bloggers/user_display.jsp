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

<%@ include file="/html/portlet/recent_bloggers/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objArray = (Object[])row.getObject();

BlogsStatsUser statsUser = (BlogsStatsUser)objArray[0];
String rowHREF = (String)objArray[1];
%>

<liferay-ui:user-display url="<%= rowHREF %>" userId="<%= statsUser.getUserId() %>">
	<div class="blogger-post-count">
		<span><liferay-ui:message key="posts" />:</span> <%= statsUser.getEntryCount() %>
	</div>

	<div class="blogger-stars">
		<span><liferay-ui:message key="stars" />:</span> <%= statsUser.getRatingsTotalEntries() %>
	</div>

	<div class="blogger-date">
		<span><liferay-ui:message key="date" />:</span> <%= dateFormatDate.format(statsUser.getLastPostDate()) %>
	</div>
</liferay-ui:user-display>