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

if (Validator.isNull(redirect) && Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("struts_action", "/mobile_device_rules/view");
	portletURL.setParameter("groupId", String.valueOf(groupId));

	backURL = portletURL.toString();
}

long ruleGroupId = ParamUtil.getLong(request, "ruleGroupId");

MDRRuleGroup ruleGroup = MDRRuleGroupLocalServiceUtil.getRuleGroup(ruleGroupId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/mobile_device_rules/view_rules");
portletURL.setParameter("ruleGroupId", String.valueOf(ruleGroupId));
portletURL.setParameter("groupId", String.valueOf(groupId));
portletURL.setParameter("redirect", redirect);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle="<%= false %>"
	title='<%= LanguageUtil.format(pageContext, "classification-rules-for-x", ruleGroup.getName(locale), false) %>'
/>

<aui:nav-bar>
	<aui:nav>
		<liferay-portlet:renderURL var="addURL">
			<portlet:param name="struts_action" value="/mobile_device_rules/edit_rule" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="ruleGroupId" value="<%= String.valueOf(ruleGroupId) %>" />
		</liferay-portlet:renderURL>

		<aui:nav-item href="<%= addURL %>" iconCssClass="icon-plus" label="add-classification-rule" />
	</aui:nav>
</aui:nav-bar>

<div class="separator"><!-- --></div>

<liferay-ui:search-container
	delta="<%= 5 %>"
	deltaConfigurable="<%= false %>"
	emptyResultsMessage="no-classification-rules-are-configured-for-this-device-family"
	headerNames="name,type"
	iteratorURL="<%= portletURL %>"
	total="<%= MDRRuleLocalServiceUtil.getRulesCount(ruleGroupId) %>"
>
	<liferay-ui:search-container-results
		results="<%= MDRRuleLocalServiceUtil.getRules(ruleGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.mobiledevicerules.model.MDRRule"
		escapedModel="<%= true %>"
		keyProperty="ruleId"
		modelVar="rule"
	>
		<liferay-portlet:renderURL var="rowURL">
			<portlet:param name="struts_action" value="/mobile_device_rules/edit_rule" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="ruleId" value="<%= String.valueOf(rule.getRuleId()) %>" />
		</liferay-portlet:renderURL>

		<%@ include file="/html/portlet/mobile_device_rules/rule_columns.jspf" %>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator type="more" />
</liferay-ui:search-container>