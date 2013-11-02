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
 * @author Amos Fong
 * @author Shuyang Zhou
 */
public class EmailAddressGeneratorFactory {

	public static EmailAddressGenerator getInstance() {
		return _emailAddressGenerator;
	}

	public static void setInstance(
		EmailAddressGenerator emailAddressGenerator) {

		if (_log.isDebugEnabled()) {
			_log.debug("Set " + ClassUtil.getClassName(emailAddressGenerator));
		}

		if (emailAddressGenerator == null) {
			_emailAddressGenerator = _originalEmailAddressGenerator;
		}
		else {
			_emailAddressGenerator = emailAddressGenerator;
		}
	}

	public void afterPropertiesSet() throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug(
				"Instantiate " + PropsValues.USERS_EMAIL_ADDRESS_GENERATOR);
		}

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		_originalEmailAddressGenerator =
			(EmailAddressGenerator)InstanceFactory.newInstance(
				classLoader, PropsValues.USERS_EMAIL_ADDRESS_GENERATOR);

		_emailAddressGenerator = _originalEmailAddressGenerator;
	}

	private static Log _log = LogFactoryUtil.getLog(
		EmailAddressGeneratorFactory.class);

	private static volatile EmailAddressGenerator _emailAddressGenerator;
	private static EmailAddressGenerator _originalEmailAddressGenerator;

}