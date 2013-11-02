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

<%@ include file="/html/portlet/document_library/init.jsp" %>

<liferay-ui:header
	backURL="javascript:history.go(-1);"
	title="error"
/>

<liferay-ui:error exception="<%= DuplicateLockException.class %>">

	<%
	Lock lock = (Lock)errorException;
	%>

	<%= LanguageUtil.format(pageContext, "you-cannot-modify-this-document-because-it-was-locked-by-x-on-x", new Object[] {HtmlUtil.escape(PortalUtil.getUserName(lock.getUserId(), String.valueOf(lock.getUserId()))), dateFormatDateTime.format(lock.getCreateDate())}, false) %>
</liferay-ui:error>

<liferay-ui:error exception="<%= InvalidFileVersionException.class %>" message="file-version-is-invalid" />
<liferay-ui:error exception="<%= NoSuchDirectoryException.class %>" message="the-folder-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchFileEntryException.class %>" message="the-document-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchFileException.class %>" message="the-document-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchFolderException.class %>" message="the-folder-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchRepositoryException.class %>" message="the-repository-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchStructureException.class %>" message="the-structure-could-not-be-found" />
<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-do-not-have-the-required-permissions" />