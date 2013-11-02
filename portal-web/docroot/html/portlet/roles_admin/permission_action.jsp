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

<%@ include file="/html/portlet/roles_admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objArray = (Object[])row.getObject();

Permission permission = (Permission)objArray[0];
Role role = (Role)objArray[1];
%>

<liferay-ui:icon-menu>
	<portlet:actionURL var="deletePermissionURL">
		<portlet:param name="struts_action" value="/roles_admin/edit_role_permissions" />
		<portlet:param name="<%= Constants.CMD %>" value="delete_permission" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="roleId" value="<%= String.valueOf(role.getRoleId()) %>" />
		<portlet:param name="name" value="<%= permission.getName() %>" />
		<portlet:param name="scope" value="<%= String.valueOf(permission.getScope()) %>" />
		<portlet:param name="primKey" value="<%= permission.getPrimKey() %>" />
		<portlet:param name="actionId" value="<%= String.valueOf(permission.getActionId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		confirmation="are-your-sure-you-want-to-remove-this-permission"
		message="remove"
		url="<%= deletePermissionURL %>"
	/>
</liferay-ui:icon-menu>