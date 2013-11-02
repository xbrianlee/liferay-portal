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

package com.liferay.portal.kernel.lock;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Alexander Chow
 */
public class LockListenerRegistryUtil {

	public static LockListener getLockListener(String className) {
		return getLockListenerRegistry().getLockListener(className);
	}

	public static LockListenerRegistry getLockListenerRegistry() {
		PortalRuntimePermission.checkGetBeanProperty(
			LockListenerRegistryUtil.class);

		return _lockListenerRegistry;
	}

	public static void register(LockListener lockListener) {
		getLockListenerRegistry().register(lockListener);
	}

	public static void unregister(LockListener lockListener) {
		getLockListenerRegistry().unregister(lockListener);
	}

	public void setLockListenerRegistry(
		LockListenerRegistry lockListenerRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_lockListenerRegistry = lockListenerRegistry;
	}

	private static LockListenerRegistry _lockListenerRegistry;

}