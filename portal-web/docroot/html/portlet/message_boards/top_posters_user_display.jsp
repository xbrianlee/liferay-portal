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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MBStatsUser statsUser = (MBStatsUser)row.getObject();

String[] ranks = MBUtil.getUserRank(portletPreferences, themeDisplay.getLanguageId(), statsUser);
%>

<liferay-ui:user-display userId="<%= statsUser.getUserId() %>">
	<c:if test="<%= Validator.isNotNull(ranks[0]) %>">
		<div class="thread-user-rank">
			<span><liferay-ui:message key="rank" />:</span> <%= ranks[0] %>
		</div>
	</c:if>

	<div class="thread-user-post-count">
		<span><liferay-ui:message key="posts" />:</span> <%= statsUser.getMessageCount() %>
	</div>

	<div class="thread-user-join-date">
		<span><liferay-ui:message key="join-date" />:</span> <%= dateFormatDate.format(userDisplay.getCreateDate()) %>
	</div>

	<c:if test="<%= statsUser.getLastPostDate() != null %>">
		<div class="thread-user-last-post-date">
			<span><liferay-ui:message key="last-post-date" />:</span> <%= dateFormatDate.format(statsUser.getLastPostDate()) %>
		</div>
	</c:if>
</liferay-ui:user-display>