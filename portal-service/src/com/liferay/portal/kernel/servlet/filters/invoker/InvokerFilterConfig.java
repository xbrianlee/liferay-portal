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

package com.liferay.portal.kernel.servlet.filters.invoker;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class InvokerFilterConfig implements FilterConfig {

	public InvokerFilterConfig(
		ServletContext servletContext, String filterName,
		Map<String, String> initParameterMap) {

		_servletContext = servletContext;
		_filterName = filterName;
		_initParameterMap = initParameterMap;
	}

	@Override
	public String getFilterName() {
		return _filterName;
	}

	@Override
	public String getInitParameter(String key) {
		return _initParameterMap.get(key);
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		return new Enumeration<String>() {

			@Override
			public boolean hasMoreElements() {
				return _keys.hasNext();
			}

			@Override
			public String nextElement() {
				return _keys.next();
			}

			private Iterator<String> _keys =
				_initParameterMap.keySet().iterator();

		};
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	private String _filterName;
	private Map<String, String> _initParameterMap;
	private ServletContext _servletContext;

}