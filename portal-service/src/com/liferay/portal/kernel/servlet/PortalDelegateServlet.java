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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.InstanceFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

/**
 * <p>
 * See http://issues.liferay.com/browse/LEP-2297.
 * </p>
 *
 * @author Olaf Fricke
 * @author Brian Wing Shun Chan
 */
public class PortalDelegateServlet extends SecureServlet {

	@Override
	protected void doPortalDestroy() {
		PortalDelegatorServlet.removeDelegate(_subContext);

		servlet.destroy();
	}

	@Override
	protected void doPortalInit() throws Exception {
		ServletContext servletContext = servletConfig.getServletContext();

		ClassLoader classLoader = (ClassLoader)servletContext.getAttribute(
			PluginContextListener.PLUGIN_CLASS_LOADER);

		String servletClass = servletConfig.getInitParameter("servlet-class");

		_subContext = servletConfig.getInitParameter("sub-context");

		if (_subContext == null) {
			_subContext = getServletName();
		}

		servlet = (Servlet)InstanceFactory.newInstance(
			classLoader, servletClass);

		if (!(servlet instanceof HttpServlet)) {
			throw new IllegalArgumentException(
				"servlet-class is not an instance of " +
					HttpServlet.class.getName());
		}

		servlet.init(servletConfig);

		PortalDelegatorServlet.addDelegate(_subContext, (HttpServlet)servlet);
	}

	private String _subContext;

}