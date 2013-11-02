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

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.portal.kernel.util.AggregateClassLoader;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Michael C. Han
 */
public class MultiClassLoaderProxyRequest extends ProxyRequest {

	public MultiClassLoaderProxyRequest(Method method, Object[] arguments)
		throws Exception {

		super(method, arguments);

		ClassLoader[] classLoaders = inspectForClassLoaders(method);

		_clientClassLoaders = AggregateClassLoader.getAggregateClassLoader(
			classLoaders);
	}

	@Override
	public Object execute(Object object) throws Exception {
		ClassLoader contextClassLoader = null;

		if (_clientClassLoaders != null) {
			Thread currentThread = Thread.currentThread();

			contextClassLoader = currentThread.getContextClassLoader();

			ClassLoader invocationClassLoader =
				AggregateClassLoader.getAggregateClassLoader(
					new ClassLoader[] {
						contextClassLoader, _clientClassLoaders
					});

			currentThread.setContextClassLoader(invocationClassLoader);
		}

		try {
			return super.execute(object);
		}
		catch (InvocationTargetException ite) {
			throw new Exception(ite.getTargetException());
		}
		finally {
			if (contextClassLoader != null) {
				Thread currentThread = Thread.currentThread();

				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected ClassLoader[] inspectForClassLoaders(Method method)
		throws Exception {

		Annotation[][] annotationsArray = method.getParameterAnnotations();

		if (ArrayUtil.isEmpty(annotationsArray)) {
			return null;
		}

		for (int i = 0; i < annotationsArray.length; i++) {
			Annotation[] annotations = annotationsArray[i];

			if (ArrayUtil.isEmpty(annotations)) {
				continue;
			}

			for (Annotation annotation : annotations) {
				if (ExecutingClassLoaders.class.isAssignableFrom(
						annotation.annotationType())) {

					return (ClassLoader[])getArguments()[i];
				}
			}
		}

		return null;
	}

	private ClassLoader _clientClassLoaders;

}