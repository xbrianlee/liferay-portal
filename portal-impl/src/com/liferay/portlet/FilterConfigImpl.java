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

package com.liferay.portlet;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

import javax.portlet.PortletContext;
import javax.portlet.filter.FilterConfig;

/**
 * @author Brian Wing Shun Chan
 */
public class FilterConfigImpl implements FilterConfig {

	public FilterConfigImpl(
		String filterName, PortletContext portletContext,
		Map<String, String> params) {

		_filterName = filterName;
		_portletContext = portletContext;
		_params = params;
	}

	@Override
	public String getFilterName() {
		return _filterName;
	}

	@Override
	public String getInitParameter(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		return _params.get(name);
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		return Collections.enumeration(_params.keySet());
	}

	@Override
	public PortletContext getPortletContext() {
		return _portletContext;
	}

	private String _filterName;
	private Map<String, String> _params;
	private PortletContext _portletContext;

}