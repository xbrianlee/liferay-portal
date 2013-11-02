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
WorkflowTaskDisplayTerms displayTerms = new WorkflowTaskDisplayTerms(renderRequest);
%>

<liferay-ui:search-toggle
	autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>"
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_workflow_task_search"
>

	<aui:fieldset>
		<aui:input label="task" name="<%= displayTerms.NAME %>" size="20" value="<%= displayTerms.getName() %>" />

		<aui:select name="<%= displayTerms.TYPE %>">

			<%
			String displayTermsType = displayTerms.getType();

			List<WorkflowHandler> workflowHandlers = WorkflowHandlerRegistryUtil.getWorkflowHandlers();

			for (WorkflowHandler workflowHandler : workflowHandlers) {
				if (!workflowHandler.isAssetTypeSearchable()) {
					continue;
				}

				String defaultWorkflowHandlerType = workflowHandler.getClassName();
			%>

				<aui:option label="<%= workflowHandler.getType(locale) %>" selected="<%= displayTermsType.equals(defaultWorkflowHandlerType) %>" value="<%= defaultWorkflowHandlerType %>" />

			<%
			}
			%>

		</aui:select>
	</aui:fieldset>
</liferay-ui:search-toggle>