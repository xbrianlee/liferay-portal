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

<%@ include file="/html/portlet/software_catalog/init.jsp" %>

<liferay-ui:header
	backURL="javascript:history.go(-1);"
	title="error"
/>

<liferay-ui:error exception="<%= NoSuchFrameworkVersionException.class %>" message="the-framework-version-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchLicenseException.class %>" message="the-license-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchProductEntryException.class %>" message="the-product-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchProductVersionException.class %>" message="the-product-version-could-not-be-found" />
<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-do-not-have-the-required-permissions" />