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

package com.liferay.portal.module.framework;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Miguel Pastor
 * @author Raymond Augé
 * @see    ModuleFrameworkClassLoader
 */
public class ModuleFrameworkServletAdapter extends HttpServlet {

	public HttpServlet addingService(Object serviceReference) {
		return (HttpServlet)_moduleFrameworkAdapterHelper.execute(
			"addingService", serviceReference);
	}

	@Override
	public void destroy() {
		_moduleFrameworkAdapterHelper.execute("destroy");
	}

	@Override
	public void init(ServletConfig servletConfig) {
		_moduleFrameworkAdapterHelper.exec(
			"init", new Class[] {ServletConfig.class}, servletConfig);
	}

	public void modifiedService(
		Object serviceReference, HttpServlet httpService) {

		_moduleFrameworkAdapterHelper.execute(
			"modifiedService", serviceReference, httpService);
	}

	public void removedService(
		Object serviceReference, HttpServlet httpService) {

		_moduleFrameworkAdapterHelper.execute(
			"removedService", serviceReference, httpService);
	}

	@Override
	protected void service(
		HttpServletRequest request, HttpServletResponse response) {

		_moduleFrameworkAdapterHelper.exec(
			"service",
			new Class[] {HttpServletRequest.class, HttpServletResponse.class},
			request, response);
	}

	private static ModuleFrameworkAdapterHelper _moduleFrameworkAdapterHelper =
		new ModuleFrameworkAdapterHelper(
			"com.liferay.osgi.bootstrap.ModuleFrameworkServlet");

}