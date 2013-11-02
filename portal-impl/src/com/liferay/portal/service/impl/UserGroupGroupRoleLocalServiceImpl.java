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

import com.liferay.portal.NoSuchUserGroupGroupRoleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.UserGroupGroupRole;
import com.liferay.portal.security.permission.PermissionCacheUtil;
import com.liferay.portal.service.base.UserGroupGroupRoleLocalServiceBaseImpl;
import com.liferay.portal.service.persistence.UserGroupGroupRolePK;

import java.util.List;

/**
 * @author Brett Swaim
 */
public class UserGroupGroupRoleLocalServiceImpl
	extends UserGroupGroupRoleLocalServiceBaseImpl {

	@Override
	public void addUserGroupGroupRoles(
			long userGroupId, long groupId, long[] roleIds)
		throws SystemException {

		for (long roleId : roleIds) {
			UserGroupGroupRolePK pk = new UserGroupGroupRolePK(
				userGroupId, groupId, roleId);

			UserGroupGroupRole userGroupGroupRole =
				userGroupGroupRolePersistence.fetchByPrimaryKey(pk);

			if (userGroupGroupRole == null) {
				userGroupGroupRole = userGroupGroupRolePersistence.create(pk);

				userGroupGroupRolePersistence.update(userGroupGroupRole);
			}
		}

		PermissionCacheUtil.clearCache();
	}

	@Override
	public void addUserGroupGroupRoles(
			long[] userGroupIds, long groupId, long roleId)
		throws SystemException {

		for (long userGroupId : userGroupIds) {
			UserGroupGroupRolePK pk = new UserGroupGroupRolePK(
				userGroupId, groupId, roleId);

			UserGroupGroupRole userGroupGroupRole =
				userGroupGroupRolePersistence.fetchByPrimaryKey(pk);

			if (userGroupGroupRole == null) {
				userGroupGroupRole = userGroupGroupRolePersistence.create(pk);

				userGroupGroupRolePersistence.update(userGroupGroupRole);
			}
		}

		PermissionCacheUtil.clearCache();
	}

	@Override
	public UserGroupGroupRole deleteUserGroupGroupRole(
			UserGroupGroupRole userGroupGroupRole)
		throws SystemException {

		userGroupGroupRolePersistence.remove(userGroupGroupRole);

		PermissionCacheUtil.clearCache();

		return userGroupGroupRole;
	}

	@Override
	public void deleteUserGroupGroupRoles(
			long userGroupId, long groupId, long[] roleIds)
		throws SystemException {

		for (long roleId : roleIds) {
			UserGroupGroupRolePK pk = new UserGroupGroupRolePK(
				userGroupId, groupId, roleId);

			try {
				userGroupGroupRolePersistence.remove(pk);
			}
			catch (NoSuchUserGroupGroupRoleException nsuggre) {
			}
		}

		PermissionCacheUtil.clearCache();
	}

	@Override
	public void deleteUserGroupGroupRoles(long userGroupId, long[] groupIds)
		throws SystemException {

		for (long groupId : groupIds) {
			userGroupGroupRolePersistence.removeByU_G(userGroupId, groupId);
		}

		PermissionCacheUtil.clearCache();
	}

	@Override
	public void deleteUserGroupGroupRoles(long[] userGroupIds, long groupId)
		throws SystemException {

		for (long userGroupId : userGroupIds) {
			userGroupGroupRolePersistence.removeByU_G(userGroupId, groupId);
		}

		PermissionCacheUtil.clearCache();
	}

	@Override
	public void deleteUserGroupGroupRoles(
			long[] userGroupIds, long groupId, long roleId)
		throws SystemException {

		for (long userGroupId : userGroupIds) {
			UserGroupGroupRolePK pk = new UserGroupGroupRolePK(
				userGroupId, groupId, roleId);

			try {
				userGroupGroupRolePersistence.remove(pk);
			}
			catch (NoSuchUserGroupGroupRoleException nsuggre) {
			}
		}

		PermissionCacheUtil.clearCache();
	}

	@Override
	public void deleteUserGroupGroupRolesByGroupId(long groupId)
		throws SystemException {

		userGroupGroupRolePersistence.removeByGroupId(groupId);

		PermissionCacheUtil.clearCache();
	}

	@Override
	public void deleteUserGroupGroupRolesByRoleId(long roleId)
		throws SystemException {

		userGroupGroupRolePersistence.removeByRoleId(roleId);

		PermissionCacheUtil.clearCache();
	}

	@Override
	public void deleteUserGroupGroupRolesByUserGroupId(long userGroupId)
		throws SystemException {

		userGroupGroupRolePersistence.removeByUserGroupId(userGroupId);

		PermissionCacheUtil.clearCache();
	}

	@Override
	public List<UserGroupGroupRole> getUserGroupGroupRoles(long userGroupId)
		throws SystemException {

		return userGroupGroupRolePersistence.findByUserGroupId(userGroupId);
	}

	@Override
	public List<UserGroupGroupRole> getUserGroupGroupRoles(
			long userGroupId, long groupId)
		throws SystemException {

		return userGroupGroupRolePersistence.findByU_G(userGroupId, groupId);
	}

	@Override
	public List<UserGroupGroupRole> getUserGroupGroupRolesByGroupAndRole(
			long groupId, long roleId)
		throws SystemException {

		return userGroupGroupRolePersistence.findByG_R(groupId, roleId);
	}

	@Override
	public boolean hasUserGroupGroupRole(
			long userGroupId, long groupId, long roleId)
		throws SystemException {

		UserGroupGroupRolePK pk = new UserGroupGroupRolePK(
			userGroupId, groupId, roleId);

		UserGroupGroupRole userGroupGroupRole =
			userGroupGroupRolePersistence.fetchByPrimaryKey(pk);

		if (userGroupGroupRole != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasUserGroupGroupRole(
			long userGroupId, long groupId, String roleName)
		throws PortalException, SystemException {

		UserGroup userGroup = userGroupPersistence.findByPrimaryKey(
			userGroupId);

		long companyId = userGroup.getCompanyId();

		Role role = rolePersistence.findByC_N(companyId, roleName);

		long roleId = role.getRoleId();

		return hasUserGroupGroupRole(userGroupId, groupId, roleId);
	}

}