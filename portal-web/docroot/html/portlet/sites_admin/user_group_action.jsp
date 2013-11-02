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

UserGroup userGroup = (UserGroup)row.getObject();

Group group = (Group)row.getParameter("group");
%>

<liferay-ui:icon-menu>
	<c:if test="<%= permissionChecker.isGroupOwner(group.getGroupId()) %>">
		<portlet:renderURL var="assignURL">
			<portlet:param name="struts_action" value="/sites_admin/edit_site_assignments" />
			<portlet:param name="tabs1" value="user-groups" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="userGroupId" value="<%= String.valueOf(userGroup.getUserGroupId()) %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="assign_user_group_roles"
			message="assign-site-roles"
			url="<%= assignURL %>"
		/>

		<portlet:actionURL var="removeURL">
			<portlet:param name="struts_action" value="/sites_admin/edit_site_assignments" />
			<portlet:param name="<%= Constants.CMD %>" value="group_user_groups" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
			<portlet:param name="removeUserGroupIds" value="<%= String.valueOf(userGroup.getUserGroupId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			image="unassign_user_group"
			message="remove-membership"
			url="<%= removeURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>