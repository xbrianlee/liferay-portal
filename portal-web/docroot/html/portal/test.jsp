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

<%@ include file="/html/portal/init.jsp" %>

<%
List<User> users = UserLocalServiceUtil.search(company.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, null, 0, 10, (OrderByComparator)null);

request.setAttribute("users", users);
%>

<display:table name="users">
	<display:column property="userId" title="User ID" />
	<display:column property="emailAddress" title="Email Address" />
</display:table>

<%
System.out.println(request.getClass().getName());
System.out.println("request.getRemoteUser() " + request.getRemoteUser());

PortalServiceUtil.testGetUserId();
%>

Test