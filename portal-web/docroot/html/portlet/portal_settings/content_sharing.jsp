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
boolean contentSharingWithSiteAdministratorsEnabled = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.SITES_CONTENT_SHARING_THROUGH_ADMINISTRATORS_ENABLED);
int contentSharingWithChildrenEnabled = PrefsPropsUtil.getInteger(company.getCompanyId(), PropsKeys.SITES_CONTENT_SHARING_WITH_CHILDREN_ENABLED);
%>

<h3><liferay-ui:message key="content-sharing" /></h3>

<aui:fieldset>
	<aui:input label="allow-site-administrators-to-display-content-from-other-sites-they-administer" name='<%= "settings--" + PropsKeys.SITES_CONTENT_SHARING_THROUGH_ADMINISTRATORS_ENABLED + "--" %>' type="checkbox" value="<%= contentSharingWithSiteAdministratorsEnabled %>" />

	<aui:select label="allow-subsites-to-display-content-from-parent-sites" name='<%= "settings--" + PropsKeys.SITES_CONTENT_SHARING_WITH_CHILDREN_ENABLED + "--" %>'>
		<aui:option label="enabled-by-default" selected="<%= contentSharingWithChildrenEnabled == Sites.CONTENT_SHARING_WITH_CHILDREN_ENABLED_BY_DEFAULT %>" value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_ENABLED_BY_DEFAULT %>" />
		<aui:option label="disabled-by-default" selected="<%= contentSharingWithChildrenEnabled == Sites.CONTENT_SHARING_WITH_CHILDREN_DISABLED_BY_DEFAULT %>" value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_DISABLED_BY_DEFAULT %>" />
		<aui:option label="disabled" selected="<%= contentSharingWithChildrenEnabled == Sites.CONTENT_SHARING_WITH_CHILDREN_DISABLED %>" value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_DISABLED %>" />
	</aui:select>
</aui:fieldset>