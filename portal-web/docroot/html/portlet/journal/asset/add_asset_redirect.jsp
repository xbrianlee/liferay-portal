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

<%@ include file="/html/portlet/journal/init.jsp" %>

<%
String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

Portlet selPortlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), referringPortletResource);
%>

<aui:script use="aui-base">
	Liferay.fire(
		'closeWindow',
		{
			id: '_<%= HtmlUtil.escapeJS(selPortlet.getPortletId()) %>_editAsset',
			portletAjaxable: <%= selPortlet.isAjaxable() %>,
			refresh: '<%= HtmlUtil.escapeJS(selPortlet.getPortletId()) %>'
		}
	);
</aui:script>