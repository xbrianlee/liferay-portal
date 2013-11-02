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

<%@ include file="/html/taglib/init.jsp" %>

<aui:fieldset>

	<%
	Calendar cal = CalendarFactoryUtil.getCalendar(timeZone, locale);

	int hour = cal.get(Calendar.HOUR_OF_DAY);

	if (DateUtil.isFormatAmPm(locale)) {
		hour = cal.get(Calendar.HOUR);
	}
	%>

	<aui:field-wrapper label="start-date">
		<div class="field-row">
			<liferay-ui:input-date
				dayParam="schedulerStartDateDay"
				dayValue="<%= cal.get(Calendar.DATE) %>"
				disabled="<%= false %>"
				firstDayOfWeek="<%= cal.getFirstDayOfWeek() - 1 %>"
				monthParam="schedulerStartDateMonth"
				monthValue="<%= cal.get(Calendar.MONTH) %>"
				yearParam="schedulerStartDateYear"
				yearValue="<%= cal.get(Calendar.YEAR) %>"
			/>

			&nbsp;

			<liferay-ui:input-time
				amPmParam="schedulerStartDateAmPm"
				amPmValue="<%= cal.get(Calendar.AM_PM) %>"
				hourParam="schedulerStartDateHour"
				hourValue="<%= hour %>"
				minuteParam="schedulerStartDateMinute"
				minuteValue="<%= cal.get(Calendar.MINUTE) %>"
			/>
		</div>
	</aui:field-wrapper>

	<aui:field-wrapper label="end-date">
		<aui:input checked="<%= true %>" id="schedulerNoEndDate" label="no-end-date" name="endDateType" type="radio" value="0" />
		<aui:input first="<%= true %>" id="schedulerEndBy" label="end-by" name="endDateType" type="radio" value="1" />

		<div class="field-row hide" id="<portlet:namespace />schedulerEndDateType">
			<liferay-ui:input-date
				dayParam="schedulerEndDateDay"
				dayValue="<%= cal.get(Calendar.DATE) %>"
				disabled="<%= false %>"
				firstDayOfWeek="<%= cal.getFirstDayOfWeek() - 1 %>"
				monthParam="schedulerEndDateMonth"
				monthValue="<%= cal.get(Calendar.MONTH) %>"
				yearParam="schedulerEndDateYear"
				yearValue="<%= cal.get(Calendar.YEAR) %>"
			/>

			&nbsp;

			<liferay-ui:input-time
				amPmParam="schedulerEndDateAmPm"
				amPmValue="<%= cal.get(Calendar.AM_PM) %>"
				hourParam="schedulerEndDateHour"
				hourValue="<%= hour %>"
				minuteParam="schedulerEndDateMinute"
				minuteValue="<%= cal.get(Calendar.MINUTE) %>"
			/>
		</div>
	</aui:field-wrapper>
</aui:fieldset>

<liferay-ui:input-repeat />

<aui:script>
	function <portlet:namespace />showTable(id) {
		document.getElementById("<portlet:namespace />neverTable").style.display = "none";
		document.getElementById("<portlet:namespace />dailyTable").style.display = "none";
		document.getElementById("<portlet:namespace />weeklyTable").style.display = "none";
		document.getElementById("<portlet:namespace />monthlyTable").style.display = "none";
		document.getElementById("<portlet:namespace />yearlyTable").style.display = "none";

		document.getElementById(id).style.display = "block";
	}

	Liferay.Util.toggleRadio('<portlet:namespace />schedulerEndBy', '<portlet:namespace />schedulerEndDateType');
	Liferay.Util.toggleRadio('<portlet:namespace />schedulerNoEndDate', '', ['<portlet:namespace />schedulerEndDateType']);
</aui:script>