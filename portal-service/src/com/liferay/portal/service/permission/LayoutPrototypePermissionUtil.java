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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Jorge Ferrer
 */
public class LayoutPrototypePermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long layoutPrototypeId,
			String actionId)
		throws PrincipalException {

		getLayoutPrototypePermission().check(
			permissionChecker, layoutPrototypeId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long layoutPrototypeId,
		String actionId) {

		return getLayoutPrototypePermission().contains(
			permissionChecker, layoutPrototypeId, actionId);
	}

	public static LayoutPrototypePermission getLayoutPrototypePermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			LayoutPrototypePermissionUtil.class);

		return _layoutPrototypePermission;
	}

	public void setLayoutPrototypePermission(
		LayoutPrototypePermission layoutPrototypePermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_layoutPrototypePermission = layoutPrototypePermission;
	}

	private static LayoutPrototypePermission _layoutPrototypePermission;

}