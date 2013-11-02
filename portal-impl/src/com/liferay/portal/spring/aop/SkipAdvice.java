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

import com.liferay.portal.kernel.spring.aop.Skip;

import java.lang.annotation.Annotation;

import java.util.Collections;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 */
public class SkipAdvice extends AnnotationChainableMethodAdvice<Skip> {

	@Override
	public Object before(MethodInvocation methodInvocation) throws Throwable {
		Skip skip = findAnnotation(methodInvocation);

		if (skip != _nullSkip) {
			MethodInterceptorsBag methodInterceptorsBag =
				serviceBeanAopCacheManager.getMethodInterceptorsBag(
					methodInvocation);

			if (methodInterceptorsBag == null) {
				return null;
			}

			MethodInterceptorsBag newMethodInterceptorsBag =
				new MethodInterceptorsBag(
					methodInterceptorsBag.getClassLevelMethodInterceptors(),
					Collections.<MethodInterceptor>emptyList());

			serviceBeanAopCacheManager.putMethodInterceptorsBag(
				methodInvocation, newMethodInterceptorsBag);

			ServiceBeanMethodInvocation serviceBeanMethodInvocation =
				(ServiceBeanMethodInvocation)methodInvocation;

			serviceBeanMethodInvocation.setMethodInterceptors(
				Collections.<MethodInterceptor>emptyList());
		}

		return null;
	}

	@Override
	public Skip getNullAnnotation() {
		return _nullSkip;
	}

	private static Skip _nullSkip = new Skip() {

		@Override
		public Class<? extends Annotation> annotationType() {
			return Skip.class;
		}

	};

}