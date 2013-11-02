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

<%@ include file="/html/portlet/roles_admin/init.jsp" %>

<%
String cmd = ParamUtil.getString(request, Constants.CMD);

Role role = (Role)request.getAttribute(WebKeys.ROLE);

String portletResource = ParamUtil.getString(request, "portletResource");

request.setAttribute("edit_role_permissions.jsp-role", role);
request.setAttribute("edit_role_permissions.jsp-portletResource", portletResource);
%>

<c:choose>
	<c:when test="<%= cmd.equals(Constants.EDIT) %>">
		<liferay-util:include page="/html/portlet/roles_admin/edit_role_permissions_form.jsp" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portlet/roles_admin/edit_role_permissions_summary.jsp" />
	</c:otherwise>
</c:choose>