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
String adminMailHostNames = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_MAIL_HOST_NAMES);
%>

<h3><liferay-ui:message key="mail-host-names" /></h3>

<aui:fieldset>
	<aui:input label='<%= LanguageUtil.format(pageContext, "enter-one-mail-host-name-per-line-for-all-additional-mail-host-names-besides-x", company.getMx(), false) %>' name='<%= "settings--" + PropsKeys.ADMIN_MAIL_HOST_NAMES + "--" %>' type="textarea" value="<%= adminMailHostNames %>" />
</aui:fieldset>