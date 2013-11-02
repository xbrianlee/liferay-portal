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

<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "browse");

String usersListView = ParamUtil.get(request, "usersListView", UserConstants.LIST_VIEW_TREE);
%>

<aui:nav>

	<%
	boolean hasAddOrganizationPermission = PortalPermissionUtil.contains(permissionChecker, ActionKeys.ADD_ORGANIZATION);
	boolean hasAddUserPermission = PortalPermissionUtil.contains(permissionChecker, ActionKeys.ADD_USER);
	%>

	<c:if test="<%= hasAddOrganizationPermission || hasAddUserPermission %>">
		<aui:nav-item dropdown="<%= true %>" iconCssClass="icon-plus" label="add" selected='<%= toolbarItem.equals("add") %>'>
			<portlet:renderURL var="viewUsersURL">
				<portlet:param name="struts_action" value="/users_admin/view" />
				<portlet:param name="sitesListView" value="<%= usersListView %>" />
			</portlet:renderURL>

			<c:if test="<%= hasAddUserPermission %>">
				<portlet:renderURL var="addUserURL">
					<portlet:param name="struts_action" value="/users_admin/edit_user" />
					<portlet:param name="redirect" value="<%= viewUsersURL %>" />
				</portlet:renderURL>

				<aui:nav-item href="<%= addUserURL %>" iconCssClass="icon-user" label="user" />
			</c:if>

			<aui:nav-item cssClass="divider" />

			<c:if test="<%= hasAddOrganizationPermission %>">

				<%
				for (String organizationType : PropsValues.ORGANIZATIONS_TYPES) {
				%>

					<portlet:renderURL var="addOrganizationURL">
						<portlet:param name="struts_action" value="/users_admin/edit_organization" />
						<portlet:param name="redirect" value="<%= viewUsersURL %>" />
						<portlet:param name="type" value="<%= organizationType %>" />
					</portlet:renderURL>

					<aui:nav-item href="<%= addOrganizationURL %>" iconCssClass="icon-globe" label="<%= LanguageUtil.get(pageContext, organizationType) %>" />

				<%
				}
				%>

			</c:if>
		</aui:nav-item>
	</c:if>

	<c:choose>
		<c:when test="<%= PortalPermissionUtil.contains(permissionChecker, ActionKeys.EXPORT_USER) %>">
			<aui:nav-item href='<%= "javascript:" + renderResponse.getNamespace() + "exportUsers();" %>' label="export-users" selected='<%= toolbarItem.equals("export-users") %>' />
		</c:when>
		<c:when test="<%= PortletPermissionUtil.contains(permissionChecker, PortletKeys.USERS_ADMIN, ActionKeys.EXPORT_USER) %>">
			<aui:nav-item href='<%= "javascript:" + renderResponse.getNamespace() + "exportUsers();" %>' label="export-organization-users" selected='<%= toolbarItem.equals("export-organization-users") %>' />
		</c:when>
	</c:choose>
</aui:nav>

<aui:script>
	function <portlet:namespace />exportUsers() {
		document.<portlet:namespace />fm.method = "post";

		submitForm(document.<portlet:namespace />fm, "<portlet:actionURL><portlet:param name="struts_action" value="/users_admin/export_users" /></portlet:actionURL>&compress=0&etag=0&strip=0", false);
	}
</aui:script>