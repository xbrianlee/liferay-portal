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

package com.liferay.portal.kernel.log;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 * @author Raymond Augé
 */
public class LogFactoryUtil {

	public static Log getLog(Class<?> c) {
		return getLog(c.getName());
	}

	public static Log getLog(String name) {

		// The following concurrent collection retrieve has the side effect of a
		// memory fence read. This will invalidate all dirty cache data if there
		// are any. If the LogWrapper swap happens before this, the new Log will
		// be visible to the current Thread.

		LogWrapper logWrapper = _logWrappers.get(name);

		if (logWrapper == null) {
			if (SanitizerLogWrapper.isEnabled()) {
				logWrapper = new SanitizerLogWrapper(_logFactory.getLog(name));
			}
			else {
				logWrapper = new LogWrapper(_logFactory.getLog(name));
			}

			LogWrapper previousLogWrapper = _logWrappers.putIfAbsent(
				name, logWrapper);

			if (previousLogWrapper != null) {
				logWrapper = previousLogWrapper;
			}
		}

		return logWrapper;
	}

	public static LogFactory getLogFactory() {
		PortalRuntimePermission.checkGetBeanProperty(LogFactoryUtil.class);

		return _logFactory;
	}

	public static void setLogFactory(LogFactory logFactory) {
		PortalRuntimePermission.checkSetBeanProperty(LogFactoryUtil.class);

		for (Map.Entry<String, LogWrapper> entry : _logWrappers.entrySet()) {
			String name = entry.getKey();

			LogWrapper logWrapper = entry.getValue();

			logWrapper.setLog(logFactory.getLog(name));
		}

		// The following volatile write will flush out all cache data. All
		// previously swapped LogWrappers will be visible for any reads after a
		// memory fence read according to the happens-before rules.

		_logFactory = logFactory;
	}

	private static volatile LogFactory _logFactory = new Jdk14LogFactoryImpl();

	private static final ConcurrentMap<String, LogWrapper> _logWrappers =
		new ConcurrentHashMap<String, LogWrapper>();

}