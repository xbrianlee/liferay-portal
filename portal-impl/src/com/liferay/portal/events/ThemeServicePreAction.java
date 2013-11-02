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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.util.ColorSchemeFactoryUtil;
import com.liferay.portal.kernel.util.ThemeFactoryUtil;
import com.liferay.portal.model.ColorScheme;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Edward Han
 */
public class ThemeServicePreAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			servicePre(request, response);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void servicePre(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Theme theme = themeDisplay.getTheme();
		ColorScheme colorScheme = themeDisplay.getColorScheme();

		if (theme != null) {
			if (_log.isInfoEnabled()) {
				_log.info("Theme is already set");
			}

			return;
		}

		Layout layout = themeDisplay.getLayout();

		boolean wapTheme = BrowserSnifferUtil.isWap(request);

		if (layout != null) {
			if (wapTheme) {
				theme = layout.getWapTheme();
				colorScheme = layout.getWapColorScheme();
			}
			else {
				theme = layout.getTheme();
				colorScheme = layout.getColorScheme();
			}
		}
		else {
			String themeId = null;
			String colorSchemeId = null;

			if (wapTheme) {
				themeId = ThemeFactoryUtil.getDefaultWapThemeId(
					themeDisplay.getCompanyId());
				colorSchemeId =
					ColorSchemeFactoryUtil.getDefaultWapColorSchemeId();
			}
			else {
				themeId = ThemeFactoryUtil.getDefaultRegularThemeId(
					themeDisplay.getCompanyId());
				colorSchemeId =
					ColorSchemeFactoryUtil.getDefaultRegularColorSchemeId();
			}

			theme = ThemeLocalServiceUtil.getTheme(
				themeDisplay.getCompanyId(), themeId, wapTheme);
			colorScheme = ThemeLocalServiceUtil.getColorScheme(
				themeDisplay.getCompanyId(), theme.getThemeId(), colorSchemeId,
				wapTheme);
		}

		request.setAttribute(WebKeys.THEME, theme);
		request.setAttribute(WebKeys.COLOR_SCHEME, colorScheme);

		themeDisplay.setLookAndFeel(theme, colorScheme);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ThemeServicePreAction.class);

}