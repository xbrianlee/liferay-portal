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
 */
public class CommonPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long classNameId, long classPK,
			String actionId)
		throws PortalException, SystemException {

		getCommonPermission().check(
			permissionChecker, classNameId, classPK, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, String className, long classPK,
			String actionId)
		throws PortalException, SystemException {

		getCommonPermission().check(
			permissionChecker, className, classPK, actionId);
	}

	public static CommonPermission getCommonPermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			CommonPermissionUtil.class);

		return _commonPermission;
	}

	public void setCommonPermission(CommonPermission commonPermission) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_commonPermission = commonPermission;
	}

	private static CommonPermission _commonPermission;

}