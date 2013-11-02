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

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionListener;

/**
 * <p>
 * See http://issues.liferay.com/browse/LEP-2299.
 * </p>
 *
 * @author Olaf Fricke
 * @author Brian Wing Shun Chan
 */
public class PortletSessionListenerLoader implements ServletContextListener {

	public PortletSessionListenerLoader(
		HttpSessionListener httpSessionListener) {

		_httpSessionListener = httpSessionListener;
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		PortletSessionListenerManager.removeHttpSessionListener(
			_httpSessionListener);

		_httpSessionListener = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		PortletSessionListenerManager.addHttpSessionListener(
			_httpSessionListener);
	}

	private HttpSessionListener _httpSessionListener;

}