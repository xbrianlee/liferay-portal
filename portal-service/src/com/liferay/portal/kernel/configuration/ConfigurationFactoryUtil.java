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

package com.liferay.portal.kernel.configuration;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class ConfigurationFactoryUtil {

	public static Configuration getConfiguration(
		ClassLoader classLoader, String name) {

		return getConfigurationFactory().getConfiguration(classLoader, name);
	}

	public static ConfigurationFactory getConfigurationFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ConfigurationFactoryUtil.class);

		return _configurationFactory;
	}

	public static void setConfigurationFactory(
		ConfigurationFactory configurationFactory) {

		PortalRuntimePermission.checkSetBeanProperty(
			ConfigurationFactoryUtil.class);

		_configurationFactory = configurationFactory;
	}

	private static ConfigurationFactory _configurationFactory;

}