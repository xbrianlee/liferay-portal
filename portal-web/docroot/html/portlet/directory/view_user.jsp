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

<%@ include file="/html/portlet/directory/init.jsp" %>

<%
User selUser = PortalUtil.getSelectedUser(request);

selUser = selUser.toEscapedModel();

Contact selContact = selUser.getContact();

List<Organization> organizations = OrganizationLocalServiceUtil.getUserOrganizations(selUser.getUserId());

request.setAttribute("user.selUser", selUser);
request.setAttribute("user.selContact", selContact);
request.setAttribute("user.organizations", organizations);
request.setAttribute("addresses.className", Contact.class.getName());
request.setAttribute("addresses.classPK", selContact.getContactId());
request.setAttribute("emailAddresses.className", Contact.class.getName());
request.setAttribute("emailAddresses.classPK", selContact.getContactId());
request.setAttribute("phones.className", Contact.class.getName());
request.setAttribute("phones.classPK", selContact.getContactId());
request.setAttribute("websites.className", Contact.class.getName());
request.setAttribute("websites.classPK", selContact.getContactId());
%>

<liferay-util:include page="/html/portlet/directory/tabs1.jsp" />

<div class="user-information">
	<div class="section entity-details">
		<liferay-util:include page="/html/portlet/directory/user/details.jsp" />
	</div>

	<div class="section entity-addresses">
		<liferay-util:include page="/html/portlet/directory/user/addresses.jsp" />
	</div>

	<div class="section entity-email-addresses">
		<liferay-util:include page="/html/portlet/directory/common/additional_email_addresses.jsp" />
	</div>

	<div class="section entity-websites">
		<liferay-util:include page="/html/portlet/directory/common/websites.jsp" />
	</div>

	<div class="section entity-phones">
		<liferay-util:include page="/html/portlet/directory/user/phone_numbers.jsp" />
	</div>

	<div class="section entity-instant-messenger">
		<liferay-util:include page="/html/portlet/directory/user/instant_messenger.jsp" />
	</div>

	<div class="section entity-social-network">
		<liferay-util:include page="/html/portlet/directory/user/social_network.jsp" />
	</div>

	<div class="section entity-sms">
		<liferay-util:include page="/html/portlet/directory/user/sms.jsp" />
	</div>

	<div class="section entity-comments">
		<liferay-util:include page="/html/portlet/directory/user/comments.jsp" />
	</div>
</div>