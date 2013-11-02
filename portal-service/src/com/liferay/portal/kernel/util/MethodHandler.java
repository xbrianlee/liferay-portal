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

import java.io.Serializable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.Arrays;

/**
 * @author Shuyang Zhou
 */
public class MethodHandler implements Serializable {

	public MethodHandler(Method method, Object... arguments) {
		this(new MethodKey(method), arguments);
	}

	public MethodHandler(MethodKey methodKey, Object... arguments) {
		_methodKey = methodKey;
		_arguments = arguments;
	}

	public Object[] getArguments() {
		Object[] arguments = new Object[_arguments.length];

		System.arraycopy(_arguments, 0, arguments, 0, _arguments.length);

		return arguments;
	}

	public MethodKey getMethodKey() {
		return _methodKey;
	}

	public Object invoke(boolean newInstance) throws Exception {
		Method method = _methodKey.getMethod();

		Object targetObject = null;

		if (newInstance && !Modifier.isStatic(method.getModifiers())) {
			Class<?> targetClass = _methodKey.getDeclaringClass();

			targetObject = targetClass.newInstance();
		}

		return method.invoke(targetObject, _arguments);
	}

	public Object invoke(Object target) throws Exception {
		Method method = _methodKey.getMethod();

		return method.invoke(target, _arguments);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{arguments=");
		sb.append(Arrays.toString(_arguments));
		sb.append(", methodKey=");
		sb.append(_methodKey);
		sb.append("}");

		return sb.toString();
	}

	private Object[] _arguments;
	private MethodKey _methodKey;

}