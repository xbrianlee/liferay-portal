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

package com.liferay.portal.spring.context;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.aspectj.weaver.tools.ShadowMatch;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJPointcutAdvisor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;

/**
 * @author Shuyang Zhou
 */
public class PortletBeanFactoryCleaner implements BeanFactoryAware {

	public static void readBeans() {
		if (_beanFactory == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Bean factory is null");
			}

			return;
		}

		if (!(_beanFactory instanceof ListableBeanFactory)) {
			return;
		}

		ListableBeanFactory listableBeanFactory =
			(ListableBeanFactory)_beanFactory;

		String[] names = listableBeanFactory.getBeanDefinitionNames();

		for (String name : names) {
			try {
				_readBean(listableBeanFactory, name);
			}
			catch (Exception e) {
			}
		}
	}

	public void destroy() {
		for (BeanFactoryAware beanFactoryAware : _beanFactoryAwares) {
			try {
				beanFactoryAware.setBeanFactory(null);
			}
			catch (Exception e) {
			}
		}

		_beanFactoryAwares.clear();

		for (AspectJExpressionPointcut aspectJExpressionPointcut :
				_aspectJExpressionPointcuts) {

			try {
				Map<Method, ShadowMatch> shadowMatchCache =
					(Map<Method, ShadowMatch>)_shadowMatchCacheField.get(
						aspectJExpressionPointcut);

				shadowMatchCache.clear();
			}
			catch (Exception e) {
			}
		}

		_aspectJExpressionPointcuts.clear();
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		_beanFactory = beanFactory;
	}

	private static void _readBean(
			ListableBeanFactory listableBeanFactory, String name)
		throws Exception {

		Object bean = listableBeanFactory.getBean(name);

		if (bean instanceof AspectJPointcutAdvisor) {
			AspectJPointcutAdvisor aspectJPointcutAdvisor =
				(AspectJPointcutAdvisor)bean;

			Pointcut pointcut = aspectJPointcutAdvisor.getPointcut();

			ClassFilter classFilter = pointcut.getClassFilter();

			if (classFilter instanceof AspectJExpressionPointcut) {
				AspectJExpressionPointcut aspectJExpressionPointcut =
					(AspectJExpressionPointcut)classFilter;

				_beanFactoryAwares.add(aspectJExpressionPointcut);
				_aspectJExpressionPointcuts.add(aspectJExpressionPointcut);
			}
		}
		else if (bean instanceof BeanFactoryAware) {
			_beanFactoryAwares.add((BeanFactoryAware)bean);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortletBeanFactoryCleaner.class);

	private static Set<AspectJExpressionPointcut> _aspectJExpressionPointcuts =
		new HashSet<AspectJExpressionPointcut>();
	private static BeanFactory _beanFactory;
	private static Set<BeanFactoryAware> _beanFactoryAwares =
		new HashSet<BeanFactoryAware>();
	private static Field _shadowMatchCacheField;

	static {
		try {
			_shadowMatchCacheField = ReflectionUtil.getDeclaredField(
				AspectJExpressionPointcut.class, "shadowMatchCache");
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

}