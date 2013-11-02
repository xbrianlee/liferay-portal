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

import java.lang.reflect.Constructor;

/**
 * @author Brian Wing Shun Chan
 */
public class InstanceFactory {

	public static Object newInstance(ClassLoader classLoader, String className)
		throws Exception {

		return newInstance(
			classLoader, className, (Class<?>[])null, (Object[])null);
	}

	public static Object newInstance(
			ClassLoader classLoader, String className, Class<?> parameterType,
			Object argument)
		throws Exception {

		return newInstance(
			classLoader, className, new Class<?>[] {parameterType},
			new Object[] {argument});
	}

	public static Object newInstance(
			ClassLoader classLoader, String className,
			Class<?>[] parameterTypes, Object[] arguments)
		throws Exception {

		if (classLoader == null) {
			Thread currentThread = Thread.currentThread();

			classLoader = currentThread.getContextClassLoader();
		}

		Class<?> clazz = classLoader.loadClass(className);

		if ((parameterTypes != null) && (arguments != null) &&
			(parameterTypes.length > 0) && (arguments.length > 0) &&
			(parameterTypes.length == arguments.length)) {

			Constructor<?> constructor = clazz.getConstructor(parameterTypes);

			return constructor.newInstance(arguments);
		}
		else {
			return clazz.newInstance();
		}
	}

	public static Object newInstance(String className) throws Exception {
		return newInstance(null, className);
	}

	public static Object newInstance(
			String className, Class<?> parameterType, Object argument)
		throws Exception {

		return newInstance(
			null, className, new Class<?>[] {parameterType},
			new Object[] {argument});
	}

	public static Object newInstance(
			String className, Class<?>[] parameterTypes, Object[] arguments)
		throws Exception {

		return newInstance(null, className, parameterTypes, arguments);
	}

}