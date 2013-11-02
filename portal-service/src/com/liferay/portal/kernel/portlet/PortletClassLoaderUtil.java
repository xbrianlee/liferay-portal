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

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.servlet.PluginContextListener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletClassLoaderUtil {

	public static ClassLoader getClassLoader() {
		Thread currentThread = Thread.currentThread();

		return _classLoaders.get(currentThread.getId());
	}

	public static ClassLoader getClassLoader(String portletId) {
		PortalRuntimePermission.checkGetClassLoader(portletId);

		PortletBag portletBag = PortletBagPool.get(portletId);

		if (portletBag == null) {
			return null;
		}

		ServletContext servletContext = portletBag.getServletContext();

		return (ClassLoader)servletContext.getAttribute(
			PluginContextListener.PLUGIN_CLASS_LOADER);
	}

	public static String getServletContextName() {
		return _servletContextName;
	}

	public static void setClassLoader(ClassLoader classLoader) {
		PortalRuntimePermission.checkSetBeanProperty(
			PortletClassLoaderUtil.class);

		Thread currentThread = Thread.currentThread();

		_classLoaders.put(currentThread.getId(), classLoader);
	}

	public static void setServletContextName(String servletContextName) {
		PortalRuntimePermission.checkSetBeanProperty(
			PortletClassLoaderUtil.class);

		_servletContextName = servletContextName;
	}

	private static Map<Long, ClassLoader> _classLoaders =
		new HashMap<Long, ClassLoader>();
	private static String _servletContextName;

}