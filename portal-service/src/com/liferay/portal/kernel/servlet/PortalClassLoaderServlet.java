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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalLifecycle;
import com.liferay.portal.kernel.util.PortalLifecycleUtil;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalClassLoaderServlet
	extends HttpServlet implements PortalLifecycle {

	@Override
	public void destroy() {
		portalDestroy();
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

		_servletConfig = servletConfig;

		PortalLifecycleUtil.register(this);
	}

	@Override
	public void portalDestroy() {
		if (!_calledPortalDestroy) {
			PortalLifecycleUtil.removeDestroy(this);

			doPortalDestroy();

			_calledPortalDestroy = true;
		}
	}

	@Override
	public void portalInit() {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		try {
			currentThread.setContextClassLoader(portalClassLoader);

			String servletClass = _servletConfig.getInitParameter(
				"servlet-class");

			_servlet = (HttpServlet)InstanceFactory.newInstance(
				portalClassLoader, servletClass);

			_servlet.init(_servletConfig);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(
				PortalClassLoaderUtil.getClassLoader());

			_servlet.service(request, response);
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	protected void doPortalDestroy() {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(
				PortalClassLoaderUtil.getClassLoader());

			if (_servlet != null) {
				_servlet.destroy();
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalClassLoaderServlet.class);

	private boolean _calledPortalDestroy;
	private HttpServlet _servlet;
	private ServletConfig _servletConfig;

}