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

<%@ include file="/html/portlet/page_comments/init.jsp" %>

<c:if test="<%= LayoutPermissionUtil.contains(permissionChecker, layout, ActionKeys.VIEW) %>">
	<portlet:actionURL var="discussionURL">
		<portlet:param name="struts_action" value="/page_comments/edit_page_discussion" />
	</portlet:actionURL>

	<liferay-ui:discussion
		className="<%= Layout.class.getName() %>"
		classPK="<%= layout.getPlid() %>"
		formAction="<%= discussionURL %>"
		formName="fm"
		redirect="<%= currentURL %>"
		userId="<%= user.getUserId() %>"
	/>
</c:if>