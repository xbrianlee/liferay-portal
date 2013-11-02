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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objects = (Object[])row.getObject();

PortletItem portletItem = (PortletItem)objects[0];
portletResource = (String)objects[1];
%>

<liferay-ui:icon-menu>
	<portlet:actionURL var="restoreURL">
		<portlet:param name="struts_action" value="/portlet_configuration/edit_archived_setups" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="portletResource" value="<%= portletResource %>" />
		<portlet:param name="name" value="<%= portletItem.getName() %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		image="undo"
		message="restore"
		url="<%= restoreURL %>"
	/>

	<portlet:actionURL var="deleteURL">
		<portlet:param name="struts_action" value="/portlet_configuration/edit_archived_setups" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="portletResource" value="<%= portletResource %>" />
		<portlet:param name="portletItemId" value="<%= String.valueOf(portletItem.getPortletItemId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>