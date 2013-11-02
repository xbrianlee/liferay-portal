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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.ColorScheme;

/**
 * @author Vilmos Papp
 */
public class ColorSchemeFactoryUtil {

	public static ColorScheme getColorScheme() {
		return getColorSchemeFactory().getColorScheme();
	}

	public static ColorScheme getColorScheme(String colorSchemeId) {
		return getColorSchemeFactory().getColorScheme(colorSchemeId);
	}

	public static ColorScheme getColorScheme(
		String colorSchemeId, String name, String cssClass) {

		return getColorSchemeFactory().getColorScheme(
			colorSchemeId, name, cssClass);
	}

	public static ColorSchemeFactory getColorSchemeFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ColorSchemeFactoryUtil.class);

		return _colorSchemeFactory;
	}

	public static ColorScheme getDefaultRegularColorScheme() {
		return getColorSchemeFactory().getDefaultRegularColorScheme();
	}

	public static String getDefaultRegularColorSchemeId() {
		return getColorSchemeFactory().getDefaultRegularColorSchemeId();
	}

	public static ColorScheme getDefaultWapColorScheme() {
		return getColorSchemeFactory().getDefaultWapColorScheme();
	}

	public static String getDefaultWapColorSchemeId() {
		return getColorSchemeFactory().getDefaultWapColorSchemeId();
	}

	public void setColorSchemeFactory(ColorSchemeFactory colorSchemeFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_colorSchemeFactory = colorSchemeFactory;
	}

	private static ColorSchemeFactory _colorSchemeFactory;

}