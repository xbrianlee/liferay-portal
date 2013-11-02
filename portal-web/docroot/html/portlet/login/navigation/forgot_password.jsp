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

<%@ include file="/html/portlet/login/init.jsp" %>

<%
String strutsAction = ParamUtil.getString(request, "struts_action");

boolean showForgotPasswordIcon = false;

if (!strutsAction.equals("/login/forgot_password") && (company.isSendPassword() || company.isSendPasswordResetLink())) {
	showForgotPasswordIcon = true;
}
%>

<c:if test="<%= showForgotPasswordIcon %>">
	<portlet:renderURL var="forgotPasswordURL">
		<portlet:param name="struts_action" value="/login/forgot_password" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="help"
		message="forgot-password"
		url="<%= forgotPasswordURL %>"
	/>
</c:if>