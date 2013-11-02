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
public class NetChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
	}

	@Override
	public boolean implies(Permission permission) {
		String name = permission.getName();

		if (name.equals(NET_PERMISSION_GET_PROXY_SELECTOR)) {
			if (!hasGetProxySelector(permission)) {
				logSecurityException(_log, "Attempted to get proxy selector");

				return false;
			}
		}
		else if (name.equals(NET_PERMISSION_SPECIFY_STREAM_HANDLER)) {
			if (!hasSpecifyStreamHandler(permission)) {
				logSecurityException(
					_log, "Attempted to specify stream handler");

				return false;
			}
		}
		else {
			logSecurityException(
				_log, "Attempted " + name + " network operation");

			return false;
		}

		return true;
	}

	protected boolean hasGetProxySelector(Permission permission) {
		int stackIndex = Reflection.getStackIndex(11, 10);

		Class<?> callerClass = Reflection.getCallerClass(stackIndex);

		if (isTrustedCaller(callerClass, permission)) {
			return true;
		}

		return false;
	}

	protected boolean hasSpecifyStreamHandler(Permission permission) {
		int stackIndex = Reflection.getStackIndex(11, 10);

		Class<?> callerClass = Reflection.getCallerClass(stackIndex);

		if (isTrustedCaller(callerClass, permission)) {
			return true;
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(NetChecker.class);

}