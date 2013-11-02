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

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class PermissionThreadLocal {

	public static PermissionChecker getPermissionChecker() {
		return _permissionChecker.get();
	}

	public static boolean isAddResource() {
		return _addResource.get();
	}

	public static boolean isFlushEnabled() {
		return _flushEnabled.get();
	}

	public static void setAddResource(boolean addResource) {
		_addResource.set(addResource);
	}

	public static void setIndexEnabled(boolean indexEnabled) {
		_flushEnabled.set(indexEnabled);
	}

	public static void setPermissionChecker(
		PermissionChecker permissionChecker) {

		_permissionChecker.set(permissionChecker);
	}

	private static ThreadLocal<Boolean> _addResource =
		new AutoResetThreadLocal<Boolean>(
			PermissionThreadLocal.class + "._addResource", true);
	private static ThreadLocal<Boolean> _flushEnabled =
		new AutoResetThreadLocal<Boolean>(
			PermissionThreadLocal.class + "._flushEnabled", true);

	private static ThreadLocal<PermissionChecker> _permissionChecker =
		new AutoResetThreadLocal<PermissionChecker>(
			PermissionThreadLocal.class + "._permissionChecker") {

				@Override
				protected PermissionChecker copy(
					PermissionChecker permissionChecker) {

					return permissionChecker;
				}

			};

}