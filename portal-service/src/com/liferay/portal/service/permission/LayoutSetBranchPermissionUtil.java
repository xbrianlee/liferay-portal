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
import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutSetBranchPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker,
			LayoutSetBranch layoutSetBranch, String actionId)
		throws PortalException {

		getLayoutSetBranchPermission().check(
			permissionChecker, layoutSetBranch, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, long layoutSetBranchId,
			String actionId)
		throws PortalException, SystemException {

		getLayoutSetBranchPermission().check(
			permissionChecker, layoutSetBranchId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, LayoutSetBranch layoutSetBranch,
		String actionId) {

		return getLayoutSetBranchPermission().contains(
			permissionChecker, layoutSetBranch, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long layoutSetBranchId,
			String actionId)
		throws PortalException, SystemException {

		return getLayoutSetBranchPermission().contains(
			permissionChecker, layoutSetBranchId, actionId);
	}

	public static LayoutSetBranchPermission getLayoutSetBranchPermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			LayoutSetBranchPermissionUtil.class);

		return _layoutSetBranchPermission;
	}

	public void setLayoutSetBranchPermission(
		LayoutSetBranchPermission layoutSetBranchPermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_layoutSetBranchPermission = layoutSetBranchPermission;
	}

	private static LayoutSetBranchPermission _layoutSetBranchPermission;

}