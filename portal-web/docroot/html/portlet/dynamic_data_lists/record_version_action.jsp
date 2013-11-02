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

<%@ include file="/html/portlet/dynamic_data_lists/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DDLRecordVersion recordVersion = (DDLRecordVersion)row.getObject();

long formDDMTemplateId = GetterUtil.getLong((String)row.getParameter("formDDMTemplateId"));
%>

<liferay-ui:icon-menu>
	<liferay-portlet:renderURL portletName="<%= PortletKeys.DYNAMIC_DATA_LISTS %>" var="viewRecordVersionURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="struts_action" value="/dynamic_data_lists/view_record" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="recordId" value="<%= String.valueOf(recordVersion.getRecordId()) %>" />
		<portlet:param name="version" value="<%= recordVersion.getVersion() %>" />
		<portlet:param name="formDDMTemplateId" value="<%= String.valueOf(formDDMTemplateId) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:icon
		image="view"
		url="<%= viewRecordVersionURL %>"
	/>

	<portlet:actionURL var="revertURL">
		<portlet:param name="struts_action" value="/dynamic_data_lists/edit_record" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.REVERT %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="recordId" value="<%= String.valueOf(recordVersion.getRecordId()) %>" />
		<portlet:param name="version" value="<%= String.valueOf(recordVersion.getVersion()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		image="undo"
		message="revert"
		url="<%= revertURL %>"
	/>
</liferay-ui:icon-menu>