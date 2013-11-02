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

package com.liferay.portlet.dynamicdatamapping.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortletKeys;

/**
 * @author Bruno Basto
 */
public class DDMPermission {

	public static final String RESOURCE_NAME =
		"com.liferay.portlet.dynamicdatamapping";

	public static void check(
			PermissionChecker permissionChecker, long groupId, String name,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, name, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String name,
		String actionId) {

		Boolean hasPermission = null;

		if (actionId.equals(ActionKeys.ADD_PORTLET_DISPLAY_TEMPLATE)) {
			hasPermission = StagingPermissionUtil.hasPermission(
				permissionChecker, groupId, RESOURCE_NAME, groupId,
				PortletKeys.PORTLET_DISPLAY_TEMPLATES, actionId);
		}
		else {
			hasPermission = StagingPermissionUtil.hasPermission(
				permissionChecker, groupId, RESOURCE_NAME, groupId, name,
				actionId);
		}

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		return permissionChecker.hasPermission(
			groupId, name, groupId, actionId);
	}

}