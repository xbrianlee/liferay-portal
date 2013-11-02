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

<%@ include file="init.jsp" %>

<%@ page import="com.liferay.portal.model.Portlet" %>
<%@ page import="com.liferay.portal.service.PortletLocalServiceUtil" %>

<%@ page import="java.util.Set" %>

<%
Portlet portlet = PortletLocalServiceUtil.getPortletById(portletDisplay.getId());

Set<String> paths = application.getResourcePaths("/WEB-INF/jsp/" + portlet.getFriendlyURLMapping() + "/controllers/");

for (String path : paths) {
	int x = path.lastIndexOf("/");
	int y = path.indexOf("_controller.jsp");

	if (y == -1) {
		continue;
	}

	String controller = path.substring(x + 1, y);
%>

	<portlet:resourceURL var="resourceURL">
		<portlet:param name="controller" value="<%= controller %>" />
		<portlet:param name="action" value="touch" />
	</portlet:resourceURL>

	<iframe height="0" src="<%= resourceURL %>" style="display: none; visibility: hidden;" width="0"></iframe>

<%
}
%>