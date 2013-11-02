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
boolean termsOfUseRequired = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.TERMS_OF_USE_REQUIRED, PropsValues.TERMS_OF_USE_REQUIRED);
boolean usersScreenNameAlwaysAutogenerate = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE, PropsValues.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE);
boolean usersLastNameRequired = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_LAST_NAME_REQUIRED, PropsValues.USERS_LAST_NAME_REQUIRED);
boolean fieldEnableMale = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_MALE, PropsValues.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_MALE);
boolean fieldEnableBirthday = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_BIRTHDAY, PropsValues.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_BIRTHDAY);

String adminReservedScreenNames = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_RESERVED_SCREEN_NAMES);
String adminReservedEmailAddresses = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_RESERVED_EMAIL_ADDRESSES);

String adminDefaultGroupNames = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_DEFAULT_GROUP_NAMES);
String adminDefaultOrganizationGroupNames = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_DEFAULT_ORGANIZATION_GROUP_NAMES);
String adminDefaultRoleNames = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_DEFAULT_ROLE_NAMES);
String adminDefaultUserGroupNames = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_DEFAULT_USER_GROUP_NAMES);
boolean adminSyncDefaultAssociations = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_SYNC_DEFAULT_ASSOCIATIONS, PropsValues.ADMIN_SYNC_DEFAULT_ASSOCIATIONS);
%>

<h3><liferay-ui:message key="users" /></h3>

<liferay-ui:tabs
	names="fields,reserved-credentials,default-user-associations"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<aui:fieldset>
			<aui:input label="terms-of-use-required" name='<%= "settings--" + PropsKeys.TERMS_OF_USE_REQUIRED + "--" %>' type="checkbox" value="<%= termsOfUseRequired %>" />

			<aui:input label="autogenerate-user-screen-names" name='<%= "settings--" + PropsKeys.USERS_SCREEN_NAME_ALWAYS_AUTOGENERATE + "--" %>' type="checkbox" value="<%= usersScreenNameAlwaysAutogenerate %>" />

			<aui:input label="last-name-required" name='<%= "settings--" + PropsKeys.USERS_LAST_NAME_REQUIRED + "--" %>' type="checkbox" value="<%= usersLastNameRequired %>" />

			<aui:input label="enable-birthday" name='<%= "settings--" + PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_BIRTHDAY + "--" %>' type="checkbox" value="<%= fieldEnableBirthday %>" />

			<aui:input label="enable-gender" name='<%= "settings--" + PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_CONTACT_MALE + "--" %>' type="checkbox" value="<%= fieldEnableMale %>" />
		</aui:fieldset>
	</liferay-ui:section>
	<liferay-ui:section>
		<aui:fieldset>
			<aui:input helpMessage="enter-one-screen-name-per-line-to-reserve-the-screen-name" label="screen-names" name='<%= "settings--" + PropsKeys.ADMIN_RESERVED_SCREEN_NAMES + "--" %>' type="textarea" value="<%= adminReservedScreenNames %>" />

			<aui:input helpMessage="enter-one-user-email-address-per-line-to-reserve-the-user-email-address" label="email-addresses" name='<%= "settings--" + PropsKeys.ADMIN_RESERVED_EMAIL_ADDRESSES + "--" %>' type="textarea" value="<%= adminReservedEmailAddresses %>" />
		</aui:fieldset>
	</liferay-ui:section>
	<liferay-ui:section>
		<aui:fieldset>
			<aui:input helpMessage="check-to-apply-the-changes-to-existing-users" label="apply-to-existing-users" name='<%= "settings--" + PropsKeys.ADMIN_SYNC_DEFAULT_ASSOCIATIONS + "--" %>' type="checkbox" value="<%= adminSyncDefaultAssociations %>" />

			<aui:input helpMessage="enter-the-default-site-names-per-line-that-are-associated-with-newly-created-users" label="sites" name='<%= "settings--" + PropsKeys.ADMIN_DEFAULT_GROUP_NAMES + "--" %>' type="textarea" value="<%= adminDefaultGroupNames %>" />

			<aui:input helpMessage="enter-the-default-organization-site-names-per-line-that-are-associated-with-newly-created-users" label="organization-sites" name='<%= "settings--" + PropsKeys.ADMIN_DEFAULT_ORGANIZATION_GROUP_NAMES + "--" %>' type="textarea" value="<%= adminDefaultOrganizationGroupNames %>" />

			<aui:input helpMessage="enter-the-default-role-names-per-line-that-are-associated-with-newly-created-users" label="roles" name='<%= "settings--" + PropsKeys.ADMIN_DEFAULT_ROLE_NAMES + "--" %>' type="textarea" value="<%= adminDefaultRoleNames %>" />

			<aui:input helpMessage="enter-the-default-user-group-names-per-line-that-are-associated-with-newly-created-users" label="user-groups" name='<%= "settings--" + PropsKeys.ADMIN_DEFAULT_USER_GROUP_NAMES + "--" %>' type="textarea" value="<%= adminDefaultUserGroupNames %>" />
		</aui:fieldset>
	</liferay-ui:section>
</liferay-ui:tabs>