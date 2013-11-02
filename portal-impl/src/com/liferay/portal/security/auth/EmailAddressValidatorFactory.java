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
public class EmailAddressValidatorFactory {

	public static EmailAddressValidator getInstance() {
		return _emailAddressValidator;
	}

	public static void setInstance(
		EmailAddressValidator emailAddressValidator) {

		if (_log.isDebugEnabled()) {
			_log.debug("Set " + ClassUtil.getClassName(emailAddressValidator));
		}

		if (emailAddressValidator == null) {
			_emailAddressValidator = _originalEmailAddressValidator;
		}
		else {
			_emailAddressValidator = emailAddressValidator;
		}
	}

	public void afterPropertiesSet() throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug(
				"Instantiate " + PropsValues.USERS_EMAIL_ADDRESS_VALIDATOR);
		}

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		_originalEmailAddressValidator =
			(EmailAddressValidator)InstanceFactory.newInstance(
				classLoader, PropsValues.USERS_EMAIL_ADDRESS_VALIDATOR);

		_emailAddressValidator = _originalEmailAddressValidator;
	}

	private static Log _log = LogFactoryUtil.getLog(
		EmailAddressValidatorFactory.class);

	private static volatile EmailAddressValidator _emailAddressValidator;
	private static EmailAddressValidator _originalEmailAddressValidator;

}