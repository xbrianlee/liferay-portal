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
Organization organization = (Organization)request.getAttribute(WebKeys.ORGANIZATION);

long organizationId = BeanParamUtil.getLong(organization, request, "organizationId");

request.setAttribute(WebKeys.ORGANIZATION, organization);
request.setAttribute("addresses.className", Organization.class.getName());
request.setAttribute("addresses.classPK", organizationId);
request.setAttribute("emailAddresses.className", Organization.class.getName());
request.setAttribute("emailAddresses.classPK", organizationId);
request.setAttribute("phones.className", Organization.class.getName());
request.setAttribute("phones.classPK", organizationId);
request.setAttribute("websites.className", Organization.class.getName());
request.setAttribute("websites.classPK", organizationId);
%>

<liferay-util:include page="/html/portlet/directory/tabs1.jsp" />

<div class="organization-information">
	<div class="section entity-details">
		<liferay-util:include page="/html/portlet/directory/organization/details.jsp" />
	</div>

	<div class="section entity-email-addresses">
		<liferay-util:include page="/html/portlet/directory/common/additional_email_addresses.jsp" />
	</div>

	<div class="section entity-websites">
		<liferay-util:include page="/html/portlet/directory/common/websites.jsp" />
	</div>

	<div class="section entity-addresses">
		<liferay-util:include page="/html/portlet/directory/organization/addresses.jsp" />
	</div>

	<div class="section entity-phones">
		<liferay-util:include page="/html/portlet/directory/organization/phone_numbers.jsp" />
	</div>

	<div class="section entity-services">
		<liferay-util:include page="/html/portlet/directory/organization/services.jsp" />
	</div>

	<div class="section entity-comments">
		<liferay-util:include page="/html/portlet/directory/organization/comments.jsp" />
	</div>
</div>