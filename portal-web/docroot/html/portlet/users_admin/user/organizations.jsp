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
User selUser = (User)request.getAttribute("user.selUser");

List<Organization> organizations = (List<Organization>)request.getAttribute("user.organizations");
%>

<liferay-ui:error-marker key="errorSection" value="organizations" />

<liferay-ui:membership-policy-error />

<liferay-util:buffer var="removeOrganizationIcon">
	<liferay-ui:icon
		image="unlink"
		label="<%= true %>"
		message="remove"
	/>
</liferay-util:buffer>

<h3><liferay-ui:message key="organizations" /></h3>

<liferay-ui:search-container
	headerNames="name,type,roles,null"
>
	<liferay-ui:search-container-results
		results="<%= organizations %>"
		total="<%= organizations.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Organization"
		escapedModel="<%= true %>"
		keyProperty="organizationId"
		modelVar="organization"
	>
		<liferay-ui:search-container-column-text
			name="name"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			name="type"
			value="<%= LanguageUtil.get(pageContext, organization.getType()) %>"
		/>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			name="roles"
		>

			<%
			if (selUser != null) {
				List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(selUser.getUserId(), organization.getGroup().getGroupId());

				for (UserGroupRole userGroupRole : userGroupRoles) {
					Role role = RoleLocalServiceUtil.getRole(userGroupRole.getRoleId());

					buffer.append(HtmlUtil.escape(role.getTitle(locale)));
					buffer.append(StringPool.COMMA_AND_SPACE);
				}

				if (!userGroupRoles.isEmpty()) {
					buffer.setIndex(buffer.index() - 1);
				}
			}
			%>

		</liferay-ui:search-container-column-text>

		<c:if test="<%= !portletName.equals(PortletKeys.MY_ACCOUNT) && ((selUser == null) || !OrganizationMembershipPolicyUtil.isMembershipProtected(permissionChecker, selUser.getUserId(), organization.getOrganizationId())) %>">
			<liferay-ui:search-container-column-text>
				<a class="modify-link" data-rowId="<%= organization.getOrganizationId() %>" href="javascript:;"><%= removeOrganizationIcon %></a>
			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>

<c:if test="<%= !portletName.equals(PortletKeys.MY_ACCOUNT) %>">
	<br />

	<liferay-ui:icon
		cssClass="modify-link"
		iconCssClass="icon-search"
		id="selectOrganizationLink"
		label="<%= true %>"
		linkCssClass="btn"
		message="select"
		method="get"
		url="javascript:;"
	/>
</c:if>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />organizationsSearchContainer');

	searchContainer.get('contentBox').delegate(
		'click',
		function(event) {
			var link = event.currentTarget;
			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));
		},
		'.modify-link'
	);

	var selectOrganizationLink = A.one('#<portlet:namespace />selectOrganizationLink');

	if (selectOrganizationLink) {
		selectOrganizationLink.on(
			'click',
			function(event) {
				Liferay.Util.selectEntity(
					{
						dialog: {
							constrain: true,
							modal: true
						},
						id: '<portlet:namespace />selectOrganization',
						title: '<liferay-ui:message arguments="organization" key="select-x" />',
						uri: '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/users_admin/select_organization" /><portlet:param name="p_u_i_d" value='<%= selUser == null ? "0" : String.valueOf(selUser.getUserId()) %>' /></portlet:renderURL>'
					},
					function(event) {
						var rowColumns = [];

						rowColumns.push(event.name);
						rowColumns.push(event.type);
						rowColumns.push('');
						rowColumns.push('<a class="modify-link" data-rowId="' + event.organizationid + '" href="javascript:;"><%= UnicodeFormatter.toString(removeOrganizationIcon) %></a>');

						searchContainer.addRow(rowColumns, event.organizationid);

						searchContainer.updateDataStore();
					}
				);
			}
		);
	}
</aui:script>