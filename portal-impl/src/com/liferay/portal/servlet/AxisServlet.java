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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.servlet.PluginContextListener;
import com.liferay.portal.security.ac.AccessControlThreadLocal;
import com.liferay.portal.util.ClassLoaderUtil;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class AxisServlet extends com.liferay.util.axis.AxisServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		ServletContext servletContext = servletConfig.getServletContext();

		_pluginClassLoader = (ClassLoader)servletContext.getAttribute(
			PluginContextListener.PLUGIN_CLASS_LOADER);

		if (_pluginClassLoader == null) {
			super.init(servletConfig);
		}
		else {
			ClassLoader contextClassLoader =
				ClassLoaderUtil.getContextClassLoader();

			try {
				ClassLoaderUtil.setContextClassLoader(_pluginClassLoader);

				super.init(servletConfig);
			}
			finally {
				ClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		boolean remoteAccess = AccessControlThreadLocal.isRemoteAccess();

		try {
			AccessControlThreadLocal.setRemoteAccess(true);

			if (_pluginClassLoader == null) {
				super.service(request, response);
			}
			else {
				ClassLoader contextClassLoader =
					ClassLoaderUtil.getContextClassLoader();

				try {
					ClassLoaderUtil.setContextClassLoader(_pluginClassLoader);

					super.service(request, response);
				}
				finally {
					ClassLoaderUtil.setContextClassLoader(contextClassLoader);
				}
			}
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (ServletException se) {
			throw se;
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
		finally {
			AccessControlThreadLocal.setRemoteAccess(remoteAccess);
		}
	}

	private ClassLoader _pluginClassLoader;

}