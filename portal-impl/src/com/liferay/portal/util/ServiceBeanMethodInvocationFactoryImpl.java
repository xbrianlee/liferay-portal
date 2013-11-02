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

package com.liferay.portal.util;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ServiceBeanMethodInvocationFactory;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.aop.ServiceBeanMethodInvocation;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 */
@DoPrivileged
public class ServiceBeanMethodInvocationFactoryImpl
	implements ServiceBeanMethodInvocationFactory {

	@Override
	public Object proceed(
			Object target, Class<?> targetClass, Method method,
			Object[] arguments, String[] methodInterceptorBeanIds)
		throws Exception {

		if (ArrayUtil.isEmpty(methodInterceptorBeanIds)) {
			throw new IllegalArgumentException(
				"Method interceptor bean IDs array is empty");
		}

		ServiceBeanMethodInvocation serviceBeanMethodInvocation = create(
			target, targetClass, method, arguments);

		List<MethodInterceptor> methodInterceptors = getMethodInterceptors(
			methodInterceptorBeanIds);

		serviceBeanMethodInvocation.setMethodInterceptors(methodInterceptors);

		try {
			return serviceBeanMethodInvocation.proceed();
		}
		catch (Throwable t) {
			if (t instanceof Exception) {
				throw (Exception)t;
			}

			throw new Exception(t);
		}
	}

	protected ServiceBeanMethodInvocation create(
		Object target, Class<?> targetClass, Method method,
		Object[] arguments) {

		return new ServiceBeanMethodInvocation(
			target, targetClass, method, arguments);
	}

	protected List<MethodInterceptor> getMethodInterceptors(
		String... methodInterceptorBeanIds) {

		String methodInterceptorsKey = StringUtil.merge(
			methodInterceptorBeanIds);

		List<MethodInterceptor> methodInterceptors = _methodInterceptors.get(
			methodInterceptorsKey);

		if (methodInterceptors != null) {
			return methodInterceptors;
		}

		methodInterceptors = new ArrayList<MethodInterceptor>();

		for (String methodInterceptorBeanId : methodInterceptorBeanIds) {
			MethodInterceptor methodInterceptor =
				(MethodInterceptor)PortalBeanLocatorUtil.locate(
					methodInterceptorBeanId);

			methodInterceptors.add(methodInterceptor);
		}

		_methodInterceptors.put(methodInterceptorsKey, methodInterceptors);

		return methodInterceptors;
	}

	private Map<String, List<MethodInterceptor>> _methodInterceptors =
		new HashMap<String, List<MethodInterceptor>>();

}