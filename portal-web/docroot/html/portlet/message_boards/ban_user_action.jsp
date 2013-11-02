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

MBBan ban = (MBBan)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= MBPermission.contains(permissionChecker, scopeGroupId, ActionKeys.BAN_USER) %>">
		<portlet:actionURL var="unbanUserURL">
			<portlet:param name="struts_action" value="/message_boards/ban_user" />
			<portlet:param name="<%= Constants.CMD %>" value="unban" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="banUserId" value="<%= String.valueOf(ban.getBanUserId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			image="../message_boards/unban_user"
			message="unban-this-user"
			url="<%= unbanUserURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>