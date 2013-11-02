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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ScreenNameGeneratorFactory {

	public static ScreenNameGenerator getInstance() {
		return _screenNameGenerator;
	}

	public static void setInstance(ScreenNameGenerator screenNameGenerator) {
		if (_log.isDebugEnabled()) {
			_log.debug("Set " + ClassUtil.getClassName(screenNameGenerator));
		}

		if (screenNameGenerator == null) {
			_screenNameGenerator = _originalScreenNameGenerator;
		}
		else {
			_screenNameGenerator = screenNameGenerator;
		}
	}

	public void afterPropertiesSet() throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug(
				"Instantiate " + PropsValues.USERS_SCREEN_NAME_GENERATOR);
		}

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		_originalScreenNameGenerator =
			(ScreenNameGenerator)InstanceFactory.newInstance(
				classLoader, PropsValues.USERS_SCREEN_NAME_GENERATOR);

		_screenNameGenerator = _originalScreenNameGenerator;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ScreenNameGeneratorFactory.class);

	private static ScreenNameGenerator _originalScreenNameGenerator;
	private static volatile ScreenNameGenerator _screenNameGenerator;

}