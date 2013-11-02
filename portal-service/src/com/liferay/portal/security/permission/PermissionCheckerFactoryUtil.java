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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.User;

/**
 * @author Charles May
 * @author Brian Wing Shun Chan
 */
public class PermissionCheckerFactoryUtil {

	public static PermissionChecker create(User user) throws Exception {
		return getPermissionCheckerFactory().create(user);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #create(User)}
	 */
	public static PermissionChecker create(User user, boolean checkGuest)
		throws Exception {

		return getPermissionCheckerFactory().create(user);
	}

	public static PermissionCheckerFactory getPermissionCheckerFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			PermissionCheckerFactoryUtil.class);

		return _permissionCheckerFactory;
	}

	public void setPermissionCheckerFactory(
		PermissionCheckerFactory permissionCheckerFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_permissionCheckerFactory = permissionCheckerFactory;
	}

	private static PermissionCheckerFactory _permissionCheckerFactory;

}