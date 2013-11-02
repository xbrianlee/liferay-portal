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

package com.liferay.portal.cache;

import com.liferay.portal.kernel.cache.Lifecycle;
import com.liferay.portal.kernel.cache.ThreadLocalCachable;
import com.liferay.portal.kernel.cache.ThreadLocalCache;
import com.liferay.portal.kernel.cache.ThreadLocalCacheManager;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.aop.AnnotationChainableMethodAdvice;
import com.liferay.portal.spring.aop.ServiceBeanMethodInvocation;

import java.io.Serializable;

import java.lang.annotation.Annotation;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class ThreadLocalCacheAdvice
	extends AnnotationChainableMethodAdvice<ThreadLocalCachable> {

	@Override
	public void afterReturning(MethodInvocation methodInvocation, Object result)
		throws Throwable {

		ThreadLocalCachable threadLocalCachable = findAnnotation(
			methodInvocation);

		if (threadLocalCachable == _nullThreadLocalCacheable) {
			return;
		}

		Serializable cacheName = _getCacheName(methodInvocation);

		ThreadLocalCache<Object> threadLocalCache =
			ThreadLocalCacheManager.getThreadLocalCache(
				threadLocalCachable.scope(), cacheName);

		String cacheKey = _getCacheKey(methodInvocation.getArguments());

		if (result == null) {
			threadLocalCache.put(cacheKey, nullResult);
		}
		else {
			threadLocalCache.put(cacheKey, result);
		}
	}

	@Override
	public Object before(MethodInvocation methodInvocation) throws Throwable {
		ThreadLocalCachable threadLocalCachable = findAnnotation(
			methodInvocation);

		if (threadLocalCachable == _nullThreadLocalCacheable) {
			return null;
		}

		Serializable cacheName = _getCacheName(methodInvocation);

		ThreadLocalCache<?> threadLocalCache =
			ThreadLocalCacheManager.getThreadLocalCache(
				threadLocalCachable.scope(), cacheName);

		String cacheKey = _getCacheKey(methodInvocation.getArguments());

		Object value = threadLocalCache.get(cacheKey);

		if (value == nullResult) {
			return null;
		}

		return value;
	}

	@Override
	public ThreadLocalCachable getNullAnnotation() {
		return _nullThreadLocalCacheable;
	}

	private String _getCacheKey(Object[] arguments) {
		StringBundler sb = new StringBundler(arguments.length * 2 - 1);

		for (int i = 0; i < arguments.length; i++) {
			sb.append(StringUtil.toHexString(arguments[i]));

			if ((i + 1) < arguments.length) {
				sb.append(StringPool.POUND);
			}
		}

		return sb.toString();
	}

	private Serializable _getCacheName(MethodInvocation methodInvocation) {
		if (methodInvocation instanceof ServiceBeanMethodInvocation) {
			ServiceBeanMethodInvocation serviceBeanMethodInvocation =
				(ServiceBeanMethodInvocation)methodInvocation;

			return serviceBeanMethodInvocation.toCacheKeyModel();
		}
		else {
			return methodInvocation.toString();
		}
	}

	private static ThreadLocalCachable _nullThreadLocalCacheable =
		new ThreadLocalCachable() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ThreadLocalCachable.class;
			}

			@Override
			public Lifecycle scope() {
				return null;
			}

		};

}