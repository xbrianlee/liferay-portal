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
Contact selContact = (Contact)request.getAttribute("user.selContact");
%>

<c:if test="<%= Validator.isNotNull(selContact.getSmsSn()) %>">
	<h3><liferay-ui:message key="sms" /></h3>

	<ul class="property-list">
		<li><%= HtmlUtil.escape(selContact.getSmsSn()) %></li>
	</ul>
</c:if>