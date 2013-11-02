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
String className = (String)request.getAttribute("emailAddresses.className");
long classPK = (Long)request.getAttribute("emailAddresses.classPK");

List<EmailAddress> emailAddresses = Collections.emptyList();

int[] emailAddressesIndexes = null;

String emailAddressesIndexesParam = ParamUtil.getString(request, "emailAddressesIndexes");

if (Validator.isNotNull(emailAddressesIndexesParam)) {
	emailAddresses = new ArrayList<EmailAddress>();

	emailAddressesIndexes = StringUtil.split(emailAddressesIndexesParam, 0);

	for (int emailAddressesIndex : emailAddressesIndexes) {
		emailAddresses.add(new EmailAddressImpl());
	}
}
else {
	if (classPK > 0) {
		emailAddresses = EmailAddressServiceUtil.getEmailAddresses(className, classPK);

		emailAddressesIndexes = new int[emailAddresses.size()];

		for (int i = 0; i < emailAddresses.size() ; i++) {
			emailAddressesIndexes[i] = i;
		}
	}

	if (emailAddresses.isEmpty()) {
		emailAddresses = new ArrayList<EmailAddress>();

		emailAddresses.add(new EmailAddressImpl());

		emailAddressesIndexes = new int[] {0};
	}

	if (emailAddressesIndexes == null) {
		emailAddressesIndexes = new int[0];
	}
}
%>

<liferay-ui:error-marker key="errorSection" value="additionalEmailAddresses" />

<h3><liferay-ui:message key="additional-email-addresses" /></h3>

<div class="alert alert-info">
	<liferay-ui:message key="email-address-and-type-are-required-fields" />
</div>

<liferay-ui:error exception="<%= EmailAddressException.class %>" message="please-enter-a-valid-email-address" />
<liferay-ui:error key="<%= NoSuchListTypeException.class.getName() + className + ListTypeConstants.EMAIL_ADDRESS %>" message="please-select-a-type" />

<aui:fieldset>

	<%
	for (int i = 0; i < emailAddressesIndexes.length; i++) {
		int emailAddressesIndex = emailAddressesIndexes[i];

		EmailAddress emailAddress = emailAddresses.get(i);
	%>

		<aui:model-context bean="<%= emailAddress %>" model="<%= EmailAddress.class %>" />

		<div class="lfr-form-row lfr-form-row-inline">
			<div class="row-fields">
				<aui:input name='<%= "emailAddressId" + emailAddressesIndex %>' type="hidden" value="<%= emailAddress.getEmailAddressId() %>" />

				<aui:input cssClass="email-field" fieldParam='<%= "emailAddressAddress" + emailAddressesIndex %>' id='<%= "emailAddressAddress" + emailAddressesIndex %>' inlineField="<%= true %>" label="email-address" name="address" width="150px" />

				<aui:select inlineField="<%= true %>" label="type" listType="<%= className + ListTypeConstants.EMAIL_ADDRESS %>" name='<%= "emailAddressTypeId" + emailAddressesIndex %>' />

				<aui:input checked="<%= emailAddress.isPrimary() %>" cssClass="primary-ctrl" id='<%= "emailAddressPrimary" + emailAddressesIndex %>' inlineField="<%= true %>" label="primary" name="emailAddressPrimary" type="radio" value="<%= emailAddressesIndex %>" />
			</div>
		</div>

	<%
	}
	%>

	<aui:input name="emailAddressesIndexes" type="hidden" value="<%= StringUtil.merge(emailAddressesIndexes) %>" />
</aui:fieldset>

<aui:script use="liferay-auto-fields">
	Liferay.once(
		'formNavigator:reveal<portlet:namespace />additionalEmailAddresses',
		function() {
			new Liferay.AutoFields(
				{
					contentBox: '#<portlet:namespace />additionalEmailAddresses > fieldset',
					fieldIndexes: '<portlet:namespace />emailAddressesIndexes',
					namespace: '<portlet:namespace />'
				}
			).render();
		}
	);
</aui:script>