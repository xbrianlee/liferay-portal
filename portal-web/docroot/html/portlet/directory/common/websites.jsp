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
String className = (String)request.getAttribute("websites.className");
long classPK = (Long)request.getAttribute("websites.classPK");

List<Website> websites = Collections.emptyList();

if (classPK > 0) {
	websites = WebsiteServiceUtil.getWebsites(className, classPK);
}
%>

<c:if test="<%= !websites.isEmpty() %>">
	<h3><liferay-ui:message key="websites" /></h3>

	<ul class="property-list">

	<%
	for (Website website: websites) {
		website = website.toEscapedModel();
	%>

		<li class="<%= website.isPrimary() ? "primary" : "" %>">
			<a href="<%= website.getUrl() %>"><%= website.getUrl() %></a>

			<%= LanguageUtil.get(pageContext, website.getType().getName()) %>
		</li>

	<%
	}
	%>

	</ul>
</c:if>