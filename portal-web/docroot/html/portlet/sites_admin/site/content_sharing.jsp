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

<%@ include file="/html/portlet/sites_admin/init.jsp" %>

<%
Group liveGroup = (Group)request.getAttribute("site.liveGroup");

UnicodeProperties groupTypeSettings = null;

if (liveGroup != null) {
	groupTypeSettings = liveGroup.getTypeSettingsProperties();
}
else {
	groupTypeSettings = new UnicodeProperties();
}

int companyContentSharingWithChildrenEnabled = PrefsPropsUtil.getInteger(company.getCompanyId(), PropsKeys.SITES_CONTENT_SHARING_WITH_CHILDREN_ENABLED);
int groupContentSharingWithChildrenEnabled = PropertiesParamUtil.getInteger(groupTypeSettings, request, "contentSharingWithChildrenEnabled", Sites.CONTENT_SHARING_WITH_CHILDREN_DEFAULT_VALUE);
%>

<h3><liferay-ui:message key="content-sharing" /></h3>

<aui:fieldset>
	<aui:select label="allow-subsites-to-display-content-from-this-site" name="contentSharingWithChildrenEnabled">
		<aui:option label='<%= (companyContentSharingWithChildrenEnabled == Sites.CONTENT_SHARING_WITH_CHILDREN_ENABLED_BY_DEFAULT) ? "default-value-enabled" : "default-value-disabled" %>' selected="<%= groupContentSharingWithChildrenEnabled == Sites.CONTENT_SHARING_WITH_CHILDREN_DEFAULT_VALUE %>" value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_DEFAULT_VALUE %>" />
		<aui:option label="enabled" selected="<%= groupContentSharingWithChildrenEnabled == Sites.CONTENT_SHARING_WITH_CHILDREN_ENABLED %>" value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_ENABLED %>" />
		<aui:option label="disabled" selected="<%= groupContentSharingWithChildrenEnabled == Sites.CONTENT_SHARING_WITH_CHILDREN_DISABLED %>" value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_DISABLED %>" />
	</aui:select>
</aui:fieldset>