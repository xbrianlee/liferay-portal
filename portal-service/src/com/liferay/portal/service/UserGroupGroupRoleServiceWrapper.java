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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link UserGroupGroupRoleService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupGroupRoleService
 * @generated
 */
@ProviderType
public class UserGroupGroupRoleServiceWrapper
	implements UserGroupGroupRoleService,
		ServiceWrapper<UserGroupGroupRoleService> {
	public UserGroupGroupRoleServiceWrapper(
		UserGroupGroupRoleService userGroupGroupRoleService) {
		_userGroupGroupRoleService = userGroupGroupRoleService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _userGroupGroupRoleService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_userGroupGroupRoleService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public void addUserGroupGroupRoles(long userGroupId, long groupId,
		long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_userGroupGroupRoleService.addUserGroupGroupRoles(userGroupId, groupId,
			roleIds);
	}

	@Override
	public void addUserGroupGroupRoles(long[] userGroupIds, long groupId,
		long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_userGroupGroupRoleService.addUserGroupGroupRoles(userGroupIds,
			groupId, roleId);
	}

	@Override
	public void deleteUserGroupGroupRoles(long userGroupId, long groupId,
		long[] roleIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_userGroupGroupRoleService.deleteUserGroupGroupRoles(userGroupId,
			groupId, roleIds);
	}

	@Override
	public void deleteUserGroupGroupRoles(long[] userGroupIds, long groupId,
		long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_userGroupGroupRoleService.deleteUserGroupGroupRoles(userGroupIds,
			groupId, roleId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public UserGroupGroupRoleService getWrappedUserGroupGroupRoleService() {
		return _userGroupGroupRoleService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedUserGroupGroupRoleService(
		UserGroupGroupRoleService userGroupGroupRoleService) {
		_userGroupGroupRoleService = userGroupGroupRoleService;
	}

	@Override
	public UserGroupGroupRoleService getWrappedService() {
		return _userGroupGroupRoleService;
	}

	@Override
	public void setWrappedService(
		UserGroupGroupRoleService userGroupGroupRoleService) {
		_userGroupGroupRoleService = userGroupGroupRoleService;
	}

	private UserGroupGroupRoleService _userGroupGroupRoleService;
}