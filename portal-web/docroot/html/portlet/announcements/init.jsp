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

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portal.service.permission.OrganizationPermissionUtil" %><%@
page import="com.liferay.portal.service.permission.UserGroupPermissionUtil" %><%@
page import="com.liferay.portlet.announcements.EntryContentException" %><%@
page import="com.liferay.portlet.announcements.EntryDisplayDateException" %><%@
page import="com.liferay.portlet.announcements.EntryExpirationDateException" %><%@
page import="com.liferay.portlet.announcements.EntryTitleException" %><%@
page import="com.liferay.portlet.announcements.EntryURLException" %><%@
page import="com.liferay.portlet.announcements.NoSuchEntryException" %><%@
page import="com.liferay.portlet.announcements.NoSuchFlagException" %><%@
page import="com.liferay.portlet.announcements.model.AnnouncementsEntry" %><%@
page import="com.liferay.portlet.announcements.model.AnnouncementsEntryConstants" %><%@
page import="com.liferay.portlet.announcements.model.AnnouncementsFlagConstants" %><%@
page import="com.liferay.portlet.announcements.service.AnnouncementsEntryLocalServiceUtil" %><%@
page import="com.liferay.portlet.announcements.service.AnnouncementsFlagLocalServiceUtil" %><%@
page import="com.liferay.portlet.announcements.service.permission.AnnouncementsEntryPermission" %><%@
page import="com.liferay.portlet.announcements.util.AnnouncementsUtil" %>

<%
int pageDelta = GetterUtil.getInteger(portletPreferences.getValue("pageDelta", String.valueOf(SearchContainer.DEFAULT_DELTA)));
%>

<%@ include file="/html/portlet/announcements/init-ext.jsp" %>