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

package com.liferay.portlet.messageboards.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.model.MBBan;
import com.liferay.portlet.messageboards.service.base.MBBanServiceBaseImpl;
import com.liferay.portlet.messageboards.service.permission.MBPermission;

/**
 * @author Brian Wing Shun Chan
 */
public class MBBanServiceImpl extends MBBanServiceBaseImpl {

	@Override
	public MBBan addBan(long banUserId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		MBPermission.check(
			permissionChecker, serviceContext.getScopeGroupId(),
			ActionKeys.BAN_USER);

		User banUser = userPersistence.findByPrimaryKey(banUserId);

		boolean groupAdmin = false;

		try {
			groupAdmin = PortalUtil.isGroupAdmin(
				banUser, serviceContext.getScopeGroupId());
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		if (groupAdmin) {
			throw new PrincipalException();
		}

		return mbBanLocalService.addBan(getUserId(), banUserId, serviceContext);
	}

	@Override
	public void deleteBan(long banUserId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MBPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.BAN_USER);

		mbBanLocalService.deleteBan(banUserId, serviceContext);
	}

}