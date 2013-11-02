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

<%@ page import="com.liferay.portal.AddressCityException" %><%@
page import="com.liferay.portal.AddressStreetException" %><%@
page import="com.liferay.portal.AddressZipException" %><%@
page import="com.liferay.portal.CompanyMaxUsersException" %><%@
page import="com.liferay.portal.ContactFirstNameException" %><%@
page import="com.liferay.portal.ContactFullNameException" %><%@
page import="com.liferay.portal.ContactLastNameException" %><%@
page import="com.liferay.portal.CookieNotSupportedException" %><%@
page import="com.liferay.portal.DuplicateOpenIdException" %><%@
page import="com.liferay.portal.DuplicateUserEmailAddressException" %><%@
page import="com.liferay.portal.DuplicateUserIdException" %><%@
page import="com.liferay.portal.DuplicateUserScreenNameException" %><%@
page import="com.liferay.portal.EmailAddressException" %><%@
page import="com.liferay.portal.GroupFriendlyURLException" %><%@
page import="com.liferay.portal.NoSuchCountryException" %><%@
page import="com.liferay.portal.NoSuchListTypeException" %><%@
page import="com.liferay.portal.NoSuchRegionException" %><%@
page import="com.liferay.portal.PasswordExpiredException" %><%@
page import="com.liferay.portal.PhoneNumberException" %><%@
page import="com.liferay.portal.RequiredFieldException" %><%@
page import="com.liferay.portal.RequiredReminderQueryException" %><%@
page import="com.liferay.portal.ReservedUserEmailAddressException" %><%@
page import="com.liferay.portal.ReservedUserIdException" %><%@
page import="com.liferay.portal.ReservedUserScreenNameException" %><%@
page import="com.liferay.portal.SendPasswordException" %><%@
page import="com.liferay.portal.TermsOfUseException" %><%@
page import="com.liferay.portal.UserActiveException" %><%@
page import="com.liferay.portal.UserEmailAddressException" %><%@
page import="com.liferay.portal.UserIdException" %><%@
page import="com.liferay.portal.UserLockoutException" %><%@
page import="com.liferay.portal.UserPasswordException" %><%@
page import="com.liferay.portal.UserReminderQueryException" %><%@
page import="com.liferay.portal.UserScreenNameException" %><%@
page import="com.liferay.portal.WebsiteURLException" %><%@
page import="com.liferay.portal.kernel.facebook.FacebookConnectUtil" %><%@
page import="com.liferay.portal.security.auth.AuthException" %><%@
page import="com.liferay.portal.util.OpenIdUtil" %><%@
page import="com.liferay.portlet.login.util.LoginUtil" %>

<%@ page import="org.openid4java.association.AssociationException" %><%@
page import="org.openid4java.consumer.ConsumerException" %><%@
page import="org.openid4java.discovery.DiscoveryException" %><%@
page import="org.openid4java.message.MessageException" %>

<%
String authType = portletPreferences.getValue("authType", StringPool.BLANK);
%>

<%@ include file="/html/portlet/login/init-ext.jsp" %>