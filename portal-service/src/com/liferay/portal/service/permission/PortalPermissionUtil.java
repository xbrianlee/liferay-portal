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
public class PortalPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, String actionId)
		throws PrincipalException {

		getPortalPermission().check(permissionChecker, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, String actionId) {

		return getPortalPermission().contains(permissionChecker, actionId);
	}

	public static PortalPermission getPortalPermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortalPermissionUtil.class);

		return _portalPermission;
	}

	public void setPortalPermission(PortalPermission portalPermission) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portalPermission = portalPermission;
	}

	private static PortalPermission _portalPermission;

}