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
long groupId = ParamUtil.getLong(request, "groupId");

String destinationName = ParamUtil.getString(request, "destinationName");

SearchContainer searchContainer = new SearchContainer();

List<String> headerNames = new ArrayList<String>();

headerNames.add("title");
headerNames.add("start-date");
headerNames.add("end-date");
headerNames.add(StringPool.BLANK);

searchContainer.setHeaderNames(headerNames);
searchContainer.setEmptyResultsMessage("there-are-no-scheduled-publication-processes");

List<SchedulerResponse> scheduledJobs = SchedulerEngineHelperUtil.getScheduledJobs(StagingUtil.getSchedulerGroupName(destinationName, groupId), StorageType.PERSISTED);

List resultRows = searchContainer.getResultRows();

for (int i = 0; i < scheduledJobs.size(); i++) {
	SchedulerResponse schedulerResponse = scheduledJobs.get(i);

	ResultRow row = new ResultRow(schedulerResponse, schedulerResponse.getJobName(), i);

	// Title

	row.addText(schedulerResponse.getDescription());

	// Start date

	row.addDate(SchedulerEngineHelperUtil.getStartTime(schedulerResponse));

	// End date

	Date endDate = SchedulerEngineHelperUtil.getEndTime(schedulerResponse);

	if (endDate != null) {
		row.addDate(endDate);
	}
	else {
		row.addText(LanguageUtil.get(pageContext, "no-end-date"));
	}

	// Action

	StringBundler sb = new StringBundler(4);

	sb.append(portletDisplay.getNamespace());
	sb.append("unschedulePublishEvent('");
	sb.append(schedulerResponse.getJobName());
	sb.append("');");

	row.addButton("right", SearchEntry.DEFAULT_VALIGN, LanguageUtil.get(pageContext, "delete"), sb.toString());

	resultRows.add(row);
}
%>

<liferay-ui:search-iterator paginate="<%= false %>" searchContainer="<%= searchContainer %>" />