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

<%@ include file="/html/portlet/sites_admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objArray = (Object[])row.getObject();

User user2 = (User)objArray[0];
Group group = (Group)objArray[1];
MembershipRequest membershipRequest = (MembershipRequest)objArray[2];
%>

<liferay-ui:icon-menu>
	<c:if test="<%= (membershipRequest.getStatusId() == MembershipRequestConstants.STATUS_PENDING) && GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.ASSIGN_MEMBERS) %>">
		<portlet:renderURL var="replyRequestURL">
			<portlet:param name="struts_action" value="/sites_admin/reply_membership_request" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="p_u_i_d" value="<%= String.valueOf(user2.getUserId()) %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
			<portlet:param name="membershipRequestId" value="<%= String.valueOf(membershipRequest.getMembershipRequestId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="reply"
			message="reply"
			url="<%= replyRequestURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>