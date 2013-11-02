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

<%@ include file="/html/taglib/ui/social_activities/init.jsp" %>

<div class="taglib-social-activities">
	<table>

	<%
	ServiceContext serviceContext = ServiceContextFactory.getInstance(request);

	boolean hasActivities = false;

	Date now = new Date();

	int daysBetween = -1;

	for (SocialActivity activity : activities) {
		SocialActivityFeedEntry activityFeedEntry = SocialActivityInterpreterLocalServiceUtil.interpret(selector, activity, serviceContext);

		if (activityFeedEntry == null) {
			continue;
		}

		if (!hasActivities) {
			hasActivities = true;
		}

		Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), activityFeedEntry.getPortletId());

		int curDaysBetween = DateUtil.getDaysBetween(new Date(activity.getCreateDate()), now, timeZone);
	%>

		<c:if test="<%= curDaysBetween > daysBetween %>">

			<%
			daysBetween = curDaysBetween;
			%>

			<tr>
				<td class="day-separator" colspan="2">
					<c:choose>
						<c:when test="<%= curDaysBetween == 0 %>">
							<liferay-ui:message key="today" />
						</c:when>
						<c:when test="<%= curDaysBetween == 1 %>">
							<liferay-ui:message key="yesterday" />
						</c:when>
						<c:otherwise>
							<%= dateFormatDate.format(activity.getCreateDate()) %>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:if>

		<tr>
			<td class="portlet-icon">
				<liferay-portlet:icon-portlet portlet="<%= portlet %>" />
			</td>
			<td class="activity-data">
				<div class="activity-title">
					<%= activityFeedEntry.getTitle() %>
				</div>
				<div class="activity-body">
					<span class="time"><%= timeFormatDate.format(activity.getCreateDate()) %></span>

					<%= activityFeedEntry.getBody() %>
				</div>
			</td>
		</tr>

	<%
	}
	%>

	</table>

	<c:if test="<%= !hasActivities %>">
		<liferay-ui:message key="there-are-no-recent-activities" />
	</c:if>
</div>

<c:if test="<%= feedEnabled && !activities.isEmpty() %>">
	<div class="separator"><!-- --></div>

	<liferay-ui:rss
		delta="<%= feedDelta %>"
		displayStyle="<%= feedDisplayStyle %>"
		feedType="<%= feedType %>"
		message="<%= feedLinkMessage %>"
		name="<%= feedTitle %>"
		url="<%= feedLink %>"
	/>
</c:if>

<%!
%>