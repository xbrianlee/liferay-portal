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
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PropsValues;

import java.io.FileNotFoundException;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * <p>
 * This web application context will first load bean definitions in the
 * contextConfigLocation parameter in web.xml. Then, the context will load bean
 * definitions specified by the property "spring.configs" in portal.properties.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Tomas Polesovsky
 */
public class PortalApplicationContext extends XmlWebApplicationContext {

	@Override
	protected void loadBeanDefinitions(
		XmlBeanDefinitionReader xmlBeanDefinitionReader) {

		try {
			super.loadBeanDefinitions(xmlBeanDefinitionReader);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		xmlBeanDefinitionReader.setResourceLoader(
			new PathMatchingResourcePatternResolver());

		if (PropsValues.SPRING_CONFIGS == null) {
			return;
		}

		List<String> configLocations = ListUtil.fromArray(
			PropsValues.SPRING_CONFIGS);

		if (StringUtil.equalsIgnoreCase(
				PropsValues.PERSISTENCE_PROVIDER, "jpa")) {

			configLocations.remove("META-INF/hibernate-spring.xml");
		}
		else {
			configLocations.remove("META-INF/jpa-spring.xml");
		}

		for (String configLocation : configLocations) {
			try {
				xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
			}
			catch (Exception e) {
				Throwable cause = e.getCause();

				if (cause instanceof FileNotFoundException) {
					if (_log.isWarnEnabled()) {
						_log.warn(cause.getMessage());
					}
				}
				else {
					_log.error(e, e);
				}
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalApplicationContext.class);

}