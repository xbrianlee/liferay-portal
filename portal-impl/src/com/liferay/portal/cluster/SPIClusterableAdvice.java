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

package com.liferay.portal.cluster;

import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.nio.intraband.rpc.IntrabandRPCUtil;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIUtil;
import com.liferay.portal.spring.aop.AnnotationChainableMethodAdvice;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.concurrent.Future;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 */
public class SPIClusterableAdvice
	extends AnnotationChainableMethodAdvice<Clusterable> {

	@Override
	public void afterReturning(MethodInvocation methodInvocation, Object result)
		throws Throwable {

		Clusterable clusterable = findAnnotation(methodInvocation);

		if (clusterable == NullClusterable.NULL_CLUSTERABLE) {
			return;
		}

		SPI spi = SPIUtil.getSPI();

		IntrabandRPCUtil.execute(
			spi.getRegistrationReference(),
			new MethodHandlerProcessCallable<Serializable>(
				ClusterableInvokerUtil.createMethodHandler(
					clusterable.acceptor(), methodInvocation)));
	}

	@Override
	public Object before(MethodInvocation methodInvocation) throws Throwable {
		Clusterable clusterable = findAnnotation(methodInvocation);

		if (clusterable == NullClusterable.NULL_CLUSTERABLE) {
			return null;
		}

		if (!clusterable.onMaster()) {
			return null;
		}

		SPI spi = SPIUtil.getSPI();

		Future<Serializable> futureResult = IntrabandRPCUtil.execute(
			spi.getRegistrationReference(),
			new MethodHandlerProcessCallable<Serializable>(
				ClusterableInvokerUtil.createMethodHandler(
					clusterable.acceptor(), methodInvocation)));

		Object result = futureResult.get();

		Method method = methodInvocation.getMethod();

		Class<?> returnType = method.getReturnType();

		if (returnType == void.class) {
			result = nullResult;
		}

		return result;
	}

	@Override
	public Clusterable getNullAnnotation() {
		return NullClusterable.NULL_CLUSTERABLE;
	}

}