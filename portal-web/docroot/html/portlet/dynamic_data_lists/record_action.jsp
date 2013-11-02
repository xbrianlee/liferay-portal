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
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");

String redirect = searchContainer.getIteratorURL().toString();

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DDLRecord record = (DDLRecord)row.getObject();

long formDDMTemplateId = GetterUtil.getLong((String)row.getParameter("formDDMTemplateId"));

boolean editable = GetterUtil.getBoolean((String)row.getParameter("editable"));

DDLRecordVersion recordVersion = record.getRecordVersion();

if (editable) {
	recordVersion = record.getLatestRecordVersion();
}
%>

<liferay-ui:icon-menu>
	<c:if test="<%= DDLRecordSetPermission.contains(permissionChecker, record.getRecordSet(), ActionKeys.VIEW) %>">
		<portlet:renderURL var="viewRecordURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="struts_action" value="/dynamic_data_lists/view_record" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="recordId" value="<%= String.valueOf(record.getRecordId()) %>" />
			<portlet:param name="version" value="<%= recordVersion.getVersion() %>" />
			<portlet:param name="formDDMTemplateId" value="<%= String.valueOf(formDDMTemplateId) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="view"
			url="<%= viewRecordURL %>"
		/>
	</c:if>

	<c:if test="<%= DDLRecordSetPermission.contains(permissionChecker, record.getRecordSet(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editRecordURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="struts_action" value="/dynamic_data_lists/edit_record" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="recordId" value="<%= String.valueOf(record.getRecordId()) %>" />
			<portlet:param name="formDDMTemplateId" value="<%= String.valueOf(formDDMTemplateId) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editRecordURL %>"
		/>
	</c:if>

	<c:if test="<%= DDLRecordSetPermission.contains(permissionChecker, record.getRecordSet(), ActionKeys.DELETE) %>">
		<portlet:actionURL var="deleteRecordURL">
			<portlet:param name="struts_action" value="/dynamic_data_mapping_list/edit_record" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="recordId" value="<%= String.valueOf(record.getRecordId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteRecordURL %>" />
	</c:if>
</liferay-ui:icon-menu>