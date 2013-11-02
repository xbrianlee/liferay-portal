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

import java.util.Comparator;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class MethodComparator implements Comparator<Method> {

	@Override
	public int compare(Method method1, Method method2) {
		String name1 = method1.getName();
		String name2 = method2.getName();

		int value = name1.compareTo(name2);

		if (value != 0) {
			return value;
		}

		Class<?>[] parameterTypes1 = method1.getParameterTypes();
		Class<?>[] parameterTypes2 = method2.getParameterTypes();

		int index = 0;

		while ((index < parameterTypes1.length) &&
			   (index < parameterTypes2.length)) {

			Class<?> parameterType1 = parameterTypes1[index];
			Class<?> parameterType2 = parameterTypes2[index];

			String parameterTypeName1 = parameterType1.getName();
			String parameterTypeName2 = parameterType2.getName();

			value = parameterTypeName1.compareTo(parameterTypeName2);

			if (value != 0) {
				return value;
			}

			index++;
		}

		if (index < (parameterTypes1.length -1)) {
			return -1;
		}
		else {
			return 1;
		}
	}

}