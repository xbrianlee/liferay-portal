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

import com.liferay.portal.kernel.util.ColorSchemeFactory;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ColorScheme;
import com.liferay.portal.model.impl.ColorSchemeImpl;

/**
 * @author Vilmos Papp
 */
public class ColorSchemeFactoryImpl implements ColorSchemeFactory {

	@Override
	public ColorScheme getColorScheme() {
		return new ColorSchemeImpl();
	}

	@Override
	public ColorScheme getColorScheme(String colorSchemeId) {
		return new ColorSchemeImpl(colorSchemeId);
	}

	@Override
	public ColorScheme getColorScheme(
		String colorSchemeId, String name, String cssClass) {

		return new ColorSchemeImpl(colorSchemeId, name, cssClass);
	}

	@Override
	public ColorScheme getDefaultRegularColorScheme() {
		return new ColorSchemeImpl(
			getDefaultRegularColorSchemeId(), StringPool.BLANK,
			StringPool.BLANK);
	}

	@Override
	public String getDefaultRegularColorSchemeId() {
		return PropsValues.DEFAULT_REGULAR_COLOR_SCHEME_ID;
	}

	@Override
	public ColorScheme getDefaultWapColorScheme() {
		return new ColorSchemeImpl(
			getDefaultWapColorSchemeId(), StringPool.BLANK, StringPool.BLANK);
	}

	@Override
	public String getDefaultWapColorSchemeId() {
		return PropsValues.DEFAULT_WAP_COLOR_SCHEME_ID;
	}

}