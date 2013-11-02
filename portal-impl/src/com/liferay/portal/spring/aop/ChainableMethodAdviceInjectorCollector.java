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

import com.liferay.portal.kernel.spring.util.SpringFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.TypedStringValue;

/**
 * @author Shuyang Zhou
 */
public class ChainableMethodAdviceInjectorCollector {

	public static final String BEAN_NAME =
		ChainableMethodAdviceInjectorCollector.class.getName();

	public static void collect(
		ConfigurableListableBeanFactory configurableListableBeanFactory) {

		ChainableMethodAdviceInjectorCollector
			chainableMethodAdviceInjectorCollector =
				new ChainableMethodAdviceInjectorCollector();

		String[] beanNames =
			configurableListableBeanFactory.getBeanDefinitionNames();

		for (String beanName : beanNames) {
			if (!beanName.contains(SpringFactoryUtil.class.getName())) {
				continue;
			}

			BeanDefinition beanDefinition =
				configurableListableBeanFactory.getBeanDefinition(beanName);

			ConstructorArgumentValues constructorArgumentValues =
				beanDefinition.getConstructorArgumentValues();

			List<ConstructorArgumentValues.ValueHolder> valueHolders =
				constructorArgumentValues.getGenericArgumentValues();

			if (valueHolders.isEmpty()) {
				continue;
			}

			ConstructorArgumentValues.ValueHolder valueHolder =
				valueHolders.get(0);

			TypedStringValue typedStringValue =
				(TypedStringValue)valueHolder.getValue();

			String className = typedStringValue.getValue();

			if (className.contains(
					ChainableMethodAdviceInjector.class.getSimpleName())) {

				chainableMethodAdviceInjectorCollector.addBeanName(beanName);
			}
		}

		if (!chainableMethodAdviceInjectorCollector.hasBeanNames()) {
			configurableListableBeanFactory.registerSingleton(
				BEAN_NAME, chainableMethodAdviceInjectorCollector);
		}
	}

	public List<String> getBeanNames() {
		return _beanNames;
	}

	protected void addBeanName(String beanName) {
		_beanNames.add(beanName);
	}

	protected boolean hasBeanNames() {
		return _beanNames.isEmpty();
	}

	private ChainableMethodAdviceInjectorCollector() {
	}

	private List<String> _beanNames = new ArrayList<String>();

}