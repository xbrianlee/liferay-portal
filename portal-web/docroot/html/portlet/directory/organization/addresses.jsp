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
String className = (String)request.getAttribute("addresses.className");
long classPK = (Long)request.getAttribute("addresses.classPK");

List<Address> addresses = Collections.emptyList();

if (classPK > 0) {
	addresses = AddressServiceUtil.getAddresses(className, classPK);
}
%>

<c:if test="<%= !addresses.isEmpty() %>">
	<h3><liferay-ui:message key="address" /></h3>

	<ul class="property-list">

	<%
	for (Address address: addresses) {
		String street1 = address.getStreet1();
		String street2 = address.getStreet2();
		String street3 = address.getStreet3();

		String zipCode = address.getZip();
		String city = address.getCity();

		String mailingName = LanguageUtil.get(pageContext, address.getType().getName());
	%>

		<li class="<%= address.isPrimary() ? "primary" : "" %>">
			<em class="mailing-name"><%= mailingName %></em>

			<c:if test="<%= Validator.isNotNull(street1) %>">
				<%= street1 %><br />
			</c:if>

			<c:if test="<%= Validator.isNotNull(street2) %>">
				<%= street2 %><br />
			</c:if>

			<c:if test="<%= Validator.isNotNull(street3) %>">
				<%= street3 %><br />
			</c:if>

			<c:if test="<%= Validator.isNotNull(zipCode) %>">
				<%= zipCode %>,
			</c:if>

			<c:if test="<%= Validator.isNotNull(city) %>">
				<%= city %>
			</c:if>

			<c:if test="<%= address.isMailing() %>">(<liferay-ui:message key="mailing" />)</c:if>
		</li>

	<%
	}
	%>

	</ul>
</c:if>