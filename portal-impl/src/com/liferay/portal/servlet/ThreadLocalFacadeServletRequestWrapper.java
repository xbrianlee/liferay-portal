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

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

import java.io.Closeable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;

/**
 * @author Shuyang Zhou
 */
public class ThreadLocalFacadeServletRequestWrapper
	extends ServletRequestWrapper implements Closeable {

	public ThreadLocalFacadeServletRequestWrapper(
		ServletRequestWrapper servletRequestWrapper,
		ServletRequest nextServletRequest) {

		super(nextServletRequest);

		_servletRequestWrapper = servletRequestWrapper;

		_nextServletRequestThreadLocal.set(nextServletRequest);

		_locales = new ArrayList<Locale>();

		Enumeration<Locale> enumeration = nextServletRequest.getLocales();

		while (enumeration.hasMoreElements()) {
			_locales.add(enumeration.nextElement());
		}
	}

	@Override
	public void close() {
		if (_servletRequestWrapper != null) {
			ServletRequest nextServletRequest =
				_nextServletRequestThreadLocal.get();

			_servletRequestWrapper.setRequest(nextServletRequest);
		}
	}

	@Override
	public Object getAttribute(String name) {
		ServletRequest servletRequest = getRequest();

		return servletRequest.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		ServletRequest servletRequest = getRequest();

		return servletRequest.getAttributeNames();
	}

	@Override
	public Enumeration<Locale> getLocales() {
		return Collections.enumeration(_locales);
	}

	@Override
	public ServletRequest getRequest() {
		return _nextServletRequestThreadLocal.get();
	}

	@Override
	public void removeAttribute(String name) {
		ServletRequest servletRequest = getRequest();

		servletRequest.removeAttribute(name);
	}

	@Override
	public void setAttribute(String name, Object o) {
		ServletRequest servletRequest = getRequest();

		servletRequest.setAttribute(name, o);
	}

	@Override
	public void setRequest(ServletRequest servletRequest) {
		_nextServletRequestThreadLocal.set(servletRequest);
	}

	private static ThreadLocal<ServletRequest> _nextServletRequestThreadLocal =
		new AutoResetThreadLocal<ServletRequest>(
			ThreadLocalFacadeServletRequestWrapper.class +
				"._nextServletRequestThreadLocal") {

			@Override
			protected ServletRequest copy(ServletRequest servletRequest) {
				return servletRequest;
			}

		};

	private List<Locale> _locales;
	private ServletRequestWrapper _servletRequestWrapper;

}