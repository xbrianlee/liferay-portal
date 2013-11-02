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

import com.liferay.portal.model.PortletApp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletContext;
import javax.portlet.filter.FilterConfig;

/**
 * @author Brian Wing Shun Chan
 */
public class FilterConfigFactory {

	public static FilterConfig create(
		com.liferay.portal.model.PortletFilter portletFilter,
		PortletContext ctx) {

		return _instance._create(portletFilter, ctx);
	}

	public static void destroy(
		com.liferay.portal.model.PortletFilter portletFilter) {

		_instance._destroy(portletFilter);
	}

	private FilterConfigFactory() {
		_pool = new ConcurrentHashMap<String, Map<String, FilterConfig>>();
	}

	private FilterConfig _create(
		com.liferay.portal.model.PortletFilter portletFilter,
		PortletContext ctx) {

		PortletApp portletApp = portletFilter.getPortletApp();

		Map<String, FilterConfig> filterConfigs = _pool.get(
			portletApp.getServletContextName());

		if (filterConfigs == null) {
			filterConfigs = new ConcurrentHashMap<String, FilterConfig>();

			_pool.put(portletApp.getServletContextName(), filterConfigs);
		}

		FilterConfig filterConfig = filterConfigs.get(
			portletFilter.getFilterName());

		if (filterConfig == null) {
			filterConfig = new FilterConfigImpl(
				portletFilter.getFilterName(), ctx,
				portletFilter.getInitParams());

			filterConfigs.put(portletFilter.getFilterName(), filterConfig);
		}

		return filterConfig;
	}

	private void _destroy(
		com.liferay.portal.model.PortletFilter portletFilter) {

		PortletApp portletApp = portletFilter.getPortletApp();

		_pool.remove(portletApp.getServletContextName());
	}

	private static FilterConfigFactory _instance = new FilterConfigFactory();

	private Map<String, Map<String, FilterConfig>> _pool;

}