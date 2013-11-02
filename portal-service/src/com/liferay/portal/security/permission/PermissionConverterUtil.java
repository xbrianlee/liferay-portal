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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.Role;

import java.util.List;

/**
 * @author Michael C. Han
 */
public class PermissionConverterUtil {

	public static List<Permission> convertPermissions(long roleId)
		throws PortalException, SystemException {

		return getPermissionConverter().convertPermissions(roleId);
	}

	public static List<Permission> convertPermissions(
			long roleId, PermissionConversionFilter permissionConversionFilter)
		throws PortalException, SystemException {

		return getPermissionConverter().convertPermissions(
			roleId, permissionConversionFilter);
	}

	public static List<Permission> convertPermissions(Role role)
		throws PortalException, SystemException {

		return getPermissionConverter().convertPermissions(role);
	}

	public static List<Permission> convertPermissions(
			Role role, PermissionConversionFilter permissionConversionFilter)
		throws PortalException, SystemException {

		return getPermissionConverter().convertPermissions(
			role, permissionConversionFilter);
	}

	public static PermissionConverter getPermissionConverter() {
		PortalRuntimePermission.checkGetBeanProperty(
			PermissionConverterUtil.class);

		return _permissionConverter;
	}

	public void setPermissionConverter(
		PermissionConverter permissionConverter) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_permissionConverter = permissionConverter;
	}

	private static PermissionConverter _permissionConverter;

}