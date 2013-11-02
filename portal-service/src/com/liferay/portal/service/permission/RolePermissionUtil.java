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
public class RolePermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long roleId, String actionId)
		throws PrincipalException {

		getRolePermission().check(permissionChecker, roleId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, long roleId,
		String actionId) {

		return getRolePermission().contains(
			permissionChecker, groupId, roleId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long roleId, String actionId) {

		return getRolePermission().contains(
			permissionChecker, roleId, actionId);
	}

	public static RolePermission getRolePermission() {
		PortalRuntimePermission.checkGetBeanProperty(RolePermissionUtil.class);

		return _rolePermission;
	}

	public void setRolePermission(RolePermission rolePermission) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_rolePermission = rolePermission;
	}

	private static RolePermission _rolePermission;

}