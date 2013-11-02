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

package com.liferay.util.bridges.alloy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Connor McKay
 */
public class AlloyFriendlyURLMapper extends DefaultFriendlyURLMapper {

	@Override
	public String buildPath(LiferayPortletURL liferayPortletURL) {
		Map<String, String> routeParameters = new HashMap<String, String>();

		buildRouteParameters(liferayPortletURL, routeParameters);

		// Populate method parameter based on the portlet lifecycle

		String lifecycle = liferayPortletURL.getLifecycle();

		if (lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			routeParameters.put("method", HttpMethods.POST);
		}
		else {
			routeParameters.put("method", HttpMethods.GET);
		}

		// Map URL with router

		String friendlyURLPath = router.parametersToUrl(routeParameters);

		if (friendlyURLPath == null) {
			return null;
		}

		// Remove mapped parameters from URL

		addParametersIncludedInPath(liferayPortletURL, routeParameters);

		// Remove method

		int pos = friendlyURLPath.indexOf(CharPool.SLASH);

		if (pos != -1) {
			friendlyURLPath = friendlyURLPath.substring(pos);
		}
		else {
			friendlyURLPath = StringPool.BLANK;
		}

		// Add mapping

		friendlyURLPath = StringPool.SLASH.concat(getMapping()).concat(
			friendlyURLPath);

		return friendlyURLPath;
	}

	@Override
	public void populateParams(
		String friendlyURLPath, Map<String, String[]> parameterMap,
		Map<String, Object> requestContext) {

		// Determine lifecycle from request method

		HttpServletRequest request = (HttpServletRequest)requestContext.get(
			"request");

		friendlyURLPath =
			request.getMethod() +
				friendlyURLPath.substring(getMapping().length() + 1);

		if (friendlyURLPath.endsWith(StringPool.SLASH)) {
			friendlyURLPath = friendlyURLPath.substring(
				0, friendlyURLPath.length() - 1);
		}

		Map<String, String> routeParameters = new HashMap<String, String>();

		if (!router.urlToParameters(friendlyURLPath, routeParameters)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No route could be found to match URL " + friendlyURLPath);
			}

			return;
		}

		String portletId = getPortletId(routeParameters);

		if (portletId == null) {
			return;
		}

		String namespace = PortalUtil.getPortletNamespace(portletId);

		addParameter(namespace, parameterMap, "p_p_id", portletId);
		addParameter(parameterMap, "p_p_lifecycle", getLifecycle(request));

		populateParams(parameterMap, namespace, routeParameters);
	}

	protected String getLifecycle(HttpServletRequest request) {
		String method = request.getMethod();

		if (StringUtil.equalsIgnoreCase(method, HttpMethods.POST)) {
			return "1";
		}

		return ParamUtil.getString(request, "p_p_lifecycle", "0");
	}

	private static Log _log = LogFactoryUtil.getLog(
		AlloyFriendlyURLMapper.class);

}