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

package com.liferay.portlet.social.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Zsolt Berentey
 */
public class SocialActivityPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		getSocialActivityPermission().check(
			permissionChecker, groupId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, String actionId) {

		return getSocialActivityPermission().contains(
			permissionChecker, groupId, actionId);
	}

	public static SocialActivityPermission getSocialActivityPermission() {
		return _socialActivityPermission;
	}

	public void setSocialActivityPermission(
		SocialActivityPermission socialActivityPermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_socialActivityPermission = socialActivityPermission;
	}

	private static SocialActivityPermission _socialActivityPermission;

}