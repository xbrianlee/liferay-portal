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

<%@ include file="/html/portlet/sites_admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

User user2 = (User)row.getObject();

Group group = (Group)row.getParameter("group");

boolean organizationUser = GetterUtil.getBoolean(row.getParameter("organizationUser"));
boolean userGroupUser = GetterUtil.getBoolean(row.getParameter("userGroupUser"));
%>

<liferay-ui:icon-menu showWhenSingleIcon="<%= true %>">
	<c:if test="<%= permissionChecker.isGroupOwner(group.getGroupId()) %>">
		<portlet:renderURL var="assignURL">
			<portlet:param name="struts_action" value="/sites_admin/edit_site_assignments" />
			<portlet:param name="tabs1" value="users" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="p_u_i_d" value="<%= String.valueOf(user2.getUserId()) %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="assign_user_roles"
			message="assign-site-roles"
			url="<%= assignURL %>"
		/>

		<c:if test="<%= !(organizationUser || userGroupUser) && !SiteMembershipPolicyUtil.isMembershipRequired(user2.getUserId(), group.getGroupId()) %>">
			<portlet:actionURL var="removeURL">
				<portlet:param name="struts_action" value="/sites_admin/edit_site_assignments" />
				<portlet:param name="<%= Constants.CMD %>" value="group_users" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
				<portlet:param name="removeUserIds" value="<%= String.valueOf(user2.getUserId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="unassign_user"
				message="remove-membership"
				url="<%= removeURL %>"
			/>
		</c:if>
	</c:if>
</liferay-ui:icon-menu>