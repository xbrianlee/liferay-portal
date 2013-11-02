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

package com.liferay.osgi.bootstrap;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.launch.Framework;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Raymond Augé
 */
public class ModuleFrameworkServlet extends HttpServlet
	implements ServiceTrackerCustomizer<HttpServlet, HttpServlet> {

	@Override
	public HttpServlet addingService(
		ServiceReference<HttpServlet> serviceReference) {

		_httpServlet = _bundleContext.getService(serviceReference);

		return _httpServlet;
	}

	@Override
	public void destroy() {
		Framework framework = (Framework)ModuleFrameworkUtil.getFramework();

		if (framework == null) {
			return;
		}

		_serviceTracker.close();
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

		Framework framework = (Framework)ModuleFrameworkUtil.getFramework();

		if (framework == null) {
			return;
		}

		_bundleContext = framework.getBundleContext();

		try {
			Filter filter = _bundleContext.createFilter(
				"(&(bean.id=" + HttpServlet.class.getName() +
					")(original.bean=*))");

			_serviceTracker = new ServiceTracker<HttpServlet, HttpServlet>(
				_bundleContext, filter, this);

			_serviceTracker.open();
		}
		catch (InvalidSyntaxException ise) {
			_log.error(ise, ise);
		}
	}

	@Override
	public void modifiedService(
		ServiceReference<HttpServlet> serviceReference,
		HttpServlet httpService) {

		_httpServlet = _bundleContext.getService(serviceReference);
	}

	@Override
	public void removedService(
		ServiceReference<HttpServlet> serviceReference,
		HttpServlet httpService) {

		_httpServlet = null;

		_bundleContext.ungetService(serviceReference);
	}

	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		if (_httpServlet == null) {
			response.sendError(
				HttpServletResponse.SC_SERVICE_UNAVAILABLE,
				"Module framework is unavailable");

			return;
		}

		_httpServlet.service(request, response);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ModuleFrameworkServlet.class);

	private BundleContext _bundleContext;
	private HttpServlet _httpServlet;
	private ServiceTracker<HttpServlet, HttpServlet> _serviceTracker;

}