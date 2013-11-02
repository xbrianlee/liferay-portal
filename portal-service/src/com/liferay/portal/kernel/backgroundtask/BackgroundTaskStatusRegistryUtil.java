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

package com.liferay.portal.kernel.backgroundtask;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskStatusRegistryUtil {

	public static BackgroundTaskStatus getBackgroundTaskStatus(
		long backgroundTaskId) {

		return getBackgroundTaskStatusRegistry().getBackgroundTaskStatus(
			backgroundTaskId);
	}

	public static BackgroundTaskStatusRegistry
		getBackgroundTaskStatusRegistry() {

		PortalRuntimePermission.checkGetBeanProperty(
			BackgroundTaskStatusRegistryUtil.class);

		return _backgroundTaskStatusRegistry;
	}

	public static BackgroundTaskStatus registerBackgroundTaskStatus(
		long backgroundTaskId) {

		return getBackgroundTaskStatusRegistry().registerBackgroundTaskStatus(
			backgroundTaskId);
	}

	public static BackgroundTaskStatus unregisterBackgroundTaskStatus(
		long backgroundTaskId) {

		return getBackgroundTaskStatusRegistry().unregisterBackgroundTaskStatus(
			backgroundTaskId);
	}

	public void setBackgroundTaskStatusRegistry(
		BackgroundTaskStatusRegistry backgroundTaskStatusRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_backgroundTaskStatusRegistry = backgroundTaskStatusRegistry;
	}

	private static BackgroundTaskStatusRegistry _backgroundTaskStatusRegistry;

}