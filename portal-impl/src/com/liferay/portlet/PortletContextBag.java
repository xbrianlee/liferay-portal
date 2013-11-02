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

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletURLGenerationListener;
import javax.portlet.filter.PortletFilter;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletContextBag {

	public PortletContextBag(String servletContextName) {
		_servletContextName = servletContextName;
	}

	public Map<String, CustomUserAttributes> getCustomUserAttributes() {
		return _customUserAttributes;
	}

	public Map<String, PortletFilter> getPortletFilters() {
		return _portletFilters;
	}

	public Map<String, PortletURLGenerationListener> getPortletURLListeners() {
		return _urlListeners;
	}

	public String getServletContextName() {
		return _servletContextName;
	}

	private Map<String, CustomUserAttributes> _customUserAttributes =
		new HashMap<String, CustomUserAttributes>();
	private Map<String, PortletFilter> _portletFilters =
		new HashMap<String, PortletFilter>();
	private String _servletContextName;
	private Map<String, PortletURLGenerationListener> _urlListeners =
		new HashMap<String, PortletURLGenerationListener>();

}