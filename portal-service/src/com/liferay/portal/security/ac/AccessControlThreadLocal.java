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

package com.liferay.portal.security.ac;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

/**
 * @author Michael C. Han
 * @author Raymond Augé
 */
public class AccessControlThreadLocal {

	public static boolean isRemoteAccess() {
		return _remoteAccess.get();
	}

	public static void setRemoteAccess(boolean remoteAccess) {
		_remoteAccess.set(remoteAccess);
	}

	private static ThreadLocal<Boolean> _remoteAccess =
		new AutoResetThreadLocal<Boolean>(
			AutoResetThreadLocal.class + "._remoteAccess", false);

}