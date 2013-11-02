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

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%
LayoutSet selLayoutSet = ((LayoutSet)request.getAttribute("edit_pages.jsp-selLayoutSet"));

long groupId = selLayoutSet.getGroupId();
String className = LayoutSet.class.getName();
long classPK = selLayoutSet.getLayoutSetId();
%>

<%@ include file="/html/portlet/layouts_admin/layout/mobile_device_rules_header.jspf" %>

<%@ include file="/html/portlet/layouts_admin/layout/mobile_device_rules_toolbar.jspf" %>

<%@ include file="/html/portlet/layouts_admin/layout/mobile_device_rules_rule_group_instances.jspf" %>