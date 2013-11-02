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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 * @author Connor McKay
 */
public class PersistedModelLocalServiceRegistryImpl
	implements PersistedModelLocalServiceRegistry {

	@Override
	public PersistedModelLocalService getPersistedModelLocalService(
		String className) {

		return _persistedModelLocalServices.get(className);
	}

	@Override
	public List<PersistedModelLocalService> getPersistedModelLocalServices() {
		return ListUtil.fromMapValues(_persistedModelLocalServices);
	}

	@Override
	public boolean isPermissionedModelLocalService(String className) {
		PersistedModelLocalService persistedModelLocalService =
			getPersistedModelLocalService(className);

		if (persistedModelLocalService == null) {
			return false;
		}

		if (persistedModelLocalService instanceof
				PermissionedModelLocalService) {

			return true;
		}

		return false;
	}

	@Override
	public void register(
		String className,
		PersistedModelLocalService persistedModelLocalService) {

		PersistedModelLocalService oldPersistedModelLocalService =
			_persistedModelLocalServices.put(
				className, persistedModelLocalService);

		if ((oldPersistedModelLocalService != null) && _log.isWarnEnabled()) {
			_log.warn("Duplicate class name " + className);
		}
	}

	@Override
	public void unregister(String className) {
		_persistedModelLocalServices.remove(className);
	}

	private static Log _log = LogFactoryUtil.getLog(
		PersistedModelLocalServiceRegistryImpl.class);

	private Map<String, PersistedModelLocalService>
		_persistedModelLocalServices =
			new ConcurrentHashMap<String, PersistedModelLocalService>();

}