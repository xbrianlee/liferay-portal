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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Michael C. Han
 * @author Raymond Augé
 */
public class AuthenticatedUserUUIDStoreUtil {

	public static boolean exists(String userUUID) {
		return getAuthenticatedUserUUIDStore().exists(userUUID);
	}

	public static AuthenticatedUserUUIDStore getAuthenticatedUserUUIDStore() {
		PortalRuntimePermission.checkGetBeanProperty(
			AuthenticatedUserUUIDStoreUtil.class);

		return _authenticatedUserUUIDStore;
	}

	public static boolean register(String userUUID) {
		return getAuthenticatedUserUUIDStore().register(userUUID);
	}

	public static boolean unregister(String userUUID) {
		return getAuthenticatedUserUUIDStore().unregister(userUUID);
	}

	public void setAuthenticatedUserUUIDStore(
		AuthenticatedUserUUIDStore authenticatedUserUUIDStore) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_authenticatedUserUUIDStore = authenticatedUserUUIDStore;
	}

	private static AuthenticatedUserUUIDStore _authenticatedUserUUIDStore;

}