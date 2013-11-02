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
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "view-members");

Group group = (Group)request.getAttribute(WebKeys.GROUP);

if (group == null) {
	long groupId = ParamUtil.getLong(request, "groupId");

	group = GroupServiceUtil.getGroup(groupId);
}
%>

<liferay-portlet:renderURL varImpl="assignMembersURL">
	<liferay-portlet:param name="struts_action" value="/sites_admin/edit_site_assignments" />
	<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
</liferay-portlet:renderURL>

<aui:nav-bar>
	<aui:nav>
		<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.ASSIGN_MEMBERS) %>">
			<aui:nav-item dropdown="<%= true %>" iconCssClass="icon-plus" label="add-members" selected='<%= toolbarItem.equals("add-members") %>'>

				<%
				assignMembersURL.setParameter("tabs1", "users");
				assignMembersURL.setParameter("tabs2", "available");
				%>

				<aui:nav-item href="<%= assignMembersURL.toString() %>" iconCssClass="icon-user" label="user" />

				<%
				assignMembersURL.setParameter("tabs1", "organizations");
				assignMembersURL.setParameter("tabs2", "available");
				%>

				<aui:nav-item href="<%= assignMembersURL.toString() %>" iconCssClass="icon-globe" label="organization" />

				<%
				assignMembersURL.setParameter("tabs1", "user-groups");
				assignMembersURL.setParameter("tabs2", "available");
				%>

				<aui:nav-item href="<%= assignMembersURL.toString() %>" iconCssClass="icon-globe" label="user-group" />
			</aui:nav-item>
		</c:if>

		<c:if test="<%= permissionChecker.isGroupOwner(group.getGroupId()) || GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.ASSIGN_USER_ROLES) %>">
			<aui:nav-item dropdown="<%= true %>" iconCssClass="icon-plus" label="add-site-roles-to" selected='<%= toolbarItem.equals("assign-user-roles") %>'>
				<portlet:renderURL var="assignUserRolesURL">
					<portlet:param name="struts_action" value="/sites_admin/edit_user_roles" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:renderURL>

				<aui:nav-item href="<%= assignUserRolesURL %>" iconCssClass="icon-user" label="users" />

				<portlet:renderURL var="assignUserGroupRolesURL">
					<portlet:param name="struts_action" value="/sites_admin/edit_user_group_roles" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:renderURL>

				<aui:nav-item href="<%= assignUserGroupRolesURL %>" iconCssClass="icon-globe" label="user-groups" />
			</aui:nav-item>
		</c:if>

		<c:if test="<%= group.getType() == GroupConstants.TYPE_SITE_RESTRICTED %>">
			<portlet:renderURL var="viewMembershipRequestsURL">
				<portlet:param name="struts_action" value="/sites_admin/view_membership_requests" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:renderURL>

			<aui:nav-item href="<%= viewMembershipRequestsURL %>" label="view-membership-requests" selected='<%= toolbarItem.equals("view-membership-requests") %>' />
		</c:if>
	</aui:nav>
</aui:nav-bar>