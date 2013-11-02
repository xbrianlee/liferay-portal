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

<%@ include file="/html/portlet/user_statistics/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 5, portletURL, null, null);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Map<String, SocialActivityCounter> activityCounters = (Map<String, SocialActivityCounter>)row.getObject();

SocialActivityCounter contributionActivityCounter = activityCounters.get(SocialActivityCounterConstants.NAME_CONTRIBUTION);

if (contributionActivityCounter == null) {
	contributionActivityCounter = new SocialActivityCounterImpl();

	contributionActivityCounter.setName(SocialActivityCounterConstants.NAME_CONTRIBUTION);
}

if (!contributionActivityCounter.isActivePeriod(SocialActivityCounterConstants.PERIOD_LENGTH_SYSTEM)) {
	contributionActivityCounter.setCurrentValue(0);
}

SocialActivityCounter participationActivityCounter = activityCounters.get(SocialActivityCounterConstants.NAME_PARTICIPATION);

if (participationActivityCounter == null) {
	participationActivityCounter = new SocialActivityCounterImpl();

	participationActivityCounter.setName(SocialActivityCounterConstants.NAME_PARTICIPATION);
}

if (!participationActivityCounter.isActivePeriod(SocialActivityCounterConstants.PERIOD_LENGTH_SYSTEM)) {
	participationActivityCounter.setCurrentValue(0);
}

activityCounters.remove(SocialActivityCounterConstants.NAME_CONTRIBUTION);
activityCounters.remove(SocialActivityCounterConstants.NAME_PARTICIPATION);
%>

<liferay-ui:user-display
	userId="<%= GetterUtil.getLong(row.getPrimaryKey()) %>"
	userName=""
>
	<c:if test="<%= userDisplay != null %>">
		<div class="user-rank">
			<span class="statistics-label"><liferay-ui:message key="rank" />:</span> <%= searchContainer.getStart() + row.getPos() + 1 %>
		</div>

		<div class="contribution-score">
			<span class="statistics-label"><liferay-ui:message key='contribution-score' />:</span> <%= contributionActivityCounter.getCurrentValue() %>

			<c:if test="<%= showTotals %>">
				<span>(<liferay-ui:message key="total" />: <%= contributionActivityCounter.getTotalValue() %>)</span>
			</c:if>
		</div>

		<div class="participation-score">
			<span class="statistics-label"><liferay-ui:message key='participation-score' />:</span> <%= participationActivityCounter.getCurrentValue() %>

			<c:if test="<%= showTotals %>">
				<span>(<liferay-ui:message key="total" />: <%= participationActivityCounter.getTotalValue() %>)</span>
			</c:if>
		</div>
	</c:if>
</liferay-ui:user-display>

<c:if test="<%= displayAdditionalActivityCounters %>">
	<div class="separator"><!-- --></div>

	<%
	for (SocialActivityCounter activityCounter : activityCounters.values()) {
		if (!activityCounter.isActivePeriod(SocialActivityCounterConstants.PERIOD_LENGTH_SYSTEM)) {
			activityCounter.setCurrentValue(0);
		}
	%>

		<div class="social-counter-<%= activityCounter.getName() %>">
			<span class="statistics-label"><liferay-ui:message key='<%= "user.statistics." + activityCounter.getName() %>' />:</span> <%= activityCounter.getCurrentValue() %>

			<c:if test="<%= showTotals %>">
				<span>(<liferay-ui:message key="total" />: <%= activityCounter.getTotalValue() %>)</span>
			</c:if>
		</div>

	<%
	}
	%>

</c:if>