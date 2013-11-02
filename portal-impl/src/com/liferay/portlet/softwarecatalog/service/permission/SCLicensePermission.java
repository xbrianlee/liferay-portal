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
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.softwarecatalog.model.SCLicense;
import com.liferay.portlet.softwarecatalog.service.SCLicenseLocalServiceUtil;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SCLicensePermission {

	public static void check(
			PermissionChecker permissionChecker, long productEntryId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, productEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, SCLicense license,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, license, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long licenseId,
			String actionId)
		throws PortalException, SystemException {

		SCLicense license = SCLicenseLocalServiceUtil.getLicense(licenseId);

		return contains(permissionChecker, license, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, SCLicense license,
		String actionId) {

		return permissionChecker.hasPermission(
			GroupConstants.DEFAULT_PARENT_GROUP_ID, SCLicense.class.getName(),
			license.getLicenseId(), actionId);
	}

}