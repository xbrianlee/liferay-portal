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

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.security.Permission;

/**
 * @author Brian Wing Shun Chan
 */
public class DefaultAcceptChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
	}

	@Override
	public boolean implies(Permission permission) {
		if (_log.isDebugEnabled()) {
			Thread.dumpStack();
		}

		if (!_log.isInfoEnabled()) {
			return true;
		}

		Class<?> clazz = permission.getClass();
		String name = permission.getName();
		String actions = permission.getActions();

		if (Validator.isNotNull(actions)) {
			_log.info(
				"Allowing permission " + clazz.getName() + " to " + name +
					" on " + actions);
		}
		else {
			_log.info("Allowing permission " + clazz.getName() + " to " + name);
		}

		return true;
	}

	private static Log _log = LogFactoryUtil.getLog(DefaultAcceptChecker.class);

}