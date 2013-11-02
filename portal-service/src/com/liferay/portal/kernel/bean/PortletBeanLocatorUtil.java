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

package com.liferay.portal.kernel.bean;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletBeanLocatorUtil {

	public static BeanLocator getBeanLocator(String servletContextName) {
		PortalRuntimePermission.checkGetBeanProperty(
			servletContextName, PortletBeanLocatorUtil.class);

		return _beanLocators.get(servletContextName);
	}

	public static Object locate(String servletContextName, String name)
		throws BeanLocatorException {

		BeanLocator beanLocator = getBeanLocator(servletContextName);

		if (beanLocator == null) {
			_log.error(
				"BeanLocator is null for servlet context " +
					servletContextName);

			throw new BeanLocatorException(
				"BeanLocator has not been set for servlet context " +
					servletContextName);
		}
		else {
			return beanLocator.locate(name);
		}
	}

	public static void setBeanLocator(
		String servletContextName, BeanLocator beanLocator) {

		PortalRuntimePermission.checkSetBeanProperty(
			servletContextName, PortletBeanLocatorUtil.class);

		if (_log.isDebugEnabled()) {
			if (beanLocator != null) {
				_log.debug(
					"Setting BeanLocator " + beanLocator.hashCode() +
						" for servlet context " + servletContextName);
			}
			else {
				_log.debug(
					"Removing BeanLocator for servlet context " +
						servletContextName);
			}
		}

		_beanLocators.put(servletContextName, beanLocator);
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortletBeanLocatorUtil.class);

	private static Map<String, BeanLocator> _beanLocators =
		new HashMap<String, BeanLocator>();

}