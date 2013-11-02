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
String className = (String)request.getAttribute("phones.className");
long classPK = (Long)request.getAttribute("phones.classPK");

List<Phone> phones = Collections.emptyList();

if (classPK > 0) {
	phones = PhoneServiceUtil.getPhones(className, classPK);
}
%>

<c:if test="<%= !phones.isEmpty() %>">
	<h3><liferay-ui:message key="phones" /></h3>

	<ul class="property-list">

	<%
	for (Phone phone: phones) {
	%>

		<li class="<%= phone.isPrimary() ? "primary" : "" %>">
			<%= phone.getNumber() %> <%= phone.getExtension() %> <%= LanguageUtil.get(pageContext, phone.getType().getName()) %>
		</li>

	<%
	}
	%>

	</ul>
</c:if>