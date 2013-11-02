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

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public abstract class ChainableMethodAdvice implements MethodInterceptor {

	public void afterReturning(MethodInvocation methodInvocation, Object result)
		throws Throwable {
	}

	public void afterThrowing(
			MethodInvocation methodInvocation, Throwable throwable)
		throws Throwable {
	}

	public Object before(MethodInvocation methodInvocation) throws Throwable {
		return null;
	}

	public void duringFinally(MethodInvocation methodInvocation) {
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Object returnValue = before(methodInvocation);

		if (returnValue != null) {
			if (returnValue == nullResult) {
				return null;
			}
			else {
				return returnValue;
			}
		}

		try {
			returnValue = methodInvocation.proceed();

			afterReturning(methodInvocation, returnValue);
		}
		catch (Throwable throwable) {
			afterThrowing(methodInvocation, throwable);

			throw throwable;
		}
		finally {
			duringFinally(methodInvocation);
		}

		return returnValue;
	}

	public void setNextMethodInterceptor(
		MethodInterceptor nextMethodInterceptor) {

		this.nextMethodInterceptor = nextMethodInterceptor;
	}

	protected void setServiceBeanAopCacheManager(
		ServiceBeanAopCacheManager serviceBeanAopCacheManager) {

		if (this.serviceBeanAopCacheManager != null) {
			return;
		}

		this.serviceBeanAopCacheManager = serviceBeanAopCacheManager;
	}

	protected MethodInterceptor nextMethodInterceptor;
	protected Object nullResult = new Object();
	protected ServiceBeanAopCacheManager serviceBeanAopCacheManager;

}