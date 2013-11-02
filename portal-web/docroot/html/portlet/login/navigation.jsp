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

<liferay-util:buffer var="navigation">
	<liferay-ui:icon-list>

		<%
		for (String section : PropsValues.LOGIN_FORM_NAVIGATION_PRE) {
		%>

			<liferay-util:include page='<%= "/html/portlet/login/navigation/" + _getSectionJsp(section) + ".jsp" %>' portletId="<%= portletDisplay.getRootPortletId() %>" />

		<%
		}

		for (String section : PropsValues.LOGIN_FORM_NAVIGATION_POST) {
		%>

			<liferay-util:include page='<%= "/html/portlet/login/navigation/" + _getSectionJsp(section) + ".jsp" %>' portletId="<%= portletDisplay.getRootPortletId() %>" />

		<%
		}
		%>

	</liferay-ui:icon-list>
</liferay-util:buffer>

<%
navigation = navigation.trim();
%>

<c:if test="<%= Validator.isNotNull(navigation) %>">
	<div class="navigation">
		<%= navigation %>
	</div>
</c:if>

<%!
private String _getSectionJsp(String name) {
	return TextFormatter.format(name, TextFormatter.N);
}
%>