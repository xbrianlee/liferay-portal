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
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class FullNameGeneratorFactory {

	public static FullNameGenerator getInstance() {
		return _fullNameGenerator;
	}

	public static void setInstance(FullNameGenerator fullNameGenerator) {
		if (_log.isDebugEnabled()) {
			_log.debug("Set " + ClassUtil.getClassName(fullNameGenerator));
		}

		if (fullNameGenerator == null) {
			_fullNameGenerator = _originalFullNameGenerator;
		}
		else {
			_fullNameGenerator = fullNameGenerator;
		}
	}

	public void afterPropertiesSet() throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("Instantiate " + PropsValues.USERS_FULL_NAME_GENERATOR);
		}

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		_originalFullNameGenerator =
			(FullNameGenerator)InstanceFactory.newInstance(
				classLoader, PropsValues.USERS_FULL_NAME_GENERATOR);

		_fullNameGenerator = _originalFullNameGenerator;
	}

	private static Log _log = LogFactoryUtil.getLog(
		FullNameGeneratorFactory.class);

	private static volatile FullNameGenerator _fullNameGenerator;
	private static FullNameGenerator _originalFullNameGenerator;

}