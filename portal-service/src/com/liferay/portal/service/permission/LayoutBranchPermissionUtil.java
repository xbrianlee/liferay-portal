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
import com.liferay.portal.model.LayoutBranch;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class LayoutBranchPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, LayoutBranch layoutBranch,
			String actionId)
		throws PortalException {

		getLayoutBranchPermission().check(
			permissionChecker, layoutBranch, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, long layoutBranchId,
			String actionId)
		throws PortalException, SystemException {

		getLayoutBranchPermission().check(
			permissionChecker, layoutBranchId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, LayoutBranch layoutBranch,
		String actionId) {

		return getLayoutBranchPermission().contains(
			permissionChecker, layoutBranch, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long layoutBranchId,
			String actionId)
		throws PortalException, SystemException {

		return getLayoutBranchPermission().contains(
			permissionChecker, layoutBranchId, actionId);
	}

	public static LayoutBranchPermission getLayoutBranchPermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			LayoutBranchPermissionUtil.class);

		return _layoutBranchPermission;
	}

	public void setLayoutBranchPermission(
		LayoutBranchPermission layoutBranchPermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_layoutBranchPermission = layoutBranchPermission;
	}

	private static LayoutBranchPermission _layoutBranchPermission;

}