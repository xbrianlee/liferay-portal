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
import com.liferay.portal.model.Group;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Brian Wing Shun Chan
 */
public class GroupPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, Group group, String actionId)
		throws PortalException, SystemException {

		getGroupPermission().check(permissionChecker, group, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException, SystemException {

		getGroupPermission().check(permissionChecker, groupId, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		getGroupPermission().check(permissionChecker, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Group group, String actionId)
		throws PortalException, SystemException {

		return getGroupPermission().contains(
			permissionChecker, group, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException, SystemException {

		return getGroupPermission().contains(
			permissionChecker, groupId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, String actionId) {

		return getGroupPermission().contains(permissionChecker, actionId);
	}

	public static GroupPermission getGroupPermission() {
		PortalRuntimePermission.checkGetBeanProperty(GroupPermissionUtil.class);

		return _groupPermission;
	}

	public void setGroupPermission(GroupPermission groupPermission) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_groupPermission = groupPermission;
	}

	private static GroupPermission _groupPermission;

}