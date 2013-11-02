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

package com.liferay.portal.messaging.async;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.async.Async;
import com.liferay.portal.spring.aop.AnnotationChainableMethodAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class AsyncAdvice extends AnnotationChainableMethodAdvice<Async> {

	@Override
	public Object before(final MethodInvocation methodInvocation)
		throws Throwable {

		if (AsyncInvokeThreadLocal.isEnabled()) {
			return null;
		}

		Async async = findAnnotation(methodInvocation);

		if (async == _nullAsync) {
			return null;
		}

		Method method = methodInvocation.getMethod();

		if (method.getReturnType() != void.class) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Async annotation on method " + method.getName() +
						" does not return void");
			}

			return null;
		}

		String destinationName = null;

		if ((_destinationNames != null) && !_destinationNames.isEmpty()) {
			Object thisObject = methodInvocation.getThis();

			destinationName = _destinationNames.get(thisObject.getClass());
		}

		if (destinationName == null) {
			destinationName = _defaultDestinationName;
		}

		MessageBusUtil.sendMessage(
			destinationName, new AsyncProcessCallable(methodInvocation));

		return nullResult;
	}

	public String getDefaultDestinationName() {
		return _defaultDestinationName;
	}

	@Override
	public Async getNullAnnotation() {
		return _nullAsync;
	}

	public void setDefaultDestinationName(String defaultDestinationName) {
		_defaultDestinationName = defaultDestinationName;
	}

	public void setDestinationNames(Map<Class<?>, String> destinationNames) {
		_destinationNames = destinationNames;
	}

	private static Log _log = LogFactoryUtil.getLog(AsyncAdvice.class);

	private static Async _nullAsync =
		new Async() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return Async.class;
			}

		};

	private String _defaultDestinationName;
	private Map<Class<?>, String> _destinationNames;

}