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

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portal.DuplicateUserGroupException" %><%@
page import="com.liferay.portal.RequiredUserGroupException" %><%@
page import="com.liferay.portal.UserGroupNameException" %><%@
page import="com.liferay.portal.security.membershippolicy.UserGroupMembershipPolicyUtil" %><%@
page import="com.liferay.portal.service.permission.UserGroupPermissionUtil" %><%@
page import="com.liferay.portlet.usergroupsadmin.search.UserUserGroupChecker" %>

<%
boolean filterManageableOrganizations = true;

if (permissionChecker.hasPermission(scopeGroupId, User.class.getName(), company.getCompanyId(), ActionKeys.VIEW)) {
	filterManageableOrganizations = false;
}

if (portletName.equals(PortletKeys.MY_ACCOUNT) || permissionChecker.hasPermission(scopeGroupId, Organization.class.getName(), company.getCompanyId(), ActionKeys.VIEW)) {
	filterManageableOrganizations = false;
}

boolean filterManageableUserGroups = true;

if (portletName.equals(PortletKeys.MY_ACCOUNT) || permissionChecker.hasPermission(scopeGroupId, UserGroup.class.getName(), company.getCompanyId(), ActionKeys.VIEW)) {
	filterManageableUserGroups = false;
}
%>

<%@ include file="/html/portlet/user_groups_admin/init-ext.jsp" %>