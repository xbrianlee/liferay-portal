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

<%
String adminAnalyticsTypes = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.ADMIN_ANALYTICS_TYPES);
%>

<h3><liferay-ui:message key="analytics" /></h3>

<aui:fieldset>
	<aui:input label="enter-one-analytics-system-name-per-line" name='<%= "settings--" + PropsKeys.ADMIN_ANALYTICS_TYPES + "--" %>' type="textarea" value="<%= adminAnalyticsTypes %>" />
</aui:fieldset>