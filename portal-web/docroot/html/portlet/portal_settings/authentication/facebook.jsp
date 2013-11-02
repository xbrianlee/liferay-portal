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
boolean facebookConnectAuthEnabled = FacebookConnectUtil.isEnabled(company.getCompanyId());
boolean facebookConnectVerifiedAccountRequired = FacebookConnectUtil.isVerifiedAccountRequired(company.getCompanyId());
String facebookConnectAppId = FacebookConnectUtil.getAppId(company.getCompanyId());
String facebookConnectAppSecret = FacebookConnectUtil.getAppSecret(company.getCompanyId());
String facebookConnectGraphURL = FacebookConnectUtil.getGraphURL(company.getCompanyId());
String facebookConnectOauthAuthURL = FacebookConnectUtil.getAuthURL(company.getCompanyId());
String facebookConnectOauthTokenURL = FacebookConnectUtil.getAccessTokenURL(company.getCompanyId());
String facebookConnectRedirectURL = FacebookConnectUtil.getRedirectURL(company.getCompanyId());
%>

<aui:fieldset>
	<aui:input label="enabled" name='<%= "settings--" + PropsKeys.FACEBOOK_CONNECT_AUTH_ENABLED + "--" %>' type="checkbox" value="<%= facebookConnectAuthEnabled %>" />

	<aui:input label="verified-account-required" name='<%= "settings--" + PropsKeys.FACEBOOK_CONNECT_VERIFIED_ACCOUNT_REQUIRED + "--" %>' type="checkbox" value="<%= facebookConnectVerifiedAccountRequired %>" />

	<aui:input cssClass="lfr-input-text-container" label="application-id" name='<%= "settings--" + PropsKeys.FACEBOOK_CONNECT_APP_ID + "--" %>' type="text" value="<%= facebookConnectAppId %>" />

	<aui:input cssClass="lfr-input-text-container" label="application-secret" name='<%= "settings--" + PropsKeys.FACEBOOK_CONNECT_APP_SECRET + "--" %>' type="text" value="<%= facebookConnectAppSecret %>" />

	<aui:input cssClass="lfr-input-text-container" label="graph-url" name='<%= "settings--" + PropsKeys.FACEBOOK_CONNECT_GRAPH_URL + "--" %>' type="text" value="<%= facebookConnectGraphURL %>" />

	<aui:input cssClass="lfr-input-text-container" label="oauth-authentication-url" name='<%= "settings--" + PropsKeys.FACEBOOK_CONNECT_OAUTH_AUTH_URL + "--" %>' type="text" value="<%= facebookConnectOauthAuthURL %>" />

	<aui:input cssClass="lfr-input-text-container" label="oauth-token-url" name='<%= "settings--" + PropsKeys.FACEBOOK_CONNECT_OAUTH_TOKEN_URL + "--" %>' type="text" value="<%= facebookConnectOauthTokenURL %>" />

	<aui:input cssClass="lfr-input-text-container" label="redirect-url" name='<%= "settings--" + PropsKeys.FACEBOOK_CONNECT_OAUTH_REDIRECT_URL + "--" %>' type="text" value="<%= facebookConnectRedirectURL %>" />
</aui:fieldset>