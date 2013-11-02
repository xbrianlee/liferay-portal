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

import java.lang.reflect.Method;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class MethodCache {

	public static void reset() {
		_methods.clear();
	}

	/**
	 * @see MethodKey
	 */
	protected static Method get(MethodKey methodKey)
		throws NoSuchMethodException {

		Method method = _methods.get(methodKey);

		if (method == null) {
			Class<?> declaringClass = methodKey.getDeclaringClass();

			method = declaringClass.getDeclaredMethod(
				methodKey.getMethodName(), methodKey.getParameterTypes());

			if (!method.isAccessible()) {
				method.setAccessible(true);
			}

			_methods.put(methodKey, method);
		}

		return method;
	}

	private static Map<MethodKey, Method> _methods =
		new ConcurrentHashMap<MethodKey, Method>();

}