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

package com.liferay.portal.security.pacl.servlet;

import com.liferay.portal.kernel.servlet.PluginContextListener;

import java.io.IOException;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Raymond Augé
 */
public class PACLRequestDispatcherWrapper implements RequestDispatcher {

	public PACLRequestDispatcherWrapper(
		ServletContext servletContext, RequestDispatcher requestDispatcher) {

		_servletContext = servletContext;
		_requestDispatcher = requestDispatcher;
	}

	@Override
	public void forward(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		doDispatch(servletRequest, servletResponse, false);
	}

	@Override
	public void include(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		doDispatch(servletRequest, servletResponse, true);
	}

	protected void doDispatch(
			ServletRequest servletRequest, ServletResponse servletResponse,
			boolean include)
		throws IOException, ServletException {

		ClassLoader pluginClassLoader =
			(ClassLoader)_servletContext.getAttribute(
				PluginContextListener.PLUGIN_CLASS_LOADER);

		DispatchPrivilegedExceptionAction dispatchPrivilegedExceptionAction =
			new DispatchPrivilegedExceptionAction(
				_requestDispatcher, servletRequest, servletResponse, include);

		try {
			if (pluginClassLoader == null) {
				AccessController.doPrivileged(
					dispatchPrivilegedExceptionAction);
			}
			else {
				dispatchPrivilegedExceptionAction.run();
			}
		}
		catch (PrivilegedActionException pae) {
			Exception e = pae.getException();

			if (e instanceof IOException) {
				throw (IOException)e;
			}

			throw (ServletException)pae.getException();
		}
	}

	private RequestDispatcher _requestDispatcher;
	private ServletContext _servletContext;

	private class DispatchPrivilegedExceptionAction
		implements PrivilegedExceptionAction<Void> {

		public DispatchPrivilegedExceptionAction(
			RequestDispatcher requestDispatcher, ServletRequest servletRequest,
			ServletResponse servletResponse, boolean include) {

			_requestDispatcher = requestDispatcher;
			_servletRequest = servletRequest;
			_servletResponse = servletResponse;
			_include = include;
		}

		@Override
		public Void run() throws IOException, ServletException {
			if (_include) {
				_requestDispatcher.include(_servletRequest, _servletResponse);
			}
			else {
				_requestDispatcher.forward(_servletRequest, _servletResponse);
			}

			return null;
		}

		private boolean _include;
		private RequestDispatcher _requestDispatcher;
		private ServletRequest _servletRequest;
		private ServletResponse _servletResponse;

	}

}