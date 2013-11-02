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

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

BackgroundTask backgroundTask = (BackgroundTask)row.getObject();
%>

<strong class="label background-task-status-<%= BackgroundTaskConstants.getStatusLabel(backgroundTask.getStatus()) %> <%= BackgroundTaskConstants.getStatusCssClass(backgroundTask.getStatus()) %>">
	<liferay-ui:message key="<%= backgroundTask.getStatusLabel() %>" />
</strong>

<c:if test="<%= backgroundTask.isInProgress() %>">

	<%
	BackgroundTaskStatus backgroundTaskStatus = BackgroundTaskStatusRegistryUtil.getBackgroundTaskStatus(backgroundTask.getBackgroundTaskId());
	%>

	<c:if test="<%= backgroundTaskStatus != null %>">

		<%
		double percentage = 100;

		long allModelAdditionCountersTotal = GetterUtil.getLong(backgroundTaskStatus.getAttribute("allModelAdditionCountersTotal"));
		long currentModelAdditionCountersTotal = GetterUtil.getLong(backgroundTaskStatus.getAttribute("currentModelAdditionCountersTotal"));

		if (allModelAdditionCountersTotal > 0) {
			percentage = Math.round((double)currentModelAdditionCountersTotal / allModelAdditionCountersTotal * 100);
		}
		%>

		<div class="progress progress-striped active">
			<div class="bar" style="width: <%= percentage %>%;">
				<c:if test="<%= allModelAdditionCountersTotal > 0 %>">
					<%= currentModelAdditionCountersTotal %> / <%= allModelAdditionCountersTotal %>
				</c:if>
			</div>
		</div>

		<%
		String stagedModelName = (String)backgroundTaskStatus.getAttribute("stagedModelName");
		String stagedModelType = (String)backgroundTaskStatus.getAttribute("stagedModelType");
		%>

		<c:if test="<%= Validator.isNotNull(stagedModelName) && Validator.isNotNull(stagedModelType) %>">

			<%
			String messageKey = "exporting";

			Map<String, Serializable> taskContextMap = backgroundTask.getTaskContextMap();

			String cmd = (String)taskContextMap.get(Constants.CMD);

			if (Validator.equals(cmd, Constants.IMPORT)) {
				messageKey = "importing";
			}
			else if (Validator.equals(cmd, Constants.PUBLISH)) {
				messageKey = "publishing";
			}
			%>

			<div class="progress-current-item">
				<strong><liferay-ui:message key="<%= messageKey %>" /><%= StringPool.TRIPLE_PERIOD %></strong> <%= ResourceActionsUtil.getModelResource(locale, stagedModelType) %> <em><%= stagedModelName %></em>
			</div>
		</c:if>
	</c:if>
</c:if>

<c:if test="<%= Validator.isNotNull(backgroundTask.getStatusMessage()) %>">

	<%
	long[] expandedBackgroundTaskIds = StringUtil.split(GetterUtil.getString(SessionClicks.get(request, "background-task-ids", null)), 0L);
	%>

	<a class="details-link toggler-header-<%= ArrayUtil.contains(expandedBackgroundTaskIds, backgroundTask.getBackgroundTaskId()) ? "expanded" : "collapsed" %>" data-persist-id="<%= backgroundTask.getBackgroundTaskId() %>" href="#"><liferay-ui:message key="details" /></a>

	<div class="background-task-status-message toggler-content-<%= ArrayUtil.contains(expandedBackgroundTaskIds, backgroundTask.getBackgroundTaskId()) ? "expanded" : "collapsed" %>">
		<liferay-util:include page="/html/portlet/layouts_admin/publish_process_message_task_details.jsp">
			<liferay-util:param name="backgroundTaskId" value="<%= String.valueOf(backgroundTask.getBackgroundTaskId()) %>" />
		</liferay-util:include>
	</div>
</c:if>