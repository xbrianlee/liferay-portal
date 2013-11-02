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

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class DynamicServletConfig implements ServletConfig {

	public DynamicServletConfig(
		ServletConfig servletConfig, Map<String, String> params) {

		_servletConfig = servletConfig;
		_params = params;
	}

	@Override
	public String getInitParameter(String name) {
		return _params.get(name);
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		return Collections.enumeration(_params.keySet());
	}

	@Override
	public ServletContext getServletContext() {
		return _servletConfig.getServletContext();
	}

	@Override
	public String getServletName() {
		return _servletConfig.getServletName();
	}

	private Map<String, String> _params;
	private ServletConfig _servletConfig;

}