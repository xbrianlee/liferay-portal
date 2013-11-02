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

import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * See http://issues.liferay.com/browse/LEP-2297.
 * </p>
 *
 * @author Olaf Fricke
 * @author Brian Wing Shun Chan
 */
public class PortalDelegatorServlet extends HttpServlet {

	public static void addDelegate(String subContext, HttpServlet delegate) {
		if (subContext == null) {
			throw new IllegalArgumentException();
		}

		if (delegate == null) {
			throw new IllegalArgumentException();
		}

		_delegates.put(subContext, delegate);
	}

	public static void removeDelegate(String subContext) {
		if (subContext == null) {
			throw new IllegalArgumentException();
		}

		_delegates.remove(subContext);
	}

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		String uri = request.getPathInfo();

		if ((uri == null) || (uri.length() == 0)) {
			response.sendError(
				HttpServletResponse.SC_NOT_FOUND,
				"Path information is not specified");

			return;
		}

		String[] paths = uri.split(StringPool.SLASH);

		if (paths.length < 2) {
			response.sendError(
				HttpServletResponse.SC_NOT_FOUND,
				"Path " + uri + " is invalid");

			return;
		}

		HttpServlet delegate = _delegates.get(paths[1]);

		if (delegate == null) {
			response.sendError(
				HttpServletResponse.SC_NOT_FOUND,
				"No servlet registred for context " + paths[1]);

			return;
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			ClassLoader delegateClassLoader =
				delegate.getClass().getClassLoader();

			currentThread.setContextClassLoader(delegateClassLoader);

			delegate.service(request, response);
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	private static Map<String, HttpServlet> _delegates =
		new HashMap<String, HttpServlet>();

}