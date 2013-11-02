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
import com.liferay.portal.model.Account;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException, SystemException {

		getAccountPermission().check(permissionChecker, account, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, long accountId,
			String actionId)
		throws PortalException, SystemException {

		getAccountPermission().check(permissionChecker, accountId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException, SystemException {

		return getAccountPermission().contains(
			permissionChecker, account, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long accountId,
			String actionId)
		throws PortalException, SystemException {

		return getAccountPermission().contains(
			permissionChecker, accountId, actionId);
	}

	public static AccountPermission getAccountPermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			AccountPermissionUtil.class);

		return _accountPermission;
	}

	public void setAccountPermission(AccountPermission accountPermission) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_accountPermission = accountPermission;
	}

	private static AccountPermission _accountPermission;

}