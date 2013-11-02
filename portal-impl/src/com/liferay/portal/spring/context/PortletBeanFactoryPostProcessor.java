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

import com.liferay.portal.kernel.spring.util.SpringFactoryUtil;
import com.liferay.portal.spring.aop.ChainableMethodAdviceInjectorCollector;

import org.springframework.beans.factory.BeanIsAbstractException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletBeanFactoryPostProcessor
	implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(
		ConfigurableListableBeanFactory configurableListableBeanFactory) {

		ChainableMethodAdviceInjectorCollector.collect(
			configurableListableBeanFactory);

		configurableListableBeanFactory.setBeanClassLoader(
			PortletApplicationContext.getBeanClassLoader());

		String[] names =
			configurableListableBeanFactory.getBeanDefinitionNames();

		for (String name : names) {
			if (!name.contains(SpringFactoryUtil.class.getName())) {
				continue;
			}

			try {
				Object bean = configurableListableBeanFactory.getBean(name);

				if (bean instanceof BeanPostProcessor) {
					configurableListableBeanFactory.addBeanPostProcessor(
						(BeanPostProcessor)bean);
				}
			}
			catch (BeanIsAbstractException biae) {
				continue;
			}
		}
	}

}