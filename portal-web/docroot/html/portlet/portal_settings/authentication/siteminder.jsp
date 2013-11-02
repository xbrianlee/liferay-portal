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

<%@ include file="/html/portlet/portal_settings/init.jsp" %>

<%
boolean siteminderAuthEnabled = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.SITEMINDER_AUTH_ENABLED, PropsValues.SITEMINDER_AUTH_ENABLED);
boolean siteminderImportFromLdap = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.SITEMINDER_IMPORT_FROM_LDAP, PropsValues.SITEMINDER_IMPORT_FROM_LDAP);
String siteminderUserHeader = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.SITEMINDER_USER_HEADER, PropsValues.SITEMINDER_USER_HEADER);
%>

<aui:fieldset>
	<aui:input label="enabled" name='<%= "settings--" + PropsKeys.SITEMINDER_AUTH_ENABLED + "--" %>' type="checkbox" value="<%= siteminderAuthEnabled %>" />

	<aui:input helpMessage="import-siteminder-users-from-ldap-help" label="import-siteminder-users-from-ldap" name='<%= "settings--" + PropsKeys.SITEMINDER_IMPORT_FROM_LDAP + "--" %>' type="checkbox" value="<%= siteminderImportFromLdap %>" />

	<aui:input cssClass="lfr-input-text-container" label="user-header" name='<%= "settings--" + PropsKeys.SITEMINDER_USER_HEADER + "--" %>' type="text" value="<%= siteminderUserHeader %>" />
</aui:fieldset>