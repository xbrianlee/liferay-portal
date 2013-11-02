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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Zsolt Berentey
 */
public class SocialRequestPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long requestId,
			String actionId)
		throws PortalException, SystemException {

		getSocialRequestPermission().check(
			permissionChecker, requestId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long requestId,
			String actionId)
		throws PortalException, SystemException {

		return getSocialRequestPermission().contains(
			permissionChecker, requestId, actionId);
	}

	public static SocialRequestPermission getSocialRequestPermission() {
		return _socialRequestPermission;
	}

	public void setSocialRequestPermission(
		SocialRequestPermission socialRequestPermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_socialRequestPermission = socialRequestPermission;
	}

	private static SocialRequestPermission _socialRequestPermission;

}