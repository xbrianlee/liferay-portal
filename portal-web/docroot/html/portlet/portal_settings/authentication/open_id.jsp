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
boolean openIdAuthEnabled = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.OPEN_ID_AUTH_ENABLED, PropsValues.OPEN_ID_AUTH_ENABLED);
%>

<aui:fieldset>
	<aui:input label="enabled" name='<%= "settings--" + PropsKeys.OPEN_ID_AUTH_ENABLED + "--" %>' type="checkbox" value="<%= openIdAuthEnabled %>" />
</aui:fieldset>