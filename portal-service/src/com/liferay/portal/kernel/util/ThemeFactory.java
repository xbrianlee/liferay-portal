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
import com.liferay.portal.model.Theme;

/**
 * @author Harrison Schueler
 */
public interface ThemeFactory {

	public Theme getDefaultRegularTheme(long companyId) throws SystemException;

	public String getDefaultRegularThemeId(long companyId)
		throws SystemException;

	public Theme getDefaultWapTheme(long companyId) throws SystemException;

	public String getDefaultWapThemeId(long companyId) throws SystemException;

	public Theme getTheme();

	public Theme getTheme(String themeId);

	public Theme getTheme(String themeId, String name);

}