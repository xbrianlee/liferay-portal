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

import com.liferay.portal.kernel.portlet.Route;
import com.liferay.portal.kernel.portlet.Router;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Connor McKay
 * @author Brian Wing Shun Chan
 */
public class RouterImpl implements Router {

	@Override
	public Route addRoute(String pattern) {
		Route route = new RouteImpl(pattern);

		_routes.add(route);

		return route;
	}

	@Override
	public String parametersToUrl(Map<String, String> parameters) {
		for (Route route : _routes) {
			String url = route.parametersToUrl(parameters);

			if (url != null) {
				return url;
			}
		}

		return null;
	}

	@Override
	public boolean urlToParameters(String url, Map<String, String> parameters) {
		for (Route route : _routes) {
			if (route.urlToParameters(url, parameters)) {
				return true;
			}
		}

		return false;
	}

	private List<Route> _routes = new ArrayList<Route>();

}