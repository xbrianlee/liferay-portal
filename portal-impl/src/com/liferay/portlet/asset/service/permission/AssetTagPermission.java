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

package com.liferay.portlet.asset.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;

/**
 * @author Eduardo Lundgren
 */
public class AssetTagPermission {

	public static void check(
			PermissionChecker permissionChecker, AssetTag tag, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, tag, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long tagId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, tagId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, AssetTag tag, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				tag.getCompanyId(), AssetTag.class.getName(), tag.getTagId(),
				tag.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			tag.getGroupId(), AssetTag.class.getName(), tag.getTagId(),
			actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long tagId, String actionId)
		throws PortalException, SystemException {

		AssetTag tag = AssetTagLocalServiceUtil.getTag(tagId);

		return contains(permissionChecker, tag, actionId);
	}

}