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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

/**
 * @author Raymond Augé
 */
public class DirectRequestDispatcherFactoryUtil {

	public static DirectRequestDispatcherFactory
		getDirectRequestDispatcherFactory() {

		PortalRuntimePermission.checkGetBeanProperty(
			DirectRequestDispatcherFactoryUtil.class);

		return _directRequestDispatcherFactory;
	}

	public static RequestDispatcher getRequestDispatcher(
		ServletContext servletContext, String path) {

		return getDirectRequestDispatcherFactory().getRequestDispatcher(
			servletContext, path);
	}

	public static RequestDispatcher getRequestDispatcher(
		ServletRequest servletRequest, String path) {

		return getDirectRequestDispatcherFactory().getRequestDispatcher(
			servletRequest, path);
	}

	public void setDirectRequestDispatcherFactory(
		DirectRequestDispatcherFactory directRequestDispatcherFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_directRequestDispatcherFactory = directRequestDispatcherFactory;
	}

	private static DirectRequestDispatcherFactory
		_directRequestDispatcherFactory;

}