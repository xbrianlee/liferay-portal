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

package com.liferay.portlet.dynamicdatalists.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;

/**
 * @author Marcellus Tavares
 */
public class DDLRecordSetPermission {

	public static void check(
			PermissionChecker permissionChecker, DDLRecordSet recordSet,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, recordSet, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long recordSetId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, recordSetId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId,
			String recordSetKey, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, groupId, recordSetKey, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, DDLRecordSet recordSet,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, recordSet.getGroupId(),
			DDLRecordSet.class.getName(), recordSet.getRecordSetId(),
			PortletKeys.DYNAMIC_DATA_LISTS, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				recordSet.getCompanyId(), DDLRecordSet.class.getName(),
				recordSet.getRecordSetId(), recordSet.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			recordSet.getGroupId(), DDLRecordSet.class.getName(),
			recordSet.getRecordSetId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long recordSetId,
			String actionId)
		throws PortalException, SystemException {

		DDLRecordSet recordSet = DDLRecordSetLocalServiceUtil.getRecordSet(
			recordSetId);

		return contains(permissionChecker, recordSet, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId,
			String recordSetKey, String actionId)
		throws PortalException, SystemException {

		DDLRecordSet recordSet = DDLRecordSetLocalServiceUtil.getRecordSet(
			groupId, recordSetKey);

		return contains(permissionChecker, recordSet, actionId);
	}

}