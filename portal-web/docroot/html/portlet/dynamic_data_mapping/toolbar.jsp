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

<%@ include file="/html/portlet/dynamic_data_mapping/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, "toolbarItem");

long groupId = ParamUtil.getLong(request, "groupId", scopeGroupId);
%>

<aui:nav-bar>
	<aui:nav>
		<portlet:renderURL var="viewStructuresURL">
			<portlet:param name="struts_action" value="/dynamic_data_mapping/view" />
			<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
		</portlet:renderURL>

		<c:if test="<%= ddmDisplay.isShowAddStructureButton() && DDMPermission.contains(permissionChecker, groupId, ddmDisplay.getResourceName(), ddmDisplay.getAddStructureActionId()) %>">
			<portlet:renderURL var="addStructureURL">
				<portlet:param name="struts_action" value="/dynamic_data_mapping/edit_structure" />
				<portlet:param name="redirect" value="<%= viewStructuresURL %>" />
				<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
			</portlet:renderURL>

			<aui:nav-item href="<%= addStructureURL %>" iconCssClass="icon-plus" label="add" selected='<%= toolbarItem.equals("add") %>' />
		</c:if>
	</aui:nav>

	<aui:nav-bar-search cssClass="pull-right" file="/html/portlet/dynamic_data_mapping/structure_search.jsp" />
</aui:nav-bar>