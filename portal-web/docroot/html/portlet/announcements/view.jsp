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

<%@ include file="/html/portlet/announcements/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "entries");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/announcements/view");
portletURL.setParameter("tabs1", tabs1);
%>

<c:if test="<%= !portletName.equals(PortletKeys.ALERTS) || (portletName.equals(PortletKeys.ALERTS) && AnnouncementsEntryPermission.contains(permissionChecker, layout, PortletKeys.ALERTS, ActionKeys.ADD_ENTRY)) %>">
	<liferay-util:include page="/html/portlet/announcements/tabs1.jsp" />
</c:if>

<c:choose>
	<c:when test='<%= tabs1.equals("entries") %>'>
		<%@ include file="/html/portlet/announcements/view_entries.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("manage-entries") %>'>
		<%@ include file="/html/portlet/announcements/view_manage_entries.jspf" %>
	</c:when>
</c:choose>