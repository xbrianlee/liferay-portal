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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.base.UserGroupGroupRoleServiceBaseImpl;

/**
 * @author Brett Swaim
 */
public class UserGroupGroupRoleServiceImpl
	extends UserGroupGroupRoleServiceBaseImpl {

	@Override
	public void addUserGroupGroupRoles(
			long userGroupId, long groupId, long[] roleIds)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isGroupOwner(groupId)) {
			throw new PrincipalException();
		}

		userGroupGroupRoleLocalService.addUserGroupGroupRoles(
			userGroupId, groupId, roleIds);
	}

	@Override
	public void addUserGroupGroupRoles(
			long[] userGroupIds, long groupId, long roleId)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isGroupOwner(groupId)) {
			throw new PrincipalException();
		}

		userGroupGroupRoleLocalService.addUserGroupGroupRoles(
			userGroupIds, groupId, roleId);
	}

	@Override
	public void deleteUserGroupGroupRoles(
			long userGroupId, long groupId, long[] roleIds)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isGroupOwner(groupId)) {
			throw new PrincipalException();
		}

		userGroupGroupRoleLocalService.deleteUserGroupGroupRoles(
			userGroupId, groupId, roleIds);
	}

	@Override
	public void deleteUserGroupGroupRoles(
			long[] userGroupIds, long groupId, long roleId)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isGroupOwner(groupId)) {
			throw new PrincipalException();
		}

		userGroupGroupRoleLocalService.deleteUserGroupGroupRoles(
			userGroupIds, groupId, roleId);
	}

}