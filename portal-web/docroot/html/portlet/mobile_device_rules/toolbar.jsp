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
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "add");
%>

<c:if test="<%= MDRPermissionUtil.contains(permissionChecker, groupId, ActionKeys.ADD_RULE_GROUP) %>">
	<portlet:renderURL var="viewRulesURL">
		<portlet:param name="struts_action" value="/mobile_device_rules/view" />
	</portlet:renderURL>

	<liferay-portlet:renderURL var="addRuleGroupURL">
		<portlet:param name="struts_action" value="/mobile_device_rules/edit_rule_group" />
		<portlet:param name="redirect" value="<%= viewRulesURL %>" />
		<portlet:param name="backURL" value="<%= viewRulesURL %>" />
		<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
	</liferay-portlet:renderURL>

	<aui:nav-bar>
		<aui:nav>
			<aui:nav-item href="<%= addRuleGroupURL %>" iconCssClass="icon-plus" label="add-device-family" selected='<%= toolbarItem.equals("add") %>' />
		</aui:nav>

		<aui:nav-bar-search cssClass="pull-right" file="/html/portlet/mobile_device_rules/rule_group_search.jsp" />
	</aui:nav-bar>
</c:if>