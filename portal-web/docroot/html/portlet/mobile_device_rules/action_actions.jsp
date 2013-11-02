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
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");

String redirect = searchContainer.getIteratorURL().toString();

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MDRAction action = (MDRAction)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= MDRRuleGroupInstancePermissionUtil.contains(permissionChecker, action.getRuleGroupInstanceId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editActionURL">
			<portlet:param name="struts_action" value="/mobile_device_rules/edit_action" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="actionId" value="<%= String.valueOf(action.getActionId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" url="<%= editActionURL %>" />

		<portlet:actionURL var="deleteURL">
			<portlet:param name="struts_action" value="/mobile_device_rules/edit_action" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="actionId" value="<%= String.valueOf(action.getActionId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL.toString() %>" />
	</c:if>
</liferay-ui:icon-menu>