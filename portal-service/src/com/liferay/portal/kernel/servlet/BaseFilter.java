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
import com.liferay.portal.kernel.servlet.filters.invoker.FilterMapping;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 * @author Eduardo Lundgren
 */
public abstract class BaseFilter implements LiferayFilter {

	@Override
	public void destroy() {
		LiferayFilterTracker.removeLiferayFilter(this);
	}

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		try {
			HttpServletRequest request = (HttpServletRequest)servletRequest;
			HttpServletResponse response = (HttpServletResponse)servletResponse;

			if (_invokerEnabled) {
				processFilter(request, response, filterChain);
			}
			else {
				String uri = request.getRequestURI();

				if (isFilterEnabled() && isFilterEnabled(request, response) &&
					_filterMapping.isMatchURLRegexPattern(request, uri)) {

					processFilter(request, response, filterChain);
				}
				else {
					filterChain.doFilter(servletRequest, servletResponse);
				}
			}
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (ServletException se) {
			throw se;
		}
		catch (Exception e) {
			Log log = getLog();

			log.error(e, e);
		}
	}

	public FilterConfig getFilterConfig() {
		return _filterConfig;
	}

	@Override
	public void init(FilterConfig filterConfig) {
		_filterConfig = filterConfig;

		if (_TCK_URL) {
			ServletContext servletContext = _filterConfig.getServletContext();

			_invokerEnabled = GetterUtil.get(
				servletContext.getInitParameter("liferay-invoker-enabled"),
				true);

			if (!_invokerEnabled) {
				_filterMapping = new FilterMapping(
					this, filterConfig, new ArrayList<String>(0),
					new ArrayList<String>(0));
			}
		}

		LiferayFilterTracker.addLiferayFilter(this);
	}

	@Override
	public boolean isFilterEnabled() {
		return _filterEnabled;
	}

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		return _filterEnabled;
	}

	@Override
	public void setFilterEnabled(boolean filterEnabled) {
		_filterEnabled = filterEnabled;
	}

	protected abstract Log getLog();

	protected void processFilter(
			Class<?> filterClass, HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
		throws Exception {

		long startTime = 0;

		String threadName = null;
		String depther = null;
		String path = null;

		Log log = getLog();

		if (log.isDebugEnabled()) {
			startTime = System.currentTimeMillis();

			Thread currentThread = Thread.currentThread();

			threadName = currentThread.getName();

			depther = (String)request.getAttribute(_DEPTHER);

			if (depther == null) {
				depther = StringPool.BLANK;
			}
			else {
				depther += StringPool.EQUAL;
			}

			request.setAttribute(_DEPTHER, depther);

			path = request.getRequestURI();

			log.debug(
				"[" + threadName + "]" + depther + "> " +
					filterClass.getName() + " " + path);
		}

		filterChain.doFilter(request, response);

		if (!log.isDebugEnabled()) {
			return;
		}

		long endTime = System.currentTimeMillis();

		depther = (String)request.getAttribute(_DEPTHER);

		if (depther == null) {
			return;
		}

		log.debug(
			"[" + threadName + "]" + depther + "< " +
				filterClass.getName() + " " + path + " " +
					(endTime - startTime) + " ms");

		if (depther.length() > 0) {
			depther = depther.substring(1);
		}

		request.setAttribute(_DEPTHER, depther);
	}

	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		throw new UnsupportedOperationException(
			"Please implement processFilter(HttpServletRequest, " +
				"HttpServletResponse, FilterChain)");
	}

	private static final String _DEPTHER = "DEPTHER";

	private static final boolean _TCK_URL = GetterUtil.getBoolean(
		PropsUtil.get(PropsKeys.TCK_URL));

	private FilterConfig _filterConfig;
	private boolean _filterEnabled = true;
	private FilterMapping _filterMapping;
	private boolean _invokerEnabled = true;

}