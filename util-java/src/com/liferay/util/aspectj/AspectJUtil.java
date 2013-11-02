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

package com.liferay.util.aspectj;

import com.liferay.portal.kernel.util.ServerDetector;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author Brian Wing Shun Chan
 */
public class AspectJUtil {

	public static Method getMethod(MethodSignature methodSignature)
		throws NoSuchMethodException {

		Method method = null;

		if (ServerDetector.isWebSphere()) {
			Class<?> declaringType = methodSignature.getDeclaringType();
			String name = methodSignature.getName();
			Class<?>[] parameterTypes = methodSignature.getParameterTypes();

			method = declaringType.getMethod(name, parameterTypes);
		}
		else {
			method = methodSignature.getMethod();
		}

		return method;
	}

	public static Method getMethod(ProceedingJoinPoint proceedingJoinPoint)
		throws NoSuchMethodException {

		MethodSignature methodSignature =
			(MethodSignature)proceedingJoinPoint.getSignature();

		return getMethod(methodSignature);
	}

}