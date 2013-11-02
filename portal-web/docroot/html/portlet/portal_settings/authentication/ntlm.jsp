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
boolean ntlmAuthEnabled = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.NTLM_AUTH_ENABLED, PropsValues.NTLM_AUTH_ENABLED);
String ntlmDomainController = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.NTLM_DOMAIN_CONTROLLER, PropsValues.NTLM_DOMAIN_CONTROLLER);
String ntlmDomainControllerName = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.NTLM_DOMAIN_CONTROLLER_NAME, PropsValues.NTLM_DOMAIN_CONTROLLER_NAME);
String ntlmDomain = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.NTLM_DOMAIN, PropsValues.NTLM_DOMAIN);
String ntlmServiceAccount = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.NTLM_SERVICE_ACCOUNT, PropsValues.NTLM_SERVICE_ACCOUNT);
String ntlmServicePassword = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.NTLM_SERVICE_PASSWORD, PropsValues.NTLM_SERVICE_PASSWORD);
%>

<aui:fieldset>
	<aui:input label="enabled" name='<%= "settings--" + PropsKeys.NTLM_AUTH_ENABLED + "--" %>' type="checkbox" value="<%= ntlmAuthEnabled %>" />

	<aui:input cssClass="lfr-input-text-container" label="domain-controller" name='<%= "settings--" + PropsKeys.NTLM_DOMAIN_CONTROLLER + "--" %>' type="text" value="<%= ntlmDomainController %>" />

	<aui:input cssClass="lfr-input-text-container" helpMessage="domain-controller-name-help" label="domain-controller-name" name='<%= "settings--" + PropsKeys.NTLM_DOMAIN_CONTROLLER_NAME + "--" %>' type="text" value="<%= ntlmDomainControllerName %>" />

	<aui:input cssClass="lfr-input-text-container" label="domain" name='<%= "settings--" + PropsKeys.NTLM_DOMAIN + "--" %>' type="text" value="<%= ntlmDomain %>" />

	<aui:input cssClass="lfr-input-text-container" label="service-account" name='<%= "settings--" + PropsKeys.NTLM_SERVICE_ACCOUNT + "--" %>' type="text" value="<%= ntlmServiceAccount %>" />

	<aui:input cssClass="lfr-input-text-container" label="service-password" name='<%= "settings--" + PropsKeys.NTLM_SERVICE_PASSWORD + "--" %>' type="text" value="<%= ntlmServicePassword %>" />
</aui:fieldset>