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

package com.liferay.portlet;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.Portlet;

import javax.portlet.PortletConfig;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletConfigFactoryUtil {

	public static PortletConfig create(
		Portlet portlet, ServletContext servletContext) {

		return getPortletConfigFactory().create(portlet, servletContext);
	}

	public static void destroy(Portlet portlet) {
		getPortletConfigFactory().destroy(portlet);
	}

	public static PortletConfigFactory getPortletConfigFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortletConfigFactoryUtil.class);

		return _portletConfigFactory;
	}

	public static PortletConfig update(Portlet portlet) {
		return getPortletConfigFactory().update(portlet);
	}

	public void setPortletConfigFactory(
		PortletConfigFactory portletConfigFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portletConfigFactory = portletConfigFactory;
	}

	private static PortletConfigFactory _portletConfigFactory;

}