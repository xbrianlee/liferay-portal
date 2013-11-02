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

package com.liferay.portlet.softwarecatalog.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion;
import com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalServiceUtil;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SCFrameworkVersionPermission {

	public static void check(
			PermissionChecker permissionChecker, long frameworkVersionId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, frameworkVersionId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker,
			SCFrameworkVersion frameworkVersion, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, frameworkVersion, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long frameworkVersionId,
			String actionId)
		throws PortalException, SystemException {

		SCFrameworkVersion frameworkVersion =
			SCFrameworkVersionLocalServiceUtil.getFrameworkVersion(
				frameworkVersionId);

		return contains(permissionChecker, frameworkVersion, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker,
		SCFrameworkVersion frameworkVersion, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				frameworkVersion.getCompanyId(),
				SCFrameworkVersion.class.getName(),
				frameworkVersion.getFrameworkVersionId(),
				frameworkVersion.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			frameworkVersion.getGroupId(), SCFrameworkVersion.class.getName(),
			frameworkVersion.getFrameworkVersionId(), actionId);
	}

}