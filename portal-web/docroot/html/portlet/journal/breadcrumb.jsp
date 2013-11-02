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

<%@ include file="/html/portlet/journal/init.jsp" %>

<%
JournalFolder folder = (JournalFolder)request.getAttribute("view.jsp-folder");

JournalUtil.addPortletBreadcrumbEntries(folder, request, liferayPortletResponse);
%>

<liferay-ui:breadcrumb
	showCurrentGroup="<%= false %>"
	showCurrentPortlet="<%= false %>"
	showGuestGroup="<%= false %>"
	showLayout="<%= false %>"
	showParentGroups="<%= false %>"
/>