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

<%@ include file="/html/portlet/shopping/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "categories");
%>

<liferay-util:include page="/html/portlet/shopping/tabs1.jsp" />

<c:choose>
	<c:when test='<%= tabs1.equals("categories") %>'>
		<%@ include file="/html/portlet/shopping/categories.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("orders") && !user.isDefaultUser() %>'>
		<%@ include file="/html/portlet/shopping/orders.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("coupons") %>'>
		<%@ include file="/html/portlet/shopping/coupons.jspf" %>
	</c:when>
</c:choose>