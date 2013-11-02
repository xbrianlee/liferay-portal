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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public class DefaultThreadLocalBinder implements ThreadLocalBinder {

	public void afterPropertiesSet() throws Exception {
		if (_threadLocalSources == null) {
			throw new IllegalArgumentException("Thread local sources is null");
		}

		init(getClassLoader());
	}

	@Override
	public void bind() {
		Map<ThreadLocal<?>, ?> threadLocalValues = _threadLocalValues.get();

		for (Map.Entry<ThreadLocal<?>, ?> entry :
				threadLocalValues.entrySet()) {

			ThreadLocal<Object> threadLocal =
				(ThreadLocal<Object>)entry.getKey();
			Object value = entry.getValue();

			threadLocal.set(value);
		}
	}

	@Override
	public void cleanUp() {
		for (ThreadLocal<?> threadLocal : _threadLocals) {
			threadLocal.remove();
		}
	}

	public ClassLoader getClassLoader() {
		if (_classLoader == null) {
			Thread currentThread = Thread.currentThread();

			_classLoader = currentThread.getContextClassLoader();
		}

		return _classLoader;
	}

	public void init(ClassLoader classLoader) throws Exception {
		for (Map.Entry<String, String> entry : _threadLocalSources.entrySet()) {
			String className = entry.getKey();
			String fieldName = entry.getValue();

			Class<?> clazz = classLoader.loadClass(className);

			Field field = ReflectionUtil.getDeclaredField(clazz, fieldName);

			if (!ThreadLocal.class.isAssignableFrom(field.getType())) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						fieldName +
							" is not of type ThreadLocal. Skip binding.");
				}

				continue;
			}

			if (!Modifier.isStatic(field.getModifiers())) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						fieldName +
							" is not a static ThreadLocal. Skip binding.");
				}

				continue;
			}

			ThreadLocal<?> threadLocal = (ThreadLocal<?>)field.get(null);

			if (threadLocal == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(fieldName + " is not initialized. Skip binding.");
				}

				continue;
			}

			_threadLocals.add(threadLocal);
		}
	}

	@Override
	public void record() {
		Map<ThreadLocal<?>, Object> threadLocalValues =
			new HashMap<ThreadLocal<?>, Object>();

		for (ThreadLocal<?> threadLocal : _threadLocals) {
			Object value = threadLocal.get();

			threadLocalValues.put(threadLocal, value);
		}

		_threadLocalValues.set(threadLocalValues);
	}

	public void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public void setThreadLocalSources(Map<String, String> threadLocalSources) {
		_threadLocalSources = threadLocalSources;
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultThreadLocalBinder.class);

	private static ThreadLocal<Map<ThreadLocal<?>, ?>> _threadLocalValues =
		new AutoResetThreadLocal<Map<ThreadLocal<?>, ?>>(
			DefaultThreadLocalBinder.class + "._threadLocalValueMap") {

			@Override
			protected Map<ThreadLocal<?>, ?> copy(
				Map<ThreadLocal<?>, ?> threadLocalValueMap) {

				return threadLocalValueMap;
			}

		};

	private ClassLoader _classLoader;
	private Set<ThreadLocal<?>> _threadLocals = new HashSet<ThreadLocal<?>>();
	private Map<String, String> _threadLocalSources;

}