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

<%@ include file="/html/portlet/directory/init.jsp" %>

<%
User selUser = (User)request.getAttribute("user.selUser");
%>

<c:if test="<%= Validator.isNotNull(selUser.getComments()) %>">
	<h3><liferay-ui:message key="comments" /></h3>

	<%= selUser.getComments() %>
</c:if>