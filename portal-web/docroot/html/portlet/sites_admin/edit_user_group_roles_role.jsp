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
String redirect = (String)request.getAttribute("edit_user_group_roles.jsp-redirect");

Group group = (Group)request.getAttribute("edit_user_group_roles.jsp-group");
int roleType = (Integer)request.getAttribute("edit_user_group_roles.jsp-roleType");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_user_group_roles.jsp-portletURL");
%>

<div>
	<%= LanguageUtil.format(pageContext, "step-x-of-x", new String[] {"1", "2"}) %>

	<liferay-ui:message key="choose-a-role" />
</div>

<br />

<h3><liferay-ui:message key="roles" /></h3>

<%
RoleSearch searchContainer = new RoleSearch(renderRequest, portletURL);
%>

<liferay-ui:search-form
	page="/html/portlet/roles_admin/role_search.jsp"
	searchContainer="<%= searchContainer %>"
/>

<%
RoleSearchTerms searchTerms = (RoleSearchTerms)searchContainer.getSearchTerms();

List<Role> roles = RoleLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), new Integer[] {roleType}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, searchContainer.getOrderByComparator());

roles = UsersAdminUtil.filterGroupRoles(permissionChecker, group.getGroupId(), roles);

int total = roles.size();

searchContainer.setTotal(total);

List results = ListUtil.subList(roles, searchContainer.getStart(), searchContainer.getEnd());

searchContainer.setResults(results);
%>

<div class="separator"><!-- --></div>

<%
List resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	Role curRole = (Role)results.get(i);

	curRole = curRole.toEscapedModel();

	ResultRow row = new ResultRow(curRole, curRole.getRoleId(), i);

	PortletURL rowURL = renderResponse.createRenderURL();

	rowURL.setParameter("struts_action", "/sites_admin/edit_user_group_roles");
	rowURL.setParameter("redirect", redirect);
	rowURL.setParameter("groupId", String.valueOf(group.getGroupId()));
	rowURL.setParameter("roleId", String.valueOf(curRole.getRoleId()));

	// Name

	row.addText(curRole.getTitle(locale), rowURL);

	// Type

	row.addText(LanguageUtil.get(pageContext, curRole.getTypeLabel()), rowURL);

	// Description

	row.addText(curRole.getDescription(locale), rowURL);

	// Add result row

	resultRows.add(row);
}
%>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />