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

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%
long groupId = ((Long)request.getAttribute("edit_pages.jsp-groupId")).longValue();
long liveGroupId = ((Long)request.getAttribute("edit_pages.jsp-liveGroupId")).longValue();
boolean privateLayout = ((Boolean)request.getAttribute("edit_pages.jsp-privateLayout")).booleanValue();
LayoutSet layoutSet = ((LayoutSet)request.getAttribute("edit_pages.jsp-selLayoutSet"));

Theme selTheme = layoutSet.getTheme();
ColorScheme selColorScheme = layoutSet.getColorScheme();

Theme selWapTheme = layoutSet.getWapTheme();
ColorScheme selWapColorScheme = layoutSet.getWapColorScheme();
%>

<liferay-ui:error-marker key="errorSection" value="look-and-feel" />

<aui:model-context bean="<%= layoutSet %>" model="<%= Layout.class %>" />

<h3><liferay-ui:message key="look-and-feel" /></h3>

<aui:fieldset>
	<aui:input name="devices" type="hidden" value='<%= PropsValues.MOBILE_DEVICE_STYLING_WAP_ENABLED? "regular,wap" : "regular" %>' />

	<c:choose>
		<c:when test="<%= PropsValues.MOBILE_DEVICE_STYLING_WAP_ENABLED %>">

		<liferay-ui:tabs
			names="regular-browsers,mobile-devices"
			refresh="<%= false %>"
		>
			<liferay-ui:section>
				<%@ include file="/html/portlet/layouts_admin/layout_set/look_and_feel_regular_browser.jspf" %>
			</liferay-ui:section>

			<liferay-ui:section>
				<%@ include file="/html/portlet/layouts_admin/layout_set/look_and_feel_wap_browser.jspf" %>
			</liferay-ui:section>
		</liferay-ui:tabs>
		</c:when>
		<c:otherwise>
			<%@ include file="/html/portlet/layouts_admin/layout_set/look_and_feel_regular_browser.jspf" %>
		</c:otherwise>
	</c:choose>
</aui:fieldset>