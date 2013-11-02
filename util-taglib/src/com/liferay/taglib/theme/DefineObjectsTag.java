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

package com.liferay.taglib.theme;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Brian Wing Shun Chan
 */
public class DefineObjectsTag extends TagSupport {

	@Override
	public int doStartTag() {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (themeDisplay == null) {
			return SKIP_BODY;
		}

		pageContext.setAttribute("themeDisplay", themeDisplay);
		pageContext.setAttribute("company", themeDisplay.getCompany());
		pageContext.setAttribute("account", themeDisplay.getAccount());
		pageContext.setAttribute("user", themeDisplay.getUser());
		pageContext.setAttribute("realUser", themeDisplay.getRealUser());
		pageContext.setAttribute("contact", themeDisplay.getContact());

		if (themeDisplay.getLayout() != null) {
			pageContext.setAttribute("layout", themeDisplay.getLayout());
		}

		if (themeDisplay.getLayouts() != null) {
			pageContext.setAttribute("layouts", themeDisplay.getLayouts());
		}

		pageContext.setAttribute("plid", new Long(themeDisplay.getPlid()));

		if (themeDisplay.getLayoutTypePortlet() != null) {
			pageContext.setAttribute(
				"layoutTypePortlet", themeDisplay.getLayoutTypePortlet());
		}

		pageContext.setAttribute(
			"scopeGroupId", new Long(themeDisplay.getScopeGroupId()));
		pageContext.setAttribute(
			"permissionChecker", themeDisplay.getPermissionChecker());
		pageContext.setAttribute("locale", themeDisplay.getLocale());
		pageContext.setAttribute("timeZone", themeDisplay.getTimeZone());
		pageContext.setAttribute("theme", themeDisplay.getTheme());
		pageContext.setAttribute("colorScheme", themeDisplay.getColorScheme());
		pageContext.setAttribute(
			"portletDisplay", themeDisplay.getPortletDisplay());

		// Deprecated

		pageContext.setAttribute(
			"portletGroupId", new Long(themeDisplay.getScopeGroupId()));

		return SKIP_BODY;
	}

}