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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class DoAsUserThread extends Thread {

	public DoAsUserThread(long userId) {
		_userId = userId;
	}

	public boolean isSuccess() {
		return _success;
	}

	@Override
	public void run() {
		try {
			PrincipalThreadLocal.setName(_userId);

			User user = UserLocalServiceUtil.getUserById(_userId);

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			doRun();

			_success = true;
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			PrincipalThreadLocal.setName(null);
			PermissionThreadLocal.setPermissionChecker(null);
		}
	}

	protected abstract void doRun() throws Exception;

	private static Log _log = LogFactoryUtil.getLog(DoAsUserThread.class);

	private boolean _success;
	private long _userId;

}