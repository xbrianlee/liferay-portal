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

<%@ include file="/html/portlet/workflow_tasks/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "assigned-to-me");
%>

<aui:nav-bar>
	<aui:nav>
		<portlet:renderURL var="assignedToMeURL">
			<portlet:param name="struts_action" value="/workflow_tasks/view" />
			<portlet:param name="toolbarItem" value="assigned-to-me" />
		</portlet:renderURL>

		<portlet:renderURL var="completedURL">
			<portlet:param name="struts_action" value="/workflow_tasks/view" />
			<portlet:param name="toolbarItem" value="my-completed-tasks" />
		</portlet:renderURL>

		<aui:nav-item href="<%= completedURL %>" iconCssClass="icon-plus" label="my-completed-tasks" selected='<%= toolbarItem.equals("my-completed-tasks") %>' />
	</aui:nav>
</aui:nav-bar>