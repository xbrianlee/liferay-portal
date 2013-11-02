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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.security.lang.DoPrivilegedUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletContext;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletContextFactory {

	public static PortletContext create(
		Portlet portlet, ServletContext servletContext) {

		return _instance._create(portlet, servletContext);
	}

	public static void destroy(Portlet portlet) {
		_instance._destroy(portlet);
	}

	private PortletContextFactory() {
		_pool = new ConcurrentHashMap<String, Map<String, PortletContext>>();
	}

	private PortletContext _create(
		Portlet portlet, ServletContext servletContext) {

		Map<String, PortletContext> portletContexts = _pool.get(
			portlet.getRootPortletId());

		if (portletContexts == null) {
			portletContexts = new ConcurrentHashMap<String, PortletContext>();

			_pool.put(portlet.getRootPortletId(), portletContexts);
		}

		PortletContext portletContext = portletContexts.get(
			portlet.getPortletId());

		if (portletContext != null) {
			return DoPrivilegedUtil.wrap(portletContext);
		}

		PortletApp portletApp = portlet.getPortletApp();

		if (portletApp.isWARFile()) {
			PortletBag portletBag = PortletBagPool.get(
				portlet.getRootPortletId());

			if (portletBag == null) {
				_log.error(
					"Portlet " + portlet.getRootPortletId() +
						" has a null portlet bag");
			}

			//String mainPath = (String)ctx.getAttribute(WebKeys.MAIN_PATH);

			servletContext = portletBag.getServletContext();

			// Context path for the portal must be passed to individual portlets

			//ctx.setAttribute(WebKeys.MAIN_PATH, mainPath);
		}

		portletContext = new PortletContextImpl(portlet, servletContext);

		portletContexts.put(portlet.getPortletId(), portletContext);

		return DoPrivilegedUtil.wrap(portletContext);
	}

	private void _destroy(Portlet portlet) {
		_pool.remove(portlet.getRootPortletId());
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortletContextFactory.class);

	private static PortletContextFactory _instance =
		new PortletContextFactory();

	private Map<String, Map<String, PortletContext>> _pool;

}