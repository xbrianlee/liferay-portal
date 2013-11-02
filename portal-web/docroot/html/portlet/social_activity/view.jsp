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

<%@ include file="/html/portlet/social_activity/init.jsp" %>

<%
Map<String, Boolean> activitySettingsMap = (Map<String, Boolean>)request.getAttribute(WebKeys.SOCIAL_ACTIVITY_SETTINGS_MAP);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/social_activity/view");
%>

<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-do-not-have-the-required-permissions" />

<portlet:actionURL var="saveActivitySettingsURL">
	<portlet:param name="struts_action" value="/social_activity/view" />
</portlet:actionURL>

<aui:form action="<%= saveActivitySettingsURL.toString() %>" cssClass="update-socialactivity-form" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input id="settingsJSON" name="settingsJSON" type="hidden" />

	<h4>
		<liferay-ui:message key="enable-social-activity-for" />:
	</h4>

	<aui:row cssClass="social-activity social-activity-settings" id="settings">
		<aui:col cssClass="social-activity-items" width="<%= 20 %>">

			<%
			for (String className : activitySettingsMap.keySet()) {
				String localizedClassName = ResourceActionsUtil.getModelResource(locale, className);

				boolean enabled = activitySettingsMap.get(className);
			%>

				<h4 class="social-activity-item" data-modelName="<%= className %>" title="<%= localizedClassName %>">
					<div class="social-activity-item-content">
						<aui:input disabled="<%= !SocialActivityPermissionUtil.contains(permissionChecker, themeDisplay.getSiteGroupId(), ActionKeys.CONFIGURATION) %>" inlineField="<%= true %>" label="" name='<%= className + ".enabled" %>' title="" type="checkbox" value="<%= enabled %>" />

						<a class="settings-label" href="javascript:;"><%= localizedClassName %></a>
					</div>
				</h4>

			<%
			}
			%>

		</aui:col>
		<aui:col cssClass="social-activity-details" width="<%= 80 %>" />
	</aui:row>

	<%
	List<String> activityDefinitionLanguageKeys = new ArrayList<String>();

	for (String modelName : activitySettingsMap.keySet()) {
		List<SocialActivityDefinition> activityDefinitions = SocialConfigurationUtil.getActivityDefinitions(modelName);

		for (SocialActivityDefinition activityDefinition : activityDefinitions) {
			activityDefinitionLanguageKeys.add("'" + modelName + "." + activityDefinition.getLanguageKey() + "': \"" + activityDefinition.getName(locale) + "\"");
		}
	}
	%>

	<aui:script use="liferay-social-activity-admin">
		new Liferay.Portlet.SocialActivity.Admin(
			{
				activityDefinitionLanguageKeys: {
					<%= StringUtil.merge(activityDefinitionLanguageKeys) %>
				},
				counterSettings: {
					contributionIncrements: [<%= StringUtil.merge(PropsValues.SOCIAL_ACTIVITY_CONTRIBUTION_INCREMENTS) %>],
					contributionLimitValues: [<%= StringUtil.merge(PropsValues.SOCIAL_ACTIVITY_CONTRIBUTION_LIMIT_VALUES) %>],
					participationIncrements: [<%= StringUtil.merge(PropsValues.SOCIAL_ACTIVITY_PARTICIPATION_INCREMENTS) %>],
					participationLimitValues: [<%= StringUtil.merge(PropsValues.SOCIAL_ACTIVITY_PARTICIPATION_LIMIT_VALUES) %>]
				},
				namespace: '<portlet:namespace />',
				portletId: '<%= portletDisplay.getId() %>'
			}
		);
	</aui:script>
</aui:form>