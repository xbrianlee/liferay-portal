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
import com.liferay.portlet.softwarecatalog.model.SCProductEntry;
import com.liferay.portlet.softwarecatalog.service.SCProductEntryLocalServiceUtil;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SCProductEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long productEntryId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, productEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, SCProductEntry productEntry,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, productEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long productEntryId,
			String actionId)
		throws PortalException, SystemException {

		SCProductEntry productEntry =
			SCProductEntryLocalServiceUtil.getProductEntry(productEntryId);

		return contains(permissionChecker, productEntry, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, SCProductEntry productEntry,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				productEntry.getCompanyId(), SCProductEntry.class.getName(),
				productEntry.getProductEntryId(), productEntry.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			productEntry.getGroupId(), SCProductEntry.class.getName(),
			productEntry.getProductEntryId(), actionId);
	}

}