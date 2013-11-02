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

<%@ include file="/html/portlet/portal_settings/init.jsp" %>

<liferay-ui:error-marker key="errorSection" value="authentication" />

<h3><liferay-ui:message key="authentication" /></h3>

<liferay-ui:tabs
	names="<%= StringUtil.merge(PropsValues.COMPANY_SETTINGS_FORM_AUTHENTICATION) %>"
	refresh="<%= false %>"
>

	<%
	for (String section : PropsValues.COMPANY_SETTINGS_FORM_AUTHENTICATION) {
	%>

		<liferay-ui:section>
			<liferay-util:include page='<%= "/html/portlet/portal_settings/authentication/" + _getSectionJsp(section) + ".jsp" %>' portletId="<%= portletDisplay.getRootPortletId() %>" />
		</liferay-ui:section>

	<%
	}
	%>

</liferay-ui:tabs>

<%!
private String _getSectionJsp(String name) {
	return TextFormatter.format(name, TextFormatter.N);
}
%>