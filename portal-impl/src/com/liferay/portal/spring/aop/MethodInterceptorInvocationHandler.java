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

package com.liferay.portal.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author Shuyang Zhou
 */
public class MethodInterceptorInvocationHandler implements InvocationHandler {

	public MethodInterceptorInvocationHandler(
		Object target, List<MethodInterceptor> methodInterceptors) {

		if (target == null) {
			throw new NullPointerException("Target is null");
		}

		_target = target;
		_targetClass = target.getClass();

		if (methodInterceptors == null) {
			throw new NullPointerException("Method interceptors is null");
		}

		if (methodInterceptors.isEmpty()) {
			throw new IllegalArgumentException("Method interceptors is empty");
		}

		for (int i = 0; i < methodInterceptors.size(); i++) {
			if (methodInterceptors.get(i) == null) {
				throw new IllegalArgumentException(
					"Method interceptor " + i + " is null");
			}
		}

		_methodInterceptors = methodInterceptors;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		ServiceBeanMethodInvocation serviceBeanMethodInvocation =
			new ServiceBeanMethodInvocation(
				_target, _targetClass, method, arguments);

		serviceBeanMethodInvocation.setMethodInterceptors(_methodInterceptors);

		return serviceBeanMethodInvocation.proceed();
	}

	private List<MethodInterceptor> _methodInterceptors;
	private Object _target;
	private Class<?> _targetClass;

}