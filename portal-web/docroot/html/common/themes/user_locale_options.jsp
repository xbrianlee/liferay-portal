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

<%
String currentURL = PortalUtil.getCurrentURL(request);

Locale userLocale = user.getLocale();

Locale[] availableLocales = LanguageUtil.getAvailableLocales(themeDisplay.getSiteGroupId());
%>

<c:if test="<%= !locale.equals(user.getLocale()) %>">
	<button class="close" id="ignoreUserLocaleOptions" type="button">&times;</button>

	<%= LanguageUtil.format(userLocale, "this-page-is-displayed-in-x", locale.getDisplayName(userLocale)) %>

	<c:if test="<%= ArrayUtil.contains(availableLocales, userLocale) %>">

		<%
		PortletURL displayPreferredLanguageURL = new PortletURLImpl(request, PortletKeys.LANGUAGE, plid, PortletRequest.ACTION_PHASE);

		displayPreferredLanguageURL.setParameter("struts_action", "/language/view");
		displayPreferredLanguageURL.setParameter("redirect", currentURL);
		displayPreferredLanguageURL.setParameter("languageId", user.getLanguageId());
		displayPreferredLanguageURL.setParameter("persistState", Boolean.FALSE.toString());
		displayPreferredLanguageURL.setPortletMode(PortletMode.VIEW);
		displayPreferredLanguageURL.setWindowState(WindowState.NORMAL);

		String displayPreferredLanguageURLString = displayPreferredLanguageURL.toString();

		displayPreferredLanguageURLString = HttpUtil.addParameter(displayPreferredLanguageURLString, "showUserLocaleOptionsMessage", false);
		%>

		<aui:a href="<%= displayPreferredLanguageURLString %>"><%= LanguageUtil.format(userLocale, "display-the-page-in-x", userLocale.getDisplayName(userLocale)) %></aui:a>
	</c:if>

	<%
	PortletURL changePreferredLanguageURL = new PortletURLImpl(request, PortletKeys.LANGUAGE, plid, PortletRequest.ACTION_PHASE);

	changePreferredLanguageURL.setParameter("struts_action", "/language/view");
	changePreferredLanguageURL.setParameter("redirect", currentURL);
	changePreferredLanguageURL.setParameter("languageId", themeDisplay.getLanguageId());
	changePreferredLanguageURL.setPortletMode(PortletMode.VIEW);
	changePreferredLanguageURL.setWindowState(WindowState.NORMAL);

	String changePreferredLanguageURLString = changePreferredLanguageURL.toString();

	changePreferredLanguageURLString = HttpUtil.addParameter(changePreferredLanguageURLString, "showUserLocaleOptionsMessage", false);
	%>

	<aui:a href="<%= changePreferredLanguageURLString %>"><%= LanguageUtil.format(userLocale, "set-x-as-your-preferred-language", locale.getDisplayName(userLocale)) %></aui:a>

	<aui:script use="aui-base,liferay-store">
		var ignoreUserLocaleOptionsNode = A.one('#ignoreUserLocaleOptions');

		ignoreUserLocaleOptionsNode.on(
			'click',
			function() {
				Liferay.Store(
					{
						ignoreUserLocaleOptions: true,
						useHttpSession: true
					}
				);
			}
		);
	</aui:script>
</c:if>