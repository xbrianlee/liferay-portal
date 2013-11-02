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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import javax.servlet.Servlet;

/**
 * @author Shuyang Zhou
 */
public class DirectServletRegistryUtil {

	public static void clearServlets() {
		getDirectServletRegistry().clearServlets();
	}

	public static DirectServletRegistry getDirectServletRegistry() {
		PortalRuntimePermission.checkGetBeanProperty(
			DirectServletRegistryUtil.class);

		return _directServletRegistry;
	}

	public static Servlet getServlet(String path) {
		return getDirectServletRegistry().getServlet(path);
	}

	public static void putServlet(String path, Servlet servlet) {
		getDirectServletRegistry().putServlet(path, servlet);
	}

	public void setDirectServletRegistry(
		DirectServletRegistry directServletRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_directServletRegistry = directServletRegistry;
	}

	private static DirectServletRegistry _directServletRegistry;

}