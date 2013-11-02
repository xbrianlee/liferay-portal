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

package com.liferay.portal.service;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Connor McKay
 */
public class PersistedModelLocalServiceRegistryUtil {

	public static PersistedModelLocalService getPersistedModelLocalService(
		String className) {

		return getPersistedModelLocalServiceRegistry().
			getPersistedModelLocalService(className);
	}

	public static PersistedModelLocalServiceRegistry
		getPersistedModelLocalServiceRegistry() {

		return _persistedModelLocalServiceRegistry;
	}

	public static List<PersistedModelLocalService>
		getPersistedModelLocalServices() {

		return getPersistedModelLocalServiceRegistry().
			getPersistedModelLocalServices();
	}

	public static boolean isPermissionedModelLocalService(String className) {
		return getPersistedModelLocalServiceRegistry().
			isPermissionedModelLocalService(className);
	}

	public static void register(
		String className,
		PersistedModelLocalService persistedModelLocalService) {

		getPersistedModelLocalServiceRegistry().register(
			className, persistedModelLocalService);
	}

	public static void unregister(String className) {
		getPersistedModelLocalServiceRegistry().unregister(className);
	}

	public void setPersistedModelLocalServiceRegistry(
		PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_persistedModelLocalServiceRegistry =
			persistedModelLocalServiceRegistry;
	}

	private static PersistedModelLocalServiceRegistry
		_persistedModelLocalServiceRegistry;

}