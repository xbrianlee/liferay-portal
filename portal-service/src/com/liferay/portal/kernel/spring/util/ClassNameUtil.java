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

package com.liferay.portal.kernel.spring.util;

/**
 * @author Shuyang Zhou
 */
public class ClassNameUtil {

	public static String getClassName(String className)
		throws ClassNotFoundException {

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		return getClassName(className, classLoader);
	}

	public static String getClassName(String className, ClassLoader classLoader)
		throws ClassNotFoundException {

		Class<?> clazz = Class.forName(className, false, classLoader);

		return clazz.getName();
	}

	public static String getSimpleClassName(String className)
		throws ClassNotFoundException {

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		return getSimpleClassName(className, classLoader);
	}

	public static String getSimpleClassName(
			String className, ClassLoader classLoader)
		throws ClassNotFoundException {

		Class<?> clazz = Class.forName(className, false, classLoader);

		return clazz.getSimpleName();
	}

}