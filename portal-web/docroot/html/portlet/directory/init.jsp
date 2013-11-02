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

<%@ page import="com.liferay.portal.NoSuchOrganizationException" %><%@
page import="com.liferay.portal.NoSuchUserGroupException" %><%@
page import="com.liferay.portlet.social.model.SocialRelationConstants" %><%@
page import="com.liferay.portlet.usersadmin.search.OrganizationDisplayTerms" %><%@
page import="com.liferay.portlet.usersadmin.search.UserDisplayTerms" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "users");

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale);
%>

<%@ include file="/html/portlet/directory/init-ext.jsp" %>