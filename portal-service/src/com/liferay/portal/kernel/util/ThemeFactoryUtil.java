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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.Theme;

/**
 * @author Harrison Schueler
 */
public class ThemeFactoryUtil {

	public static Theme getDefaultRegularTheme(long companyId)
		throws SystemException {

		return getThemeFactory().getDefaultRegularTheme(companyId);
	}

	public static String getDefaultRegularThemeId(long companyId)
		throws SystemException {

		return getThemeFactory().getDefaultRegularThemeId(companyId);
	}

	public static Theme getDefaultWapTheme(long companyId)
		throws SystemException {

		return getThemeFactory().getDefaultWapTheme(companyId);
	}

	public static String getDefaultWapThemeId(long companyId)
		throws SystemException {

		return getThemeFactory().getDefaultWapThemeId(companyId);
	}

	public static Theme getTheme() {
		return getThemeFactory().getTheme();
	}

	public static Theme getTheme(String themeId) {
		return getThemeFactory().getTheme(themeId);
	}

	public static Theme getTheme(String themeId, String name) {
		return getThemeFactory().getTheme(themeId, name);
	}

	public static ThemeFactory getThemeFactory() {
		PortalRuntimePermission.checkGetBeanProperty(ThemeFactoryUtil.class);

		return _ThemeFactory;
	}

	public void setThemeFactory(ThemeFactory ThemeFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ThemeFactory = ThemeFactory;
	}

	private static ThemeFactory _ThemeFactory;

}