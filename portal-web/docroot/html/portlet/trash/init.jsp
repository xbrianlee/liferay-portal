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

<%@ page import="com.liferay.portal.TrashPermissionException" %><%@
page import="com.liferay.portal.kernel.trash.TrashHandler" %><%@
page import="com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil" %><%@
page import="com.liferay.portal.kernel.trash.TrashRenderer" %><%@
page import="com.liferay.portlet.trash.DuplicateEntryException" %><%@
page import="com.liferay.portlet.trash.model.TrashEntryList" %><%@
page import="com.liferay.portlet.trash.model.impl.TrashEntryImpl" %><%@
page import="com.liferay.portlet.trash.search.EntrySearch" %><%@
page import="com.liferay.portlet.trash.search.EntrySearchTerms" %><%@
page import="com.liferay.portlet.trash.service.TrashEntryLocalServiceUtil" %><%@
page import="com.liferay.portlet.trash.service.TrashEntryServiceUtil" %>

<%@ include file="/html/portlet/trash/init-ext.jsp" %>