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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MDRRule rule = (MDRRule)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= MDRRuleGroupPermissionUtil.contains(permissionChecker, rule.getRuleGroupId(), ActionKeys.UPDATE) %>">
		<liferay-portlet:renderURL var="editURL">
			<portlet:param name="struts_action" value="/mobile_device_rules/edit_rule" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="ruleId" value="<%= String.valueOf(rule.getRuleId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon image="edit" url="<%= editURL %>" />

		<portlet:actionURL var="deleteURL">
			<portlet:param name="struts_action" value="/mobile_device_rules/edit_rule" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="ruleId" value="<%= String.valueOf(rule.getRuleId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL %>" />
	</c:if>
</liferay-ui:icon-menu>