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

<%@ include file="/html/portlet/journal/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

JournalFeed feed = (JournalFeed)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= JournalFeedPermission.contains(permissionChecker, feed, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editeFeedURL">
			<portlet:param name="struts_action" value="/journal/edit_feed" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(feed.getGroupId()) %>" />
			<portlet:param name="feedId" value="<%= feed.getFeedId() %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" url="<%= editeFeedURL %>" />
	</c:if>

	<c:if test="<%= JournalFeedPermission.contains(permissionChecker, feed, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= JournalFeed.class.getName() %>"
			modelResourceDescription="<%= feed.getName() %>"
			resourcePrimKey="<%= String.valueOf(feed.getId()) %>"
			var="permissionsFeedURL"
		/>

		<liferay-ui:icon image="permissions" url="<%= permissionsFeedURL %>" />
	</c:if>

	<c:if test="<%= JournalFeedPermission.contains(permissionChecker, feed, ActionKeys.DELETE) %>">
		<portlet:actionURL var="deleteFeedURL">
			<portlet:param name="struts_action" value="/journal/edit_feed" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(feed.getGroupId()) %>" />
			<portlet:param name="deleteFeedIds" value="<%= feed.getFeedId() %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteFeedURL %>" />
	</c:if>
</liferay-ui:icon-menu>