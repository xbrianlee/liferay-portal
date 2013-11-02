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

<%@ include file="/html/portlet/directory/init.jsp" %>

<%
OrganizationSearch searchContainer = (OrganizationSearch)request.getAttribute("liferay-ui:search:searchContainer");

String redirect = searchContainer.getIteratorURL().toString();

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Organization organization = (Organization)row.getObject();

long organizationId = organization.getOrganizationId();
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="viewUsersURL">
		<portlet:param name="struts_action" value="/directory/view" />
		<portlet:param name="tabs1" value="users" />
		<portlet:param name="viewUsersRedirect" value="<%= currentURL %>" />
		<portlet:param name="organizationId" value="<%= String.valueOf(organizationId) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="view_users"
		message="view-users"
		method="get" url="<%= viewUsersURL %>"
	/>

	<c:if test="<%= organization.hasSuborganizations() %>">
		<portlet:renderURL var="viewSuborganizationsURL">
			<portlet:param name="struts_action" value="/directory/view" />
			<portlet:param name="tabs1" value="organizations" />
			<portlet:param name="viewUsersRedirect" value="<%= currentURL %>" />
			<portlet:param name="parentOrganizationId" value="<%= String.valueOf(organizationId) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="view_locations" message="view-suborganizations" method="get" url="<%= viewSuborganizationsURL %>" />
	</c:if>
</liferay-ui:icon-menu>