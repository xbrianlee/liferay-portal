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

<%@ page import="com.liferay.portal.AccountNameException" %><%@
page import="com.liferay.portal.CompanyMxException" %><%@
page import="com.liferay.portal.CompanyVirtualHostException" %><%@
page import="com.liferay.portal.kernel.facebook.FacebookConnectUtil" %><%@
page import="com.liferay.portal.kernel.ldap.DuplicateLDAPServerNameException" %><%@
page import="com.liferay.portal.kernel.ldap.LDAPFilterException" %><%@
page import="com.liferay.portal.kernel.ldap.LDAPServerNameException" %><%@
page import="com.liferay.portal.security.auth.AuthSettingsUtil" %><%@
page import="com.liferay.portal.security.ldap.LDAPSettingsUtil" %><%@
page import="com.liferay.portal.security.ldap.PortalLDAPUtil" %><%@
page import="com.liferay.portal.servlet.filters.sso.opensso.OpenSSOUtil" %><%@
page import="com.liferay.portlet.documentlibrary.NoSuchFileException" %><%@
page import="com.liferay.util.ldap.LDAPUtil" %>

<%@ page import="java.net.HttpURLConnection" %><%@
page import="java.net.MalformedURLException" %><%@
page import="java.net.URL" %>

<%@ page import="javax.naming.directory.Attribute" %><%@
page import="javax.naming.directory.Attributes" %><%@
page import="javax.naming.directory.SearchResult" %><%@
page import="javax.naming.ldap.LdapContext" %>

<%@ include file="/html/portlet/portal_settings/init-ext.jsp" %>