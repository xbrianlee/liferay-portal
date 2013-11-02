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
String toolbarItem = ParamUtil.getString(request, "toolbarItem");
%>

<aui:nav-bar>
	<aui:nav>
		<portlet:renderURL var="viewDefinitionsURL">
			<portlet:param name="struts_action" value="/workflow_definitions/view" />
			<portlet:param name="tabs1" value="workflow-definitions" />
		</portlet:renderURL>

		<portlet:renderURL var="addWorkflowDefinitionURL">
			<portlet:param name="struts_action" value="/workflow_definitions/edit_workflow_definition" />
			<portlet:param name="tabs1" value="workflow-definitions" />
			<portlet:param name="redirect" value="<%= viewDefinitionsURL %>" />
			<portlet:param name="backURL" value="<%= viewDefinitionsURL %>" />
		</portlet:renderURL>

		<c:if test='<%= DeployManagerUtil.isDeployed("kaleo-designer-portlet") %>'>

			<%
			String taglibHREF = "javascript:Liferay.Util.getOpener()." + renderResponse.getNamespace() + "openKaleoDesigner('', '0', '', Liferay.Util.getWindowName());";
			%>

			<aui:nav-item href="<%= taglibHREF %>" iconCssClass="icon-plus" label='<%= LanguageUtil.format(pageContext, "add-new-x", "definition") %>' />
		</c:if>
		<aui:nav-item href="<%= addWorkflowDefinitionURL %>" iconCssClass="icon-upload" label="upload-definition" selected='<%= toolbarItem.equals("add") %>' />
	</aui:nav>
</aui:nav-bar>

<c:if test='<%= DeployManagerUtil.isDeployed("kaleo-designer-portlet") %>'>
	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />openKaleoDesigner',
			function(workflowDefinitionName, workflowDefinitionVersion, saveCallback, openerWindowName) {
				Liferay.Util.openKaleoDesignerPortlet(
					{
						availablePropertyModels: 'Liferay.KaleoDesigner.AVAILABLE_PROPERTY_MODELS.KALEO_FORMS_EDIT',
						name: workflowDefinitionName,
						openerWindowName: openerWindowName,
						portletResourceNamespace: '<%= renderResponse.getNamespace() %>',
						saveCallback: saveCallback,
						version: workflowDefinitionVersion,
						versionLabel: '<liferay-ui:message key="version" />'
					}
				);
			},
			['aui-base']
		);
	</aui:script>
</c:if>