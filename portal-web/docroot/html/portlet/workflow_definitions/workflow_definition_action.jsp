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

<%@ include file="/html/portlet/workflow_definitions/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WorkflowDefinition workflowDefinition = (WorkflowDefinition)row.getObject();
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="editURL">
		<portlet:param name="struts_action" value="/workflow_definitions/edit_workflow_definition" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="name" value="<%= workflowDefinition.getName() %>" />
		<portlet:param name="version" value="<%= String.valueOf(workflowDefinition.getVersion()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="post"
		message='<%= LanguageUtil.format(pageContext, "add-new-x", "file") %>'
		url="<%= editURL %>"
	/>

	<c:if test='<%= DeployManagerUtil.isDeployed("kaleo-designer-portlet") %>'>

		<%
		String taglibOnClick = "javascript:Liferay.Util.getOpener()." + renderResponse.getNamespace() + "openKaleoDesigner('" + workflowDefinition.getName() + "', '" + workflowDefinition.getVersion() + "', '', Liferay.Util.getWindowName());";
		%>

		<liferay-ui:icon
			image="edit"
			url="<%= taglibOnClick %>"
		/>
	</c:if>

	<c:if test="<%= !workflowDefinition.isActive() %>">
		<portlet:actionURL var="restoreWorkflowDefinitionURL">
			<portlet:param name="struts_action" value="/workflow_definitions/edit_workflow_definition" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="name" value="<%= workflowDefinition.getName() %>" />
			<portlet:param name="version" value="<%= String.valueOf(workflowDefinition.getVersion()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			image="activate"
			url="<%= restoreWorkflowDefinitionURL %>"
		/>
	</c:if>

	<portlet:actionURL var="deleteURL">
		<portlet:param name="struts_action" value="/workflow_definitions/edit_workflow_definition" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= workflowDefinition.isActive() ? Constants.DEACTIVATE : Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="name" value="<%= workflowDefinition.getName() %>" />
		<portlet:param name="version" value="<%= String.valueOf(workflowDefinition.getVersion()) %>" />
	</portlet:actionURL>

	<c:choose>
		<c:when test="<%= workflowDefinition.isActive() %>">
			<liferay-ui:icon-deactivate url="<%= deleteURL %>" />
		</c:when>
		<c:otherwise>
			<liferay-ui:icon-delete
				url="<%= deleteURL %>"
			/>
		</c:otherwise>
	</c:choose>
</liferay-ui:icon-menu>