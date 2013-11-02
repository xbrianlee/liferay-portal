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
import com.liferay.portal.model.Organization;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Brian Wing Shun Chan
 */
public class OrganizationPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long organizationId,
			String actionId)
		throws PortalException, SystemException {

		getOrganizationPermission().check(
			permissionChecker, organizationId, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, Organization organization,
			String actionId)
		throws PortalException, SystemException {

		getOrganizationPermission().check(
			permissionChecker, organization, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long organizationId,
			String actionId)
		throws PortalException, SystemException {

		return getOrganizationPermission().contains(
			permissionChecker, organizationId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long[] organizationIds,
			String actionId)
		throws PortalException, SystemException {

		return getOrganizationPermission().contains(
			permissionChecker, organizationIds, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Organization organization,
			String actionId)
		throws PortalException, SystemException {

		return getOrganizationPermission().contains(
			permissionChecker, organization, actionId);
	}

	public static OrganizationPermission getOrganizationPermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			OrganizationPermissionUtil.class);

		return _organizationPermission;
	}

	public void setOrganizationPermission(
		OrganizationPermission organizationPermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_organizationPermission = organizationPermission;
	}

	private static OrganizationPermission _organizationPermission;

}