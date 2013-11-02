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

package com.liferay.portal.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.ThemeFactory;
import com.liferay.portal.kernel.util.ThemeFactoryUtil;
import com.liferay.portal.model.Theme;
import com.liferay.portal.model.impl.ThemeImpl;

/**
 * @author Harrison Schueler
 */
public class ThemeFactoryImpl implements ThemeFactory {

	@Override
	public Theme getDefaultRegularTheme(long companyId) throws SystemException {
		return new ThemeImpl(
			ThemeFactoryUtil.getDefaultRegularThemeId(companyId),
			StringPool.BLANK);
	}

	@Override
	public String getDefaultRegularThemeId(long companyId)
		throws SystemException {

		String defaultRegularThemeId = PrefsPropsUtil.getString(
			companyId, PropsKeys.DEFAULT_REGULAR_THEME_ID);

		return PortalUtil.getJsSafePortletId(defaultRegularThemeId);
	}

	@Override
	public Theme getDefaultWapTheme(long companyId) throws SystemException {
		return new ThemeImpl(
			ThemeFactoryUtil.getDefaultWapThemeId(companyId), StringPool.BLANK);
	}

	@Override
	public String getDefaultWapThemeId(long companyId) throws SystemException {
		String defaultWapThemeId = PrefsPropsUtil.getString(
			companyId, PropsKeys.DEFAULT_WAP_THEME_ID);

		return PortalUtil.getJsSafePortletId(defaultWapThemeId);
	}

	@Override
	public Theme getTheme() {
		return new ThemeImpl();
	}

	@Override
	public Theme getTheme(String themeId) {
		return new ThemeImpl(themeId);
	}

	@Override
	public Theme getTheme(String themeId, String name) {
		return new ThemeImpl(themeId, name);
	}

}