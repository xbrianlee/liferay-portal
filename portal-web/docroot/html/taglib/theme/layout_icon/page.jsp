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

<%@ include file="/html/taglib/init.jsp" %>

<%
Layout selLayout = (Layout)request.getAttribute("liferay-theme:layout-icon:layout");
%>

<c:if test="<%= (selLayout != null) && selLayout.isIconImage() %>">
	<img alt="<liferay-ui:message key="page-icon" />" src="<%= themeDisplay.getPathImage() %>/layout_icon?img_id=<%= selLayout.getIconImageId() %>&t=<%= WebServerServletTokenUtil.getToken(selLayout.getIconImageId()) %>" />
</c:if>