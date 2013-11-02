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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
public class InstancePool {

	public static boolean contains(String className) {
		return _instance._contains(className);
	}

	public static Object get(String className) {
		return _instance._get(className);
	}

	public static Object get(String className, boolean logErrors) {
		return _instance._get(className, logErrors);
	}

	public static void put(String className, Object obj) {
		_instance._put(className, obj);
	}

	public static void reset() {
		_instance._reset();
	}

	private InstancePool() {
		_instances = new ConcurrentHashMap<String, Object>();
	}

	private boolean _contains(String className) {
		className = className.trim();

		return _instances.containsKey(className);
	}

	private Object _get(String className) {
		return _get(className, true);
	}

	private Object _get(String className, boolean logErrors) {
		className = className.trim();

		Object instance = _instances.get(className);

		if (instance != null) {
			return instance;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		try {
			Class<?> clazz = portalClassLoader.loadClass(className);

			instance = clazz.newInstance();

			_instances.put(className, instance);
		}
		catch (Exception e1) {
			if (logErrors && _log.isWarnEnabled()) {
				_log.warn(
					"Unable to load " + className +
						" with the portal class loader",
					e1);
			}

			Thread currentThread = Thread.currentThread();

			ClassLoader contextClassLoader =
				currentThread.getContextClassLoader();

			try {
				Class<?> clazz = contextClassLoader.loadClass(className);

				instance = clazz.newInstance();

				_instances.put(className, instance);
			}
			catch (Exception e2) {
				if (logErrors) {
					_log.error(
						"Unable to load " + className +
							" with the portal class loader or the " +
								"current context class loader",
						e2);
				}
			}
		}

		return instance;
	}

	private void _put(String className, Object obj) {
		className = className.trim();

		_instances.put(className, obj);
	}

	private void _reset() {
		_instances.clear();
	}

	private static Log _log = LogFactoryUtil.getLog(InstancePool.class);

	private static InstancePool _instance = new InstancePool();

	private final Map<String, Object> _instances;

}