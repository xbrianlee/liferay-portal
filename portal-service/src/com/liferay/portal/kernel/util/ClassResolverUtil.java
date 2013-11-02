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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class ClassResolverUtil {

	public static Class<?> resolve(String className, ClassLoader classLoader)
		throws ClassNotFoundException {

		try {
			return Class.forName(className, false, classLoader);
		}
		catch (ClassNotFoundException cnfe) {
			Class<?> clazz = _primitiveClasses.get(className);

			if (clazz != null) {
				return clazz;
			}
			else {
				throw cnfe;
			}
		}
	}

	public static Class<?> resolveByContextClassLoader(String className) {
		Thread thread = Thread.currentThread();

		ClassLoader contextClassLoader = thread.getContextClassLoader();

		try {
			return Class.forName(className, false, contextClassLoader);
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
	}

	public static Class<?> resolveByPortalClassLoader(String className) {
		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		try {
			return Class.forName(className, false, portalClassLoader);
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
	}

	public static Class<?> resolveByPortletClassLoader(
		String className, String servletContextName) {

		ClassLoader classLoader = ClassLoaderPool.getClassLoader(
			servletContextName);

		try {
			return Class.forName(className, false, classLoader);
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
	}

	private static final Map<String, Class<?>> _primitiveClasses =
		new HashMap<String, Class<?>>(9, 1.0F);

	static {
		_primitiveClasses.put("boolean", boolean.class);
		_primitiveClasses.put("byte", byte.class);
		_primitiveClasses.put("char", char.class);
		_primitiveClasses.put("short", short.class);
		_primitiveClasses.put("int", int.class);
		_primitiveClasses.put("long", long.class);
		_primitiveClasses.put("float", float.class);
		_primitiveClasses.put("double", double.class);
		_primitiveClasses.put("void", void.class);
	}

}