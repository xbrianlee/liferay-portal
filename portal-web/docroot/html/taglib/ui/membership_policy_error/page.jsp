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

<%@ include file="/html/taglib/ui/membership_policy_error/init.jsp" %>

<liferay-ui:error exception="<%= MembershipPolicyException.class %>">

	<%
	MembershipPolicyException mpe = (MembershipPolicyException)errorException;

	List<User> users = mpe.getUsers();
	%>

	<c:choose>
		<c:when test="<%= mpe.getType() == MembershipPolicyException.ORGANIZATION_MEMBERSHIP_NOT_ALLOWED %>">
			<liferay-ui:message arguments='<%= new Object[] {ListUtil.toString(users, "fullName", StringPool.COMMA_AND_SPACE), ListUtil.toString(mpe.getOrganizations(), "name", StringPool.COMMA_AND_SPACE)} %>' key='<%= users.size() == 1 ? "x-is-not-allowed-to-join-x" : "the-following-users-are-not-allowed-to-join-x-x" %>' />
		</c:when>
		<c:when test="<%= mpe.getType() == MembershipPolicyException.ORGANIZATION_MEMBERSHIP_REQUIRED %>">
			<liferay-ui:message arguments='<%= new Object[] {ListUtil.toString(users, "fullName", StringPool.COMMA_AND_SPACE), ListUtil.toString(mpe.getOrganizations(), "name", StringPool.COMMA_AND_SPACE)} %>' key='<%= users.size() == 1 ? "x-is-not-allowed-to-leave-x" : "the-following-users-are-not-allowed-to-leave-x-x" %>' />
		</c:when>
		<c:when test="<%= mpe.getType() == MembershipPolicyException.ROLE_MEMBERSHIP_NOT_ALLOWED %>">
			<liferay-ui:message arguments='<%= new Object[] {ListUtil.toString(users, "fullName", StringPool.COMMA_AND_SPACE), ListUtil.toString(mpe.getRoles(), "title", StringPool.COMMA_AND_SPACE)} %>' key='<%= users.size() == 1 ? "x-cannot-be-assigned-to-x" : "the-following-users-cannot-be-assigned-to-x-x" %>' />
		</c:when>
		<c:when test="<%= mpe.getType() == MembershipPolicyException.ROLE_MEMBERSHIP_REQUIRED %>">
			<liferay-ui:message arguments='<%= new Object[] {ListUtil.toString(users, "fullName", StringPool.COMMA_AND_SPACE), ListUtil.toString(mpe.getRoles(), "title", StringPool.COMMA_AND_SPACE)} %>' key='<%= users.size() == 1 ? "x-cannot-be-unassigned-from-x" : "the-following-users-cannot-be-unassigned-from-x-x" %>' />
		</c:when>
		<c:when test="<%= mpe.getType() == MembershipPolicyException.SITE_MEMBERSHIP_NOT_ALLOWED %>">
			<liferay-ui:message arguments='<%= new Object[] {ListUtil.toString(users, "fullName", StringPool.COMMA_AND_SPACE), ListUtil.toString(mpe.getGroups(), "descriptiveName", StringPool.COMMA_AND_SPACE)} %>' key='<%= users.size() == 1 ? "x-is-not-allowed-to-join-x" : "the-following-users-are-not-allowed-to-join-x-x" %>' />
		</c:when>
		<c:when test="<%= mpe.getType() == MembershipPolicyException.SITE_MEMBERSHIP_REQUIRED %>">
			<liferay-ui:message arguments='<%= new Object[] {ListUtil.toString(users, "fullName", StringPool.COMMA_AND_SPACE), ListUtil.toString(mpe.getGroups(), "descriptiveName", StringPool.COMMA_AND_SPACE)} %>' key='<%= users.size() == 1 ? "x-is-not-allowed-to-leave-x" : "the-following-users-are-not-allowed-to-leave-x-x" %>' />
		</c:when>
		<c:when test="<%= mpe.getType() == MembershipPolicyException.USER_GROUP_MEMBERSHIP_NOT_ALLOWED %>">
			<liferay-ui:message arguments='<%= new Object[] {ListUtil.toString(users, "fullName", StringPool.COMMA_AND_SPACE), ListUtil.toString(mpe.getUserGroups(), "name", StringPool.COMMA_AND_SPACE)} %>' key='<%= users.size() == 1 ? "x-are-not-allowed-to-join-x" : "the-following-users-are-not-allowed-to-join-x-x" %>' />
		</c:when>
		<c:when test="<%= mpe.getType() == MembershipPolicyException.USER_GROUP_MEMBERSHIP_REQUIRED %>">
			<liferay-ui:message arguments='<%= new Object[] {ListUtil.toString(users, "fullName", StringPool.COMMA_AND_SPACE), ListUtil.toString(mpe.getUserGroups(), "name", StringPool.COMMA_AND_SPACE)} %>' key='<%= users.size() == 1 ? "x-are-not-allowed-to-leave-x" : "the-following-users-are-not-allowed-to-leave-x-x" %>' />
		</c:when>
	</c:choose>
</liferay-ui:error>