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

package com.liferay.portal.spring.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.spring.util.FactoryBean;
import com.liferay.portal.kernel.spring.util.SpringFactory;
import com.liferay.portal.kernel.spring.util.SpringFactoryException;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.ClassLoaderUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class SpringFactoryImpl implements SpringFactory {

	@Override
	public Object newBean(String className) throws SpringFactoryException {
		return newBean(className, null);
	}

	@Override
	public Object newBean(String className, Map<String, Object> properties)
		throws SpringFactoryException {

		try {
			return doNewBean(className, properties);
		}
		catch (SpringFactoryException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SpringFactoryException(e);
		}
	}

	public void setBeanDefinitions(Map<String, String> beanDefinitions) {
		_beanDefinitions = new HashMap<String, Set<String>>();

		for (Map.Entry<String, String> entry : beanDefinitions.entrySet()) {
			String className = entry.getKey();

			Set<String> properties = SetUtil.fromArray(
				StringUtil.split(entry.getValue()));

			_beanDefinitions.put(className, properties);
		}
	}

	protected Object doNewBean(String className, Map<String, Object> properties)
		throws Exception {

		Set<String> allowedProperties = _beanDefinitions.get(className);

		if (allowedProperties == null) {
			throw new SpringFactoryException("Undefined class " + className);
		}

		Object bean = InstanceFactory.newInstance(
			ClassLoaderUtil.getPortalClassLoader(), className);

		FactoryBean<Object> factoryBean = null;

		if (bean instanceof FactoryBean) {
			factoryBean = (FactoryBean<Object>)bean;

			bean = factoryBean.create();
		}

		if (properties != null) {
			for (Map.Entry<String, Object> entry : properties.entrySet()) {
				String name = entry.getKey();

				if (!allowedProperties.contains(name)) {
					throw new SpringFactoryException(
						"Undefined property " + name + " for class " +
							className);
				}

				Object value = entry.getValue();

				BeanPropertiesUtil.setProperty(bean, name, value);
			}
		}

		if (factoryBean != null) {
			bean = factoryBean.postProcessing(bean);
		}

		return bean;
	}

	private Map<String, Set<String>> _beanDefinitions;

}