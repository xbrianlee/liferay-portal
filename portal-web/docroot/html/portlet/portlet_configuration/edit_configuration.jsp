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

<%@ include file="/html/portlet/portlet_configuration/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String returnToFullPageURL = ParamUtil.getString(request, "returnToFullPageURL");

String path = (String)request.getAttribute(WebKeys.CONFIGURATION_ACTION_PATH);
%>

<c:if test="<%= !layout.isTypeControlPanel() && !windowState.equals(LiferayWindowState.EXCLUSIVE) %>">
	<liferay-util:include page="/html/portlet/portlet_configuration/tabs1.jsp">
		<liferay-util:param name="tabs1" value="setup" />
	</liferay-util:include>
</c:if>

<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, layout.getGroupId(), ActionKeys.MANAGE_ARCHIVED_SETUPS) && !windowState.equals(LiferayWindowState.EXCLUSIVE) %>">
	<portlet:renderURL var="archivedSetupsURL">
		<portlet:param name="struts_action" value="/portlet_configuration/edit_archived_setups" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="returnToFullPageURL" value="<%= returnToFullPageURL %>" />
		<portlet:param name="portletResource" value="<%= portletResource %>" />
	</portlet:renderURL>

	<div class="archived-setups">
		<liferay-ui:icon
			image="export"
			label="<%= true %>"
			message="archive-restore-setup"
			url="<%= archivedSetupsURL %>"
		/>
	</div>
</c:if>

<c:if test="<%= Validator.isNotNull(portletResource) && Validator.isNotNull(path) %>">
	<liferay-util:include page="<%= path %>" portletId="<%= portletResource %>" />
</c:if>