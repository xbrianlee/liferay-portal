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
import com.liferay.portal.security.pacl.Reflection;

import java.security.Permission;

/**
 * @author Brian Wing Shun Chan
 */
public class SecurityChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
	}

	@Override
	public boolean implies(Permission permission) {
		String name = permission.getName();

		if (name.equals(SECURITY_PERMISSION_GET_POLICY)) {
			if (!hasGetPolicy(permission)) {
				logSecurityException(_log, "Attempted to get the policy");

				return false;
			}
		}
		else if (name.equals(SECURITY_PERMISSION_SET_POLICY)) {
			if (!hasSetPolicy(permission)) {
				logSecurityException(_log, "Attempted to set the policy");

				return false;
			}
		}
		else {
			if (_log.isDebugEnabled()) {
				Thread.dumpStack();
			}

			logSecurityException(
				_log,
				"Attempted to " + permission.getName() + " on " +
					permission.getActions());

			return false;
		}

		return true;
	}

	protected boolean hasGetPolicy(Permission permission) {
		int stackIndex = Reflection.getStackIndex(
			new int[] {11, 11}, new int[] {11, 10});

		Class<?> callerClass = Reflection.getCallerClass(stackIndex);

		if (isTrustedCaller(callerClass, permission)) {
			return true;
		}

		logSecurityException(_log, "Attempted to get the policy");

		return false;
	}

	protected boolean hasSetPolicy(Permission permission) {
		int stackIndex = Reflection.getStackIndex(
			new int[] {11, 11}, new int[] {11, 10});

		Class<?> callerClass = Reflection.getCallerClass(stackIndex);

		if (isTrustedCaller(callerClass, permission)) {
			return true;
		}

		logSecurityException(_log, "Attempted to set the policy");

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(SecurityChecker.class);

}