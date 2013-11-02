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

boolean showAnonymousIcon = false;

if (!strutsAction.startsWith("/login/create_anonymous_account") && portletName.equals(PortletKeys.FAST_LOGIN)) {
	showAnonymousIcon = true;
}
%>

<c:if test="<%= showAnonymousIcon %>">
	<portlet:renderURL var="anonymousURL">
		<portlet:param name="struts_action" value="/login/create_anonymous_account" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="guest"
		src='<%= themeDisplay.getPathThemeImages() + "/common/user_icon.png" %>'
		url="<%= anonymousURL %>"
	/>
</c:if>