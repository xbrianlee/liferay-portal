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

package com.liferay.portal.service.permission;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Brian Wing Shun Chan
 */
public class PasswordPolicyPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long passwordPolicyId,
			String actionId)
		throws PrincipalException {

		getPasswordPolicyPermission().check(
			permissionChecker, passwordPolicyId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long passwordPolicyId,
		String actionId) {

		return getPasswordPolicyPermission().contains(
			permissionChecker, passwordPolicyId, actionId);
	}

	public static PasswordPolicyPermission getPasswordPolicyPermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			PasswordPolicyPermissionUtil.class);

		return _passwordPolicyPermission;
	}

	public void setPasswordPolicyPermission(
		PasswordPolicyPermission passwordPolicyPermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_passwordPolicyPermission = passwordPolicyPermission;
	}

	private static PasswordPolicyPermission _passwordPolicyPermission;

}