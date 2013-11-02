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

<%@ include file="/html/portlet/mobile_device_rules/action/init.jsp" %>

<%
long actionGroupId = GetterUtil.getLong(typeSettingsProperties.getProperty("groupId"));
%>

<aui:select label="site" name="groupId" onChange='<%= liferayPortletResponse.getNamespace() + "changeDisplay();" %>' required="<%= true %>">
	<aui:option disabled="<%= true %>" label="select-a-site" selected="<%= actionGroupId == 0 %>" value="" />

	<%
	int count = 0;

	for (Group group : GroupServiceUtil.getUserSitesGroups()) {
		if (!group.isUser() && !group.isControlPanel()) {
			count++;
	%>

			<aui:option label="<%= group.getName() %>" selected="<%= group.getGroupId() == actionGroupId %>" value="<%= group.getGroupId() %>" />

	<%
		}
	}
	%>

	<c:if test="<%= count == 0 %>">
		<aui:option disabled="<%= true %>" label="no-available-sites" value="0" />
	</c:if>
</aui:select>

<div id="<portlet:namespace />layouts">
	<liferay-util:include page="/html/portlet/mobile_device_rules/action/site_url_layouts.jsp" />
</div>