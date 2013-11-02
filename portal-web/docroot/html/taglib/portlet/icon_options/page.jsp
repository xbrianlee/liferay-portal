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

<%@ include file="/html/taglib/init.jsp" %>

<liferay-ui:icon-menu
	cssClass="portlet-options"
	direction="down"
	extended="<%= false %>"
	icon="../aui/cog"
	message="options"
	showArrow="<%= true %>"
	showWhenSingleIcon="<%= true %>"
>
	<liferay-portlet:icon-refresh />

	<liferay-portlet:icon-portlet-css />

	<liferay-portlet:icon-configuration />

	<liferay-portlet:icon-edit />

	<liferay-portlet:icon-edit-defaults />

	<liferay-portlet:icon-edit-guest />

	<liferay-portlet:icon-export-import />

	<liferay-portlet:icon-help />

	<liferay-portlet:icon-print />

	<liferay-portlet:icon-maximize />

	<liferay-portlet:icon-minimize />

	<liferay-portlet:icon-close />

	<%
	Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);
	%>

	<c:if test="<%= portlet != null %>">

		<%
		PortletPreferences portletSetup = PortletPreferencesFactoryUtil.getStrictLayoutPortletSetup(layout, portletDisplay.getId());

		boolean widgetShowAddAppLink = GetterUtil.getBoolean(portletSetup.getValue("lfrWidgetShowAddAppLink", null), PropsValues.THEME_PORTLET_SHARING_DEFAULT);

		String facebookAPIKey = portletSetup.getValue("lfrFacebookApiKey", StringPool.BLANK);
		String facebookCanvasPageURL = portletSetup.getValue("lfrFacebookCanvasPageUrl", StringPool.BLANK);
		boolean facebookShowAddAppLink = GetterUtil.getBoolean(portletSetup.getValue("lfrFacebookShowAddAppLink", null), true);

		if (Validator.isNull(facebookCanvasPageURL) || Validator.isNull(facebookAPIKey)) {
			facebookShowAddAppLink = false;
		}

		boolean iGoogleShowAddAppLink = GetterUtil.getBoolean(portletSetup.getValue("lfrIgoogleShowAddAppLink", StringPool.BLANK));
		boolean netvibesShowAddAppLinks = GetterUtil.getBoolean(portletSetup.getValue("lfrNetvibesShowAddAppLink", StringPool.BLANK));
		boolean appShowShareWithFriendsLink = GetterUtil.getBoolean(portletSetup.getValue("lfrAppShowShareWithFriendsLink", StringPool.BLANK));

		PortletURL basePortletURL = PortletURLFactoryUtil.create(request, PortletKeys.PORTLET_SHARING, layout.getPlid(), PortletRequest.RESOURCE_PHASE);
		%>

		<c:if test="<%= widgetShowAddAppLink %>">

			<%
			String widgetHREF = "javascript:Liferay.PortletSharing.showWidgetInfo('" + PortalUtil.getWidgetURL(portlet, themeDisplay) + "', '" + basePortletURL + "');";
			%>

			<liferay-ui:icon
				cssClass='<%= portletDisplay.getNamespace() + "expose-as-widget" %>'
				image="../aui/plus-sign"
				label="<%= true %>"
				message="add-to-any-website"
				url="<%= widgetHREF %>"
			/>
		</c:if>

		<c:if test="<%= facebookShowAddAppLink %>">
			<liferay-ui:icon
				image="../aui/facebook"
				label="<%= true %>"
				message="add-to-facebook"
				method="get"
				url='<%= "http://www.facebook.com/add.php?api_key=" + facebookAPIKey + "&ref=pd" %>'
			/>
		</c:if>

		<c:if test="<%= iGoogleShowAddAppLink %>">

			<%
			String googleGadgetHREF = "http://fusion.google.com/add?source=atgs&moduleurl=" + PortalUtil.getGoogleGadgetURL(portlet, themeDisplay);
			%>

			<liferay-ui:icon
				cssClass='<%= portletDisplay.getNamespace() + "expose-as-widget" %>'
				image="../aui/plus-sign"
				label="<%= true %>"
				message="add-to-igoogle"
				url="<%= googleGadgetHREF %>"
			/>
		</c:if>

		<c:if test="<%= netvibesShowAddAppLinks %>">

			<%
			String netvibesHREF = "javascript:Liferay.PortletSharing.showNetvibesInfo('" + PortalUtil.getNetvibesURL(portlet, themeDisplay) + "', '" + basePortletURL + "');";
			%>

			<liferay-ui:icon
				image="../aui/plus-sign"
				label="<%= true %>"
				message="add-to-netvibes"
				method="get"
				url="<%= netvibesHREF %>"
			/>
		</c:if>

		<c:if test="<%= appShowShareWithFriendsLink %>">
			<liferay-ui:icon
				image="../aui/share"
				label="<%= true %>"
				message="share-this-application-with-friends"
				method="get"
				url="javascript:;"
			/>
		</c:if>
	</c:if>
</liferay-ui:icon-menu>