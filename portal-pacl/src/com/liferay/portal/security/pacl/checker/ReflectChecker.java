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
public class ReflectChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
		initSuppressAccessChecks();
	}

	@Override
	public AuthorizationProperty generateAuthorizationProperty(
		Object... arguments) {

		if ((arguments == null) || (arguments.length != 1) ||
			!(arguments[0] instanceof Permission)) {

			return null;
		}

		Permission permission = (Permission)arguments[0];

		String name = permission.getName();

		String key = null;
		String value = null;

		if (name.startsWith(RUNTIME_PERMISSION_SUPPRESS_ACCESS_CHECKS)) {
			key = "security-manager-suppress-access-checks";
			value = Boolean.TRUE.toString();
		}
		else {
			return null;
		}

		AuthorizationProperty authorizationProperty =
			new AuthorizationProperty();

		authorizationProperty.setKey(key);
		authorizationProperty.setValue(value);

		return authorizationProperty;
	}

	@Override
	public boolean implies(Permission permission) {
		String name = permission.getName();

		if (name.startsWith(RUNTIME_PERMISSION_SUPPRESS_ACCESS_CHECKS)) {
			if (!hasSuppressAccessChecks(permission)) {
				logSecurityException(
					_log, "Attempted to suppess access checks");

				return false;
			}
		}
		else {
			int stackIndex = Reflection.getStackIndex(10, 9);

			Class<?> callerClass = Reflection.getCallerClass(stackIndex);

			if (isTrustedCaller(callerClass, permission)) {
				return true;
			}

			logSecurityException(_log, "Attempted to reflect");

			return false;
		}

		return true;
	}

	protected boolean hasSuppressAccessChecks(Permission permission) {
		if (_suppressAccessChecks) {
			return true;
		}

		int stackIndex = Reflection.getStackIndex(11, 10);

		Class<?> callerClass = Reflection.getCallerClass(stackIndex);

		if (isTrustedCaller(callerClass, permission)) {
			return true;
		}

		logSecurityException(_log, "Attempted to reflect");

		return false;
	}

	protected void initSuppressAccessChecks() {
		_suppressAccessChecks = getPropertyBoolean(
			"security-manager-suppress-access-checks");
	}

	private static Log _log = LogFactoryUtil.getLog(ReflectChecker.class);

	private boolean _suppressAccessChecks;

}