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

package com.liferay.portal.theme;

import com.liferay.portal.kernel.servlet.ServletContextPool;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class ThemeLoaderFactory {

	public static boolean destroy(String servletContextName) {
		ThemeLoader themeLoader = _themeLoaders.remove(servletContextName);

		if (themeLoader == null) {
			return false;
		}

		ServletContextPool.remove(servletContextName);

		themeLoader.destroy();

		return true;
	}

	public static ThemeLoader getDefaultThemeLoader() {
		ThemeLoader themeLoader = null;

		for (Map.Entry<String, ThemeLoader> entry : _themeLoaders.entrySet()) {
			themeLoader = entry.getValue();

			break;
		}

		return themeLoader;
	}

	public static ThemeLoader getThemeLoader(String servletContextName) {
		return _themeLoaders.get(servletContextName);
	}

	public static void init(
		String servletContextName, ServletContext servletContext,
		String[] xmls) {

		ServletContextPool.put(servletContextName, servletContext);

		ThemeLoader themeLoader = new ThemeLoader(
			servletContextName, servletContext, xmls);

		_themeLoaders.put(servletContextName, themeLoader);
	}

	public static void loadThemes() {
		for (Map.Entry<String, ThemeLoader> entry : _themeLoaders.entrySet()) {
			ThemeLoader themeLoader = entry.getValue();

			themeLoader.loadThemes();
		}
	}

	private static Map<String, ThemeLoader> _themeLoaders =
		new HashMap<String, ThemeLoader>();

}