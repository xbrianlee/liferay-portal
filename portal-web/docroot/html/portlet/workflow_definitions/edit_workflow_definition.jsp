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
String redirect = ParamUtil.getString(request, "redirect");

WorkflowDefinition workflowDefinition = (WorkflowDefinition)request.getAttribute(WebKeys.WORKFLOW_DEFINITION);

String name = StringPool.BLANK;
String version = StringPool.BLANK;

if (workflowDefinition != null) {
	name = workflowDefinition.getName();
	version = String.valueOf(workflowDefinition.getVersion());
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/workflow_definitions/view");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	localizeTitle="<%= (workflowDefinition == null) %>"
	title='<%= (workflowDefinition == null) ? "upload-definition" : workflowDefinition.getName() %>'
/>

<portlet:actionURL var="editWorkflowDefinitionURL">
	<portlet:param name="struts_action" value="/workflow_definitions/edit_workflow_definition" />
</portlet:actionURL>

<aui:form action="<%= editWorkflowDefinitionURL %>" enctype="multipart/form-data" method="post">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (workflowDefinition == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="name" type="hidden" value="<%= name %>" />
	<aui:input name="version" type="hidden" value="<%= version %>" />

	<liferay-ui:error exception="<%= WorkflowDefinitionFileException.class %>" message="please-enter-a-valid-file" />

	<aui:fieldset>
		<aui:field-wrapper label="title">
			<liferay-ui:input-localized name="title" xml='<%= BeanPropertiesUtil.getString(workflowDefinition, "title") %>' />
		</aui:field-wrapper>

		<aui:input name="file" type="file" />

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<%
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, (workflowDefinition == null) ? "upload-definition" : workflowDefinition.getName()), currentURL);
%>