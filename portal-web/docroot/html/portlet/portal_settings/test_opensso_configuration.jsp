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
String openSsoLoginURL = ParamUtil.getString(request, "openSsoLoginURL");
String openSsoLogoutURL = ParamUtil.getString(request, "openSsoLogoutURL");
String openSsoServiceURL = ParamUtil.getString(request, "openSsoServiceURL");
String openSsoScreenNameAttr = ParamUtil.getString(request, "openSsoScreenNameAttr");
String openSsoEmailAddressAttr = ParamUtil.getString(request, "openSsoEmailAddressAttr");
String openSsoFirstNameAttr = ParamUtil.getString(request, "openSsoFirstNameAttr");
String openSsoLastNameAttr = ParamUtil.getString(request, "openSsoLastNameAttr");

List<String> urls = new ArrayList<String>();

urls.add(openSsoLoginURL);
urls.add(openSsoLogoutURL);
urls.add(openSsoServiceURL);
%>

<c:choose>
	<c:when test="<%= !OpenSSOUtil.isValidUrls(urls.toArray(new String[urls.size()])) %>">
		<liferay-ui:message key="liferay-has-failed-to-connect-to-the-opensso-server" />
	</c:when>
	<c:when test="<%= !OpenSSOUtil.isValidServiceUrl(openSsoServiceURL) %>">
		<liferay-ui:message key="liferay-has-failed-to-connect-to-the-opensso-services" />
	</c:when>
	<c:when test="<%= Validator.isNull(openSsoScreenNameAttr) || Validator.isNull(openSsoEmailAddressAttr) || Validator.isNull(openSsoFirstNameAttr) || Validator.isNull(openSsoLastNameAttr) %>">
		<liferay-ui:message key="please-map-each-of-the-user-properties-screen-name,-email-address,-first-name,-and-last-name-to-an-opensso-attribute" />
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="liferay-has-successfully-connected-to-the-opensso-server" />
	</c:otherwise>
</c:choose>