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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

Group group = ActionUtil.getGroup(renderRequest);

long groupId = group.getGroupId();

Organization organization = null;

if (group.isOrganization()) {
	organization = OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId());
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/sites_admin/view_teams");
portletURL.setParameter("groupId", String.valueOf(groupId));

pageContext.setAttribute("portletURL", portletURL);
%>

<c:if test="<%= !layout.isTypeControlPanel() %>">
	<liferay-ui:header
		backURL="<%= backURL %>"
		escapeXml="<%= false %>"
		localizeTitle="<%= false %>"
		title='<%= HtmlUtil.escape(group.getDescriptiveName(locale)) + StringPool.COLON + StringPool.SPACE + LanguageUtil.get(pageContext, "manage-memberships") %>'
	/>
</c:if>

<aui:form action="<%= portletURL.toString() %>" cssClass="form-search" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />

	<%
	TeamSearch searchContainer = new TeamSearch(renderRequest, portletURL);

	List headerNames = searchContainer.getHeaderNames();

	headerNames.add(StringPool.BLANK);

	TeamDisplayTerms searchTerms = (TeamDisplayTerms)searchContainer.getSearchTerms();

	int total = TeamLocalServiceUtil.searchCount(groupId, searchTerms.getName(), searchTerms.getDescription(), new LinkedHashMap<String, Object>());

	searchContainer.setTotal(total);

	List results = TeamLocalServiceUtil.search(groupId, searchTerms.getName(), searchTerms.getDescription(), new LinkedHashMap<String, Object>(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

	searchContainer.setResults(results);

	portletURL.setParameter(searchContainer.getCurParam(), String.valueOf(searchContainer.getCur()));
	%>

	<liferay-ui:input-search name="<%= searchTerms.NAME %>" />

	<div class="separator"><!-- --></div>

	<%
	List resultRows = searchContainer.getResultRows();

	for (int i = 0; i < results.size(); i++) {
		Team team = (Team)results.get(i);

		team = team.toEscapedModel();

		ResultRow row = new ResultRow(team, team.getTeamId(), i);

		PortletURL rowURL = null;

		if (TeamPermissionUtil.contains(permissionChecker, team.getTeamId(), ActionKeys.UPDATE)) {
			rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("struts_action", "/sites_admin/edit_team");
			rowURL.setParameter("redirect", currentURL);
			rowURL.setParameter("teamId", String.valueOf(team.getTeamId()));
		}

		// Name

		row.addText(team.getName(), rowURL);

		// Description

		row.addText(team.getDescription(), rowURL);

		// Action

		row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/sites_admin/team_action.jsp");

		// Add result row

		resultRows.add(row);
	}
	%>

	<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, groupId, ActionKeys.MANAGE_TEAMS) %>">
		<portlet:renderURL var="addTeamURL">
			<portlet:param name="struts_action" value="/sites_admin/edit_team" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
		</portlet:renderURL>

		<aui:button href="<%= addTeamURL %>" value="add-team" />
	</c:if>

	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
</aui:form>

<%
if (group.isOrganization()) {
	UsersAdminUtil.addPortletBreadcrumbEntries(organization, request, renderResponse);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, group.getDescriptiveName(locale), null);
}

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "manage-teams"), currentURL);
%>