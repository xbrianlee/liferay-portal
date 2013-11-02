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
boolean validate = ParamUtil.getBoolean(request, "validate", true);

String[] tempFileEntryNames = LayoutServiceUtil.getTempFileEntryNames(scopeGroupId, ExportImportHelper.TEMP_FOLDER_NAME + portletDisplay.getId());
%>

<liferay-ui:tabs
	names="new-import-process,current-and-previous"
	param="tabs3"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<div id="<portlet:namespace />exportImportOptions">

			<%
			int incompleteBackgroundTaskCount = BackgroundTaskLocalServiceUtil.getBackgroundTasksCount(themeDisplay.getScopeGroupId(), selPortlet.getPortletId(), PortletImportBackgroundTaskExecutor.class.getName(), false);
			%>

			<div class="<%= (incompleteBackgroundTaskCount == 0) ? "hide" : "in-progress" %>" id="<portlet:namespace />incompleteProcessMessage">
				<liferay-util:include page="/html/portlet/layouts_admin/incomplete_processes_message.jsp">
					<liferay-util:param name="incompleteBackgroundTaskCount" value="<%= String.valueOf(incompleteBackgroundTaskCount) %>" />
				</liferay-util:include>
			</div>

			<c:choose>
				<c:when test="<%= (tempFileEntryNames.length > 0) && !validate %>">
					<liferay-util:include page="/html/portlet/portlet_configuration/import_portlet_resources.jsp" />
				</c:when>
				<c:otherwise>
					<liferay-util:include page="/html/portlet/portlet_configuration/import_portlet_validation.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
	</liferay-ui:section>

	<liferay-ui:section>
		<div class="process-list" id="<portlet:namespace />importProcesses">
			<liferay-util:include page="/html/portlet/portlet_configuration/import_portlet_processes.jsp" />
		</div>
	</liferay-ui:section>
</liferay-ui:tabs>

<aui:script use="liferay-export-import">
	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" var="importProcessesURL">
		<portlet:param name="struts_action" value="/portlet_configuration/export_import" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.IMPORT %>" />
		<portlet:param name="<%= SearchContainer.DEFAULT_CUR_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_CUR_PARAM) %>" />
		<portlet:param name="<%= SearchContainer.DEFAULT_DELTA_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_DELTA_PARAM) %>" />
		<portlet:param name="groupId" value="<%= String.valueOf(themeDisplay.getScopeGroupId()) %>" />
		<portlet:param name="portletResource" value="<%= portletResource %>" />
	</liferay-portlet:resourceURL>

	new Liferay.ExportImport(
		{
			form: document.<portlet:namespace />fm1,
			incompleteProcessMessageNode: '#<portlet:namespace />incompleteProcessMessage',
			namespace: '<portlet:namespace />',
			processesNode: '#importProcesses',
			processesResourceURL: '<%= importProcessesURL.toString() %>'
		}
	);
</aui:script>