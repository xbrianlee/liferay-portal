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

package com.liferay.portal.template;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.theme.ThemeLoader;
import com.liferay.portal.theme.ThemeLoaderFactory;

import java.io.File;
import java.io.IOException;

import java.net.URL;

/**
 * @author Tina Tian
 */
public class ThemeResourceParser extends URLResourceParser {

	@Override
	public URL getURL(String templateId) throws IOException {
		int pos = templateId.indexOf(TemplateConstants.THEME_LOADER_SEPARATOR);

		if (pos == -1) {
			return null;
		}

		String servletContextName = templateId.substring(0, pos);

		ThemeLoader themeLoader = ThemeLoaderFactory.getThemeLoader(
			servletContextName);

		if (themeLoader == null) {
			_log.error(
				templateId + " is not valid because " + servletContextName +
					" does not map to a theme loader");

			return null;
		}

		String templateName = templateId.substring(
			pos + TemplateConstants.THEME_LOADER_SEPARATOR.length());

		String themesPath = themeLoader.getThemesPath();

		if (templateName.startsWith(themesPath)) {
			templateId = templateName.substring(themesPath.length());
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				templateId + " is associated with the theme loader " +
					servletContextName + " " + themeLoader);
		}

		File fileStorage = themeLoader.getFileStorage();

		return new File(fileStorage, templateId).toURI().toURL();
	}

	private static Log _log = LogFactoryUtil.getLog(ThemeResourceParser.class);

}