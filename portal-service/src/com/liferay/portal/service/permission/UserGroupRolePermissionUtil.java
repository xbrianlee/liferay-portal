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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class UserGroupRolePermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long groupId, long roleId)
		throws PortalException, SystemException {

		getUserGroupRolePermission().check(permissionChecker, groupId, roleId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long roleId)
		throws PortalException, SystemException {

		return getUserGroupRolePermission().contains(
			permissionChecker, groupId, roleId);
	}

	public static UserGroupRolePermission getUserGroupRolePermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			UserGroupRolePermissionUtil.class);

		return _userGroupRolePermission;
	}

	public void setUserGroupRolePermission(
		UserGroupRolePermission userGroupRolePermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_userGroupRolePermission = userGroupRolePermission;
	}

	private static UserGroupRolePermission _userGroupRolePermission;

}