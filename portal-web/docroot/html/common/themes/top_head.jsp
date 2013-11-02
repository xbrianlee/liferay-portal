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

<%@ include file="/html/common/themes/init.jsp" %>

<c:if test="<%= PropsValues.MONITORING_PORTAL_REQUEST %>">
	<%@ include file="/html/common/themes/top_monitoring.jspf" %>
</c:if>

<%@ include file="/html/common/themes/top_meta.jspf" %>
<%@ include file="/html/common/themes/top_meta-ext.jsp" %>

<link href="<%= themeDisplay.getPathThemeImages() %>/<%= PropsValues.THEME_SHORTCUT_ICON %>" rel="Shortcut Icon" />

<%-- Available Translations --%>

<%
if (!themeDisplay.isSignedIn() && layout.isPublicLayout()) {
	String completeURL = PortalUtil.getCurrentCompleteURL(request);

	String canonicalURL = PortalUtil.getCanonicalURL(completeURL, themeDisplay, layout);
%>

	<link href="<%= HtmlUtil.escapeAttribute(canonicalURL) %>" rel="canonical" />

	<%
	Locale[] availableLocales = LanguageUtil.getAvailableLocales(themeDisplay.getSiteGroupId());

	if (availableLocales.length > 1) {
		for (Locale availableLocale : availableLocales) {
	%>

			<c:if test="<%= availableLocale.equals(LocaleUtil.getDefault()) %>">
				<link href="<%= canonicalURL %>" hreflang="x-default" rel="alternate" />
			</c:if>

			<link href="<%= HtmlUtil.escapeAttribute(PortalUtil.getAlternateURL(canonicalURL, themeDisplay, availableLocale, layout)) %>" hreflang="<%= LocaleUtil.toW3cLanguageId(availableLocale) %>" rel="alternate" />

	<%
		}
	}
	%>

<%
}
%>

<%-- Portal CSS --%>

<link class="lfr-css-file" href="<%= HtmlUtil.escapeAttribute(PortalUtil.getStaticResourceURL(request, themeDisplay.getPathThemeCss() + "/aui.css")) %>" rel="stylesheet" type="text/css" />

<link href="<%= HtmlUtil.escapeAttribute(PortalUtil.getStaticResourceURL(request, themeDisplay.getCDNDynamicResourcesHost() + themeDisplay.getPathContext() + "/html/css/main.css")) %>" rel="stylesheet" type="text/css" />

<%
List<Portlet> portlets = null;

if (layout != null) {
	String ppid = ParamUtil.getString(request, "p_p_id");

	if (ppid.equals(PortletKeys.PORTLET_CONFIGURATION)) {
		portlets = new ArrayList<Portlet>();

		portlets.add(PortletLocalServiceUtil.getPortletById(company.getCompanyId(), PortletKeys.PORTLET_CONFIGURATION));

		ppid = ParamUtil.getString(request, PortalUtil.getPortletNamespace(ppid) + "portletResource");

		if (Validator.isNotNull(ppid)) {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), ppid);

			if (portlet != null) {
				portlets.add(portlet);
			}
		}
	}
	else if (layout.isTypePortlet()) {
		portlets = layoutTypePortlet.getAllPortlets();

		if (themeDisplay.isStateMaximized() || themeDisplay.isStatePopUp()) {
			if (Validator.isNotNull(ppid)) {
				Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), ppid);

				if ((portlet != null) && !portlets.contains(portlet)) {
					portlets.add(portlet);
				}
			}
		}
	}
	else if ((layout.isTypeControlPanel() || layout.isTypePanel()) && Validator.isNotNull(ppid)) {
		portlets = new ArrayList<Portlet>();

		Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), ppid);

		if (portlet != null) {
			portlets.add(portlet);
		}
	}

	request.setAttribute(WebKeys.LAYOUT_PORTLETS, portlets);
}
%>

<%-- Portlet CSS References --%>

<%@ include file="/html/common/themes/top_portlet_resources_css.jspf" %>

<%-- Portal JavaScript References --%>

<%@ include file="/html/common/themes/top_js.jspf" %>
<%@ include file="/html/common/themes/top_js-ext.jspf" %>

<%-- Portlet JavaScript References --%>

<%@ include file="/html/common/themes/top_portlet_resources_js.jspf" %>

<%-- Raw Text --%>

<%
List<String> markupHeaders = (List<String>)request.getAttribute(MimeResponse.MARKUP_HEAD_ELEMENT);

if (markupHeaders != null) {
	for (String markupHeader : markupHeaders) {
%>

		<%= markupHeader %>

<%
	}
}

StringBundler pageTopSB = OutputTag.getData(request, WebKeys.PAGE_TOP);
%>

<c:if test="<%= pageTopSB != null %>">

	<%
	pageTopSB.writeTo(out);
	%>

</c:if>

<%-- Theme CSS --%>

<link class="lfr-css-file" href="<%= HtmlUtil.escapeAttribute(PortalUtil.getStaticResourceURL(request, themeDisplay.getPathThemeCss() + "/main.css")) %>" rel="stylesheet" type="text/css" />

<%-- User Inputted Layout CSS --%>

<c:if test="<%= (layout != null) && Validator.isNotNull(layout.getCssText()) %>">
	<style type="text/css">
		<%= layout.getCssText() %>
	</style>
</c:if>

<%-- User Inputted Portlet CSS --%>

<c:if test="<%= portlets != null %>">
	<style type="text/css">

		<%
		for (Portlet portlet : portlets) {
			PortletPreferences portletSetup = PortletPreferencesFactoryUtil.getStrictLayoutPortletSetup(layout, portlet.getPortletId());

			String portletSetupCss = portletSetup.getValue("portletSetupCss", StringPool.BLANK);
		%>

			<c:if test="<%= Validator.isNotNull(portletSetupCss) %>">

				<%
				try {
				%>

					<%@ include file="/html/common/themes/portlet_css.jspf" %>

				<%
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(e.getMessage());
					}
				}
				%>

			</c:if>

		<%
		}
		%>

	</style>
</c:if>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.common.themes.top_head_jsp");
%>