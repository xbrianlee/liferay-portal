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

<%@ include file="/html/portlet/software_catalog/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SCFrameworkVersion frameworkVersion = (SCFrameworkVersion)row.getObject();

String frameworkVersionId = String.valueOf(frameworkVersion.getFrameworkVersionId());
%>

<liferay-ui:icon-menu>
	<c:if test="<%= SCFrameworkVersionPermission.contains(permissionChecker, frameworkVersion, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="struts_action" value="/software_catalog/edit_framework_version" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="frameworkVersionId" value="<%= frameworkVersionId %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= SCFrameworkVersionPermission.contains(permissionChecker, frameworkVersion, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= SCFrameworkVersion.class.getName() %>"
			modelResourceDescription="<%= frameworkVersion.getName() %>"
			resourcePrimKey="<%= frameworkVersionId %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			image="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= SCFrameworkVersionPermission.contains(permissionChecker, frameworkVersion, ActionKeys.DELETE) %>">
		<portlet:actionURL var="deleteURL">
			<portlet:param name="struts_action" value="/software_catalog/edit_framework_version" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="frameworkVersionId" value="<%= frameworkVersionId %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>