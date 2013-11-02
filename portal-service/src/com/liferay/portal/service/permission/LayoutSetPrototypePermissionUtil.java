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
 * @author Brian Wing Shun Chan
 */
public class LayoutSetPrototypePermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long layoutSetPrototypeId,
			String actionId)
		throws PrincipalException {

		getLayoutSetPrototypePermission().check(
			permissionChecker, layoutSetPrototypeId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long layoutSetPrototypeId,
		String actionId) {

		return getLayoutSetPrototypePermission().contains(
			permissionChecker, layoutSetPrototypeId, actionId);
	}

	public static LayoutSetPrototypePermission
		getLayoutSetPrototypePermission() {

		PortalRuntimePermission.checkGetBeanProperty(
			LayoutSetPrototypePermissionUtil.class);

		return _layoutSetPrototypePermission;
	}

	public void setLayoutSetPrototypePermission(
		LayoutSetPrototypePermission layoutSetPrototypePermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_layoutSetPrototypePermission = layoutSetPrototypePermission;
	}

	private static LayoutSetPrototypePermission _layoutSetPrototypePermission;

}