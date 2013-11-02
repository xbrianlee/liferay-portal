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

package com.liferay.portlet.journal.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.journal.model.JournalStructure;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class JournalStructurePermission {

	public static void check(
			PermissionChecker permissionChecker, JournalStructure structure,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, structure, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId,
			String structureId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, groupId, structureId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, JournalStructure structure,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				structure.getCompanyId(), JournalStructure.class.getName(),
				structure.getId(), structure.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			structure.getGroupId(), JournalStructure.class.getName(),
			structure.getId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId,
			String structureId, String actionId)
		throws PortalException, SystemException {

		@SuppressWarnings("deprecation")
		JournalStructure structure =
			com.liferay.portlet.journal.service.
				JournalStructureLocalServiceUtil.getStructure(
					groupId, structureId, true);

		return contains(permissionChecker, structure, actionId);
	}

}