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

import com.liferay.portal.bean.BeanLocatorImpl;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.context.ArrayApplicationContext;
import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;

/**
 * <p>
 * In most cases, SpringUtil.setContext() would have been called by
 * com.liferay.portal.spring.context.PortalContextLoaderListener, configured in
 * web.xml for the web application. However, there will be times in which
 * SpringUtil will be called in a non-web application and, therefore, require
 * manual instantiation of the application context.
 * </p>
 *
 * @author Michael Young
 */
public class SpringUtil {

	public static void loadContext() {
		List<String> configLocations = ListUtil.fromArray(
			PropsUtil.getArray(PropsKeys.SPRING_CONFIGS));

		_loadContext(configLocations);
	}

	public static void loadContext(List<String> extraConfigLocations) {
		List<String> configLocations = ListUtil.fromArray(
			PropsUtil.getArray(PropsKeys.SPRING_CONFIGS));

		if (extraConfigLocations != null) {
			configLocations.addAll(extraConfigLocations);
		}

		_loadContext(configLocations);
	}

	private static void _loadContext(List<String> configLocations) {
		if (StringUtil.equalsIgnoreCase(
				PropsValues.PERSISTENCE_PROVIDER, "jpa")) {

			configLocations.remove("META-INF/hibernate-spring.xml");
		}
		else {
			configLocations.remove("META-INF/jpa-spring.xml");
		}

		AbstractApplicationContext applicationContext =
			new ArrayApplicationContext(
				configLocations.toArray(new String[configLocations.size()]));

		BeanLocator beanLocator = new BeanLocatorImpl(
			ClassLoaderUtil.getPortalClassLoader(), applicationContext);

		PortalBeanLocatorUtil.setBeanLocator(beanLocator);
	}

}