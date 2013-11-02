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
String googleAppsUsername = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.GOOGLE_APPS_USERNAME);

String googleAppsPassword = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.GOOGLE_APPS_PASSWORD);

if (Validator.isNotNull(googleAppsPassword)) {
	googleAppsPassword = Portal.TEMP_OBFUSCATION_VALUE;
}
%>

<h3><liferay-ui:message key="google-apps" /></h3>

<aui:fieldset>
	<aui:input autocomplete="off" cssClass="lfr-input-text-container" label="user-name" name='<%= "settings--" + PropsKeys.GOOGLE_APPS_USERNAME + "--" %>' type="text" value="<%= googleAppsUsername %>" />

	<aui:input autocomplete="off" cssClass="lfr-input-text-container" label="password" name='<%= "settings--" + PropsKeys.GOOGLE_APPS_PASSWORD + "--" %>' type="password" value="<%= googleAppsPassword %>" />
</aui:fieldset>