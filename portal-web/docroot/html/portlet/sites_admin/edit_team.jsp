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

Team team = (Team)request.getAttribute(WebKeys.TEAM);

long teamId = BeanParamUtil.getLong(team, request, "teamId");

long groupId = BeanParamUtil.getLong(team, request, "groupId");

Group group = GroupServiceUtil.getGroup(groupId);

Organization organization = null;

if (group.isOrganization()) {
	organization = OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId());
}
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	escapeXml="<%= false %>"
	localizeTitle="<%= false %>"
	title='<%= ((team == null) ? LanguageUtil.get(pageContext, "new-team") : HtmlUtil.escape(team.getName())) %>'
/>

<portlet:actionURL var="editTeamURL">
	<portlet:param name="struts_action" value="/sites_admin/edit_team" />
</portlet:actionURL>

<aui:form action="<%= editTeamURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveTeam();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="teamId" type="hidden" value="<%= teamId %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />

	<liferay-ui:error exception="<%= DuplicateTeamException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= TeamNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= team %>" model="<%= Team.class %>" />

	<aui:fieldset>
		<c:if test="<%= team != null %>">
			<aui:field-wrapper label="team-id">
				<liferay-ui:input-resource url="<%= String.valueOf(team.getTeamId()) %>" />
			</aui:field-wrapper>
		</c:if>

		<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" name="name" />

		<aui:input name="description" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveTeam() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (team == null) ? Constants.ADD : Constants.UPDATE %>";

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>

<%
if (group.isOrganization()) {
	UsersAdminUtil.addPortletBreadcrumbEntries(organization, request, renderResponse);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, group.getDescriptiveName(locale), null);
}

if (team != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "manage-teams"), redirect);
	PortalUtil.addPortletBreadcrumbEntry(request, team.getName(), null);
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "manage-teams"), redirect);
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-team"), currentURL);
}
%>