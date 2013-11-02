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
String className = (String)request.getAttribute("emailAddresses.className");
long classPK = (Long)request.getAttribute("emailAddresses.classPK");

List<EmailAddress> emailAddresses = Collections.emptyList();

if (classPK > 0) {
	emailAddresses = EmailAddressServiceUtil.getEmailAddresses(className, classPK);
}
%>

<c:if test="<%= !emailAddresses.isEmpty() %>">
	<h3><liferay-ui:message key="additional-email-addresses" /></h3>

	<ul class="property-list">

	<%
	for (int i = 0; i < emailAddresses.size(); i++) {
		EmailAddress emailAddress = emailAddresses.get(i);
	%>

		<li class="<%= emailAddress.isPrimary() ? "primary" : "" %>">
			<a href="mailto:<%= emailAddress.getAddress() %>"><%= emailAddress.getAddress() %></a>

			<%= LanguageUtil.get(pageContext, emailAddress.getType().getName()) %>
		</li>

	<%
	}
	%>

	</ul>
</c:if>