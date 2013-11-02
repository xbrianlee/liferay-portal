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

<%@ include file="/html/portlet/mobile_device_rules/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

MDRRuleGroup ruleGroup = (MDRRuleGroup)renderRequest.getAttribute(WebKeys.MOBILE_DEVICE_RULES_RULE_GROUP);

long ruleGroupId = BeanParamUtil.getLong(ruleGroup, request, "ruleGroupId");
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle="<%= (ruleGroup == null) %>"
	title='<%= (ruleGroup == null) ? "new-device-family" : ruleGroup.getName(locale) %>'
/>

<c:if test="<%= ruleGroup == null %>">
	<div class="alert alert-info">
		<liferay-ui:message key="device-family-help" />
	</div>
</c:if>

<portlet:actionURL var="editRuleGroupURL">
	<portlet:param name="struts_action" value="/mobile_device_rules/edit_rule_group" />
</portlet:actionURL>

<aui:form action="<%= editRuleGroupURL %>" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (ruleGroup == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="ruleGroupId" type="hidden" value="<%= ruleGroupId %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />

	<liferay-ui:error exception="<%= NoSuchRuleGroupException.class %>" message="device-family-does-not-exist" />

	<aui:model-context bean="<%= ruleGroup %>" model="<%= MDRRuleGroup.class %>" />

	<aui:fieldset>
		<aui:input name="name" />

		<aui:input name="description" />
	</aui:fieldset>

	<c:if test="<%= ruleGroup != null %>">
		<aui:fieldset>
			<c:if test="<%= MDRRuleLocalServiceUtil.getRulesCount(ruleGroupId) == 0 %>">
				<div class="alert alert-info">
					<liferay-ui:message key="no-classification-rules-are-configured-for-this-device-family" />
				</div>
			</c:if>

			<liferay-portlet:renderURL var="editRulesURL">
				<portlet:param name="struts_action" value="/mobile_device_rules/view_rules" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="ruleGroupId" value="<%= String.valueOf(ruleGroupId) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:icon
				image="manage_nodes"
				label="<%= true %>"
				message="manage-classification-rules"
				url="<%= editRulesURL.toString() %>"
			/>
		</aui:fieldset>
	</c:if>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" value="cancel" />
	</aui:button-row>
</aui:form>