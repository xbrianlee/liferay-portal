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

PortletURL tabs1URL = renderResponse.createRenderURL();

tabs1URL.setParameter("struts_action", "/announcements/view");
tabs1URL.setParameter("tabs1", tabs1);

String tabs1Names = "entries";

if (AnnouncementsEntryPermission.contains(permissionChecker, layout, portletName, ActionKeys.ADD_ENTRY)) {
	tabs1Names += ",manage-entries";
}
%>

<liferay-ui:tabs
	names="<%= tabs1Names %>"
	url="<%= tabs1URL.toString() %>"
/>