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
List<Organization> organizations = (List<Organization>)request.getAttribute("user.organizations");

String className = (String)request.getAttribute("phones.className");
long classPK = (Long)request.getAttribute("phones.classPK");

List<Phone> personalPhones = Collections.emptyList();
List<Phone> organizationPhones = new ArrayList<Phone>();

if (classPK > 0) {
	personalPhones = PhoneServiceUtil.getPhones(className, classPK);
}

for (int i = 0; i < organizations.size(); i++) {
	try {
		organizationPhones.addAll(PhoneServiceUtil.getPhones(Organization.class.getName(), organizations.get(i).getOrganizationId()));
	}
	catch (Exception e) {
	}
}
%>

<c:if test="<%= !personalPhones.isEmpty() || !organizationPhones.isEmpty() %>">
	<h3><liferay-ui:message key="phones" /></h3>

	<c:if test="<%= !organizationPhones.isEmpty() %>">
		<h4><liferay-ui:message key="organization-phones" /></h4>

		<ul class="property-list">

		<%
		for (Phone phone: organizationPhones) {
		%>

			<li class="<%= phone.isPrimary() ? "primary" : "" %>">
				<%= phone.getNumber() %> <%= phone.getExtension() %> <%= LanguageUtil.get(pageContext, phone.getType().getName()) %>
			</li>

		<%
		}
		%>

		</ul>
	</c:if>

	<c:if test="<%= !personalPhones.isEmpty() %>">
		<h4><liferay-ui:message key="personal-phones" /></h4>

		<ul class="property-list">

		<%
		for (Phone phone: personalPhones) {
		%>

			<li class="<%= phone.isPrimary() ? "primary" : "" %>">
				<%= phone.getNumber() %> <%= phone.getExtension() %> <%= LanguageUtil.get(pageContext, phone.getType().getName()) %>
			</li>

		<%
		}
		%>

		</ul>
	</c:if>
</c:if>