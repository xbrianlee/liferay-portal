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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.servlet.PluginContextListener;

import javax.servlet.ServletContext;

/**
 * @author Bruno Farache
 * @author Shuyang Zhou
 */
public class PortletClassInvoker {

	public static Object invoke(
			boolean newInstance, String portletId, MethodKey methodKey,
			Object... arguments)
		throws Exception {

		portletId = _getRootPortletId(portletId);

		ClassLoader portletClassLoader = PortalClassLoaderUtil.getClassLoader();

		PortletBag portletBag = PortletBagPool.get(portletId);

		if (portletBag != null) {
			ServletContext servletContext = portletBag.getServletContext();

			portletClassLoader = (ClassLoader)servletContext.getAttribute(
				PluginContextListener.PLUGIN_CLASS_LOADER);
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(portletClassLoader);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, arguments);

			return methodHandler.invoke(newInstance);
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	/**
	 * Copied from <code>com.liferay.portal.model.PortletConstants</code>.
	 */
	private static String _getRootPortletId(String portletId) {
		int pos = portletId.indexOf(_INSTANCE_SEPARATOR);

		if (pos == -1) {
			return portletId;
		}
		else {
			return portletId.substring(0, pos);
		}
	}

	private static final String _INSTANCE_SEPARATOR = "_INSTANCE_";

}