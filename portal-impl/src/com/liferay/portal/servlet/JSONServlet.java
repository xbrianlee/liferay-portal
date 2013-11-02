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

import com.liferay.portal.action.JSONServiceAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.PluginContextListener;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.security.ac.AccessControlThreadLocal;
import com.liferay.portal.struts.JSONAction;
import com.liferay.portal.util.ClassLoaderUtil;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class JSONServlet extends HttpServlet {

	@Override
	public void init(ServletConfig servletConfig) {
		ServletContext servletContext = servletConfig.getServletContext();

		_pluginClassLoader = (ClassLoader)servletContext.getAttribute(
			PluginContextListener.PLUGIN_CLASS_LOADER);

		_jsonAction = getJSONAction(servletContext);
	}

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		boolean remoteAccess = AccessControlThreadLocal.isRemoteAccess();

		try {
			AccessControlThreadLocal.setRemoteAccess(true);

			if (_pluginClassLoader == null) {
				_jsonAction.execute(null, null, request, response);
			}
			else {
				ClassLoader contextClassLoader =
					ClassLoaderUtil.getContextClassLoader();

				try {
					ClassLoaderUtil.setContextClassLoader(_pluginClassLoader);

					_jsonAction.execute(null, null, request, response);
				}
				finally {
					ClassLoaderUtil.setContextClassLoader(contextClassLoader);
				}
			}
		}
		catch (IOException ioe) {
			if (!ServletResponseUtil.isClientAbortException(ioe)) {
				throw ioe;
			}
		}
		catch (SecurityException se) {
			throw new ServletException(se);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			AccessControlThreadLocal.setRemoteAccess(remoteAccess);
		}
	}

	protected JSONAction getJSONAction(ServletContext servletContext) {
		JSONAction jsonAction = new JSONServiceAction();

		jsonAction.setServletContext(servletContext);

		return jsonAction;
	}

	private static Log _log = LogFactoryUtil.getLog(JSONServlet.class);

	private JSONAction _jsonAction;
	private ClassLoader _pluginClassLoader;

}