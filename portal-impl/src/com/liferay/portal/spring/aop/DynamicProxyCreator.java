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

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.util.ClassLoaderUtil;

import java.lang.reflect.InvocationHandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.Ordered;

/**
 * @author Shuyang Zhou
 */
public class DynamicProxyCreator
	extends InstantiationAwareBeanPostProcessorAdapter implements Ordered {

	public static DynamicProxyCreator getDynamicProxyCreator() {
		return _instance;
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
		throws BeansException {

		Class<?> beanClass = bean.getClass();

		for (ObjectValuePair<BeanMatcher, InvocationHandlerFactory>
				objectValuePair : _beanMatcherInvocationHandlerFactories) {

			BeanMatcher beanMatcher = objectValuePair.getKey();

			if (beanMatcher.match(beanClass, beanName)) {
				InvocationHandlerFactory invocationHandlerFactory =
					objectValuePair.getValue();

				InvocationHandler invocationHandler =
					invocationHandlerFactory.createInvocationHandler(bean);

				bean = ProxyUtil.newProxyInstance(
					ClassLoaderUtil.getContextClassLoader(),
					beanClass.getInterfaces(), invocationHandler);
			}
		}

		return bean;
	}

	public static class Register {

		public Register(
			BeanMatcher beanMatcher,
			InvocationHandlerFactory invocationHandlerFactory) {

			ObjectValuePair<BeanMatcher, InvocationHandlerFactory>
				objectValuePair =
					new ObjectValuePair<BeanMatcher, InvocationHandlerFactory>(
						beanMatcher, invocationHandlerFactory);

			_instance._beanMatcherInvocationHandlerFactories.add(
				objectValuePair);
		}

	}

	private static DynamicProxyCreator _instance = new DynamicProxyCreator();

	private List<ObjectValuePair<BeanMatcher, InvocationHandlerFactory>>
		_beanMatcherInvocationHandlerFactories =
			new ArrayList
				<ObjectValuePair<BeanMatcher, InvocationHandlerFactory>>();

}