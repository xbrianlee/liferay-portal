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

<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
User selUser = (User)request.getAttribute("user.selUser");

String languageId = BeanParamUtil.getString(selUser, request, "languageId", user.getLanguageId());
String timeZoneId = BeanParamUtil.getString(selUser, request, "timeZoneId", user.getTimeZoneId());
%>

<aui:model-context bean="<%= selUser %>" model="<%= User.class %>" />

<h3><liferay-ui:message key="display-settings" /></h3>

<aui:fieldset>
	<aui:select label="language" name="languageId">

		<%
		Locale selLocale = LocaleUtil.fromLanguageId(languageId);

		Locale[] locales = LanguageUtil.getAvailableLocales();

		Locale languageLocale = locale;

		for (Locale curLocale : locales) {
			if (portletName.equals(PortletKeys.MY_ACCOUNT)) {
				languageLocale = curLocale;
			}
		%>

			<aui:option label="<%= curLocale.getDisplayName(languageLocale) %>" lang="<%= LocaleUtil.toW3cLanguageId(languageLocale) %>" selected="<%= (selLocale.getLanguage().equals(curLocale.getLanguage()) && selLocale.getCountry().equals(curLocale.getCountry())) %>" value="<%= LocaleUtil.toLanguageId(curLocale) %>" />

		<%
		}
		%>

	</aui:select>

	<aui:input label="time-zone" name="timeZoneId" type="timeZone" value="<%= timeZoneId %>" />

	<aui:input name="greeting" />
</aui:fieldset>