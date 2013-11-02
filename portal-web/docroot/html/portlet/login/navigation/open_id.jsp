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

boolean showOpenIdIcon = false;

if (!strutsAction.equals("/login/open_id") && OpenIdUtil.isEnabled(company.getCompanyId())) {
	showOpenIdIcon = true;
}
%>

<c:if test="<%= showOpenIdIcon %>">
	<portlet:renderURL var="openIdURL">
		<portlet:param name="struts_action" value="/login/open_id" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="open-id"
		src='<%= themeDisplay.getPathThemeImages() + "/common/openid.gif" %>'
		url="<%= openIdURL %>"
	/>
</c:if>