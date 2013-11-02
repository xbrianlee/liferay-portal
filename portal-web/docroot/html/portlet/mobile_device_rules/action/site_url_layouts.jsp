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
long actionGroupId = 0;

if (action != null) {
	actionGroupId = GetterUtil.getLong(typeSettingsProperties.getProperty("groupId"));
}
else {
	actionGroupId = ParamUtil.getLong(request, "actionGroupId");
}

long actionPlid = 0;

if (action != null) {
	actionPlid = GetterUtil.getLong(typeSettingsProperties.getProperty("plid"));
}
else {
	actionPlid = ParamUtil.getLong(request, "actionPlid");
}
%>

<aui:input name="actionPlid" type="hidden" value="<%= actionPlid %>" />

<aui:select label="page" name="plid" required="<%= true %>">
	<aui:option disabled="<%= true %>" label="select-a-page" selected="<%= actionPlid == 0 %>" value="" />

	<%
	List<Layout> publicLayouts = LayoutServiceUtil.getLayouts(actionGroupId, false);
	%>

	<c:if test="<%= !publicLayouts.isEmpty() %>">
		<aui:option disabled="<%= true %>" label="public-pages" value="0" />

		<%
		for (Layout publicLayout : publicLayouts) {
		%>

			<aui:option label="<%= publicLayout.getName(locale) %>" selected="<%= publicLayout.getPlid() == actionPlid %>" value="<%= publicLayout.getPlid() %>" />

		<%
		}
		%>

	</c:if>

	<%
	List<Layout> privateLayouts = LayoutServiceUtil.getLayouts(actionGroupId, true);
	%>

	<c:if test="<%= !privateLayouts.isEmpty() %>">
		<aui:option disabled="<%= true %>" label="private-pages" value="0" />

		<%
		for (Layout privateLayout : privateLayouts) {
		%>

			<aui:option label="<%= privateLayout.getName(locale) %>" selected="<%= privateLayout.getPlid() == actionPlid %>" value="<%= privateLayout.getPlid() %>" />

		<%
		}
		%>

	</c:if>

	<c:if test="<%= publicLayouts.isEmpty() && privateLayouts.isEmpty() %>">
		<aui:option disabled="<%= true %>" label="no-available-pages" value="0" />
	</c:if>
</aui:select>