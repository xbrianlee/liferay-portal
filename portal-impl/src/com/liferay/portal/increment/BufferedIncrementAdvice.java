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

package com.liferay.portal.increment;

import com.liferay.portal.kernel.cache.key.CacheKeyGenerator;
import com.liferay.portal.kernel.cache.key.CacheKeyGeneratorUtil;
import com.liferay.portal.kernel.increment.BufferedIncrement;
import com.liferay.portal.kernel.increment.BufferedIncrementThreadLocal;
import com.liferay.portal.kernel.increment.Increment;
import com.liferay.portal.kernel.increment.IncrementFactory;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.aop.AnnotationChainableMethodAdvice;

import java.io.Serializable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Zsolt Berentey
 * @author Shuyang Zhou
 */
public class BufferedIncrementAdvice
	extends AnnotationChainableMethodAdvice<BufferedIncrement> {

	@Override
	@SuppressWarnings("rawtypes")
	public Object before(MethodInvocation methodInvocation) throws Throwable {
		BufferedIncrement bufferedIncrement = findAnnotation(methodInvocation);

		if (!BufferedIncrementThreadLocal.isEnabled() ||
			(bufferedIncrement == _nullBufferedIncrement)) {

			return null;
		}

		String configuration = bufferedIncrement.configuration();

		BufferedIncrementConfiguration bufferedIncrementConfiguration =
			_bufferedIncrementConfigurations.get(configuration);

		if (bufferedIncrementConfiguration == null) {
			bufferedIncrementConfiguration = new BufferedIncrementConfiguration(
				configuration);

			_bufferedIncrementConfigurations.put(
				configuration, bufferedIncrementConfiguration);
		}

		if (!bufferedIncrementConfiguration.isEnabled()) {
			return nullResult;
		}

		Method method = methodInvocation.getMethod();

		BufferedIncrementProcessor bufferedIncrementProcessor =
			_bufferedIncrementProcessors.get(method);

		if (bufferedIncrementProcessor == null) {
			bufferedIncrementProcessor = new BufferedIncrementProcessor(
				bufferedIncrementConfiguration, method);

			BufferedIncrementProcessor previousBufferedIncrementProcessor =
				_bufferedIncrementProcessors.putIfAbsent(
					method, bufferedIncrementProcessor);

			if (previousBufferedIncrementProcessor != null) {
				bufferedIncrementProcessor = previousBufferedIncrementProcessor;
			}
		}

		Object[] arguments = methodInvocation.getArguments();

		Object value = arguments[arguments.length - 1];

		CacheKeyGenerator cacheKeyGenerator =
			CacheKeyGeneratorUtil.getCacheKeyGenerator(
				BufferedIncrementAdvice.class.getName());

		for (int i = 0; i < arguments.length - 1; i++) {
			cacheKeyGenerator.append(StringUtil.toHexString(arguments[i]));
		}

		Serializable batchKey = cacheKeyGenerator.finish();

		Increment<?> increment = IncrementFactory.createIncrement(
			bufferedIncrement.incrementClass(), value);

		BufferedIncreasableEntry bufferedIncreasableEntry =
			new BufferedIncreasableEntry(methodInvocation, batchKey, increment);

		bufferedIncrementProcessor.process(bufferedIncreasableEntry);

		return nullResult;
	}

	public void destroy() {
		for (BufferedIncrementProcessor bufferedIncrementProcessor :
				_bufferedIncrementProcessors.values()) {

			bufferedIncrementProcessor.destroy();
		}
	}

	@Override
	public BufferedIncrement getNullAnnotation() {
		return _nullBufferedIncrement;
	}

	private static BufferedIncrement _nullBufferedIncrement =
		new BufferedIncrement() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return BufferedIncrement.class;
			}

			@Override
			public String configuration() {
				return "default";
			}

			@Override
			public Class<? extends Increment<?>> incrementClass() {
				return null;
			}

		};

	private Map<String, BufferedIncrementConfiguration>
		_bufferedIncrementConfigurations =
			new ConcurrentHashMap<String, BufferedIncrementConfiguration>();
	private ConcurrentMap<Method, BufferedIncrementProcessor>
		_bufferedIncrementProcessors =
			new ConcurrentHashMap<Method, BufferedIncrementProcessor>();

}