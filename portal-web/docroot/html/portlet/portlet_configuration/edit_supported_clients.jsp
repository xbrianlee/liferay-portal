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

<%@ include file="/html/portlet/portlet_configuration/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String returnToFullPageURL = ParamUtil.getString(request, "returnToFullPageURL");

Set<String> allPortletModes = selPortlet.getAllPortletModes();
%>

<liferay-util:include page="/html/portlet/portlet_configuration/tabs1.jsp">
	<liferay-util:param name="tabs1" value="supported-clients" />
</liferay-util:include>

<portlet:actionURL var="editSupportedClientsURL">
	<portlet:param name="struts_action" value="/portlet_configuration/edit_supported_clients" />
</portlet:actionURL>

<aui:form action="<%= editSupportedClientsURL %>" method="post" name=">fm">
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="returnToFullPageURL" type="hidden" value="<%= returnToFullPageURL %>" />
	<aui:input name="portletResource" type="hidden" value="<%= portletResource %>" />

	<%
	for (String curPortletMode : allPortletModes) {
		String mobileDevicesParam = "portletSetupSupportedClientsMobileDevices_" + curPortletMode;
		boolean mobileDevicesDefault = selPortlet.hasPortletMode(ContentTypes.XHTML_MP, PortletModeFactory.getPortletMode(curPortletMode));

		boolean mobileDevices = GetterUtil.getBoolean(portletPreferences.getValue(mobileDevicesParam, String.valueOf(mobileDevicesDefault)));
	%>

		<aui:fieldset label='<%= LanguageUtil.get(pageContext, "portlet-mode") + ": " + LanguageUtil.get(pageContext, curPortletMode) %>'>
			<aui:input disabled="<%= true %>" label="regular-browsers" name='<%= "regularBrowsersEnabled" + curPortletMode %>' type="checkbox" value="<%= true %>" />

			<aui:input label="mobile-devices" name="<%= mobileDevicesParam %>" type="checkbox" value="<%= mobileDevices %>" />
		</aui:fieldset>

	<%
	}
	%>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>