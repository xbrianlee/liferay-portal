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

<%@ include file="/html/portlet/sites_directory/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
%>

<aui:row>
	<aui:col width="<%= 50 %>">
		<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

		<aui:form action="<%= configurationURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

			<aui:fieldset column="<%= true %>">
				<aui:select name="preferences--sites--">
					<aui:option label="<%= SitesDirectoryTag.SITES_TOP_LEVEL %>" selected="<%= sites.equals(SitesDirectoryTag.SITES_TOP_LEVEL) %>" />
					<aui:option label="<%= SitesDirectoryTag.SITES_PARENT_LEVEL %>" selected="<%= sites.equals(SitesDirectoryTag.SITES_PARENT_LEVEL) %>" />
					<aui:option label="<%= SitesDirectoryTag.SITES_SIBLINGS %>" selected="<%= sites.equals(SitesDirectoryTag.SITES_SIBLINGS) %>" />
					<aui:option label="<%= SitesDirectoryTag.SITES_CHILDREN %>" selected="<%= sites.equals(SitesDirectoryTag.SITES_CHILDREN) %>" />
				</aui:select>

				<aui:select name="preferences--displayStyle--">
					<aui:option label="icon" selected='<%= displayStyle.equals("icon") %>' />
					<aui:option label="descriptive" selected='<%= displayStyle.equals("descriptive") %>' />
					<aui:option label="list" selected='<%= displayStyle.equals("list") %>' />
					<aui:option label="list-hierarchy" selected='<%= displayStyle.equals("list-hierarchy") %>' />
				</aui:select>
			</aui:fieldset>
			<aui:button-row>
				<aui:button type="submit" />
			</aui:button-row>
		</aui:form>
	</aui:col>
	<aui:col width="<%= 50 %>">
		<liferay-portlet:preview
			portletName="<%= portletResource %>"
			queryString="struts_action=/sites_directory/view"
			showBorders="<%= true %>"
		/>
	</aui:col>
</aui:row>

<aui:script use="aui-base">
	var selectDisplayStyle = A.one('#<portlet:namespace />displayStyle');
	var selectSites = A.one('#<portlet:namespace />sites');

	var selects = A.all('#<portlet:namespace />fm select');

	var curPortletBoundaryId = '#p_p_id_<%= HtmlUtil.escapeJS(portletResource) %>_';

	var toggleCustomFields = function() {
		var data = {};

		var displayStyle = selectDisplayStyle.val();
		var sites = selectSites.val();

		data['_<%= HtmlUtil.escapeJS(portletResource) %>_displayStyle'] = displayStyle;
		data['_<%= HtmlUtil.escapeJS(portletResource) %>_sites'] = sites;

		Liferay.Portlet.refresh(curPortletBoundaryId, data);
	}

	selects.on('change', toggleCustomFields);

	toggleCustomFields();
</aui:script>