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

<%@ page import="com.liferay.portlet.expando.ColumnNameException" %><%@
page import="com.liferay.portlet.expando.ColumnTypeException" %><%@
page import="com.liferay.portlet.expando.DuplicateColumnNameException" %><%@
page import="com.liferay.portlet.expando.NoSuchColumnException" %><%@
page import="com.liferay.portlet.expando.ValueDataException" %><%@
page import="com.liferay.portlet.expando.model.CustomAttributesDisplay" %><%@
page import="com.liferay.portlet.expando.model.ExpandoColumn" %><%@
page import="com.liferay.portlet.expando.model.ExpandoColumnConstants" %><%@
page import="com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil" %><%@
page import="com.liferay.portlet.expando.service.permission.ExpandoColumnPermissionUtil" %><%@
page import="com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil" %><%@
page import="com.liferay.portlet.expando.util.comparator.CustomAttributesDisplayComparator" %>

<%@ include file="/html/portlet/expando/init-ext.jsp" %>