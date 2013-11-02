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

import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PortletURLListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletException;
import javax.portlet.PortletURLGenerationListener;
import javax.portlet.UnavailableException;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletURLListenerFactory {

	public static PortletURLGenerationListener create(
			PortletURLListener portletURLListener)
		throws PortletException {

		return _instance._create(portletURLListener);
	}

	public static void destroy(PortletURLListener portletURLListener) {
		_instance._destroy(portletURLListener);
	}

	private PortletURLListenerFactory() {
		_pool = new ConcurrentHashMap
			<String, Map<String, PortletURLGenerationListener>>();
	}

	private PortletURLGenerationListener _create(
			PortletURLListener portletURLListener)
		throws PortletException {

		PortletApp portletApp = portletURLListener.getPortletApp();

		Map<String, PortletURLGenerationListener>
			portletURLGenerationListeners = _pool.get(
				portletApp.getServletContextName());

		if (portletURLGenerationListeners == null) {
			portletURLGenerationListeners =
				new ConcurrentHashMap<String, PortletURLGenerationListener>();

			_pool.put(
				portletApp.getServletContextName(),
				portletURLGenerationListeners);
		}

		PortletURLGenerationListener portletURLGenerationListener =
			portletURLGenerationListeners.get(
				portletURLListener.getListenerClass());

		if (portletURLGenerationListener != null) {
			return portletURLGenerationListener;
		}

		if (portletApp.isWARFile()) {
			PortletContextBag portletContextBag = PortletContextBagPool.get(
				portletApp.getServletContextName());

			portletURLGenerationListener =
				portletContextBag.getPortletURLListeners().get(
					portletURLListener.getListenerClass());

			portletURLGenerationListener = _init(
				portletURLListener, portletURLGenerationListener);
		}
		else {
			portletURLGenerationListener = _init(portletURLListener);
		}

		portletURLGenerationListeners.put(
			portletURLListener.getListenerClass(),
			portletURLGenerationListener);

		return portletURLGenerationListener;
	}

	private void _destroy(PortletURLListener portletURLListener) {
		PortletApp portletApp = portletURLListener.getPortletApp();

		Map<String, PortletURLGenerationListener>
			portletURLGenerationListeners = _pool.get(
				portletApp.getServletContextName());

		if (portletURLGenerationListeners == null) {
			return;
		}

		PortletURLGenerationListener portletURLGenerationListener =
			portletURLGenerationListeners.get(
				portletURLListener.getListenerClass());

		if (portletURLGenerationListener == null) {
			return;
		}

		portletURLGenerationListeners.remove(
			portletURLListener.getListenerClass());
	}

	private PortletURLGenerationListener _init(
			PortletURLListener portletURLListener)
		throws PortletException {

		return _init(portletURLListener, null);
	}

	private PortletURLGenerationListener _init(
			PortletURLListener portletURLListener,
			PortletURLGenerationListener portletURLGenerationListener)
		throws PortletException {

		try {
			if (portletURLGenerationListener == null) {
				portletURLGenerationListener =
					(PortletURLGenerationListener)InstanceFactory.newInstance(
						portletURLListener.getListenerClass());
			}
		}
		catch (Exception e) {
			throw new UnavailableException(e.getMessage());
		}

		return portletURLGenerationListener;
	}

	private static PortletURLListenerFactory _instance =
		new PortletURLListenerFactory();

	private Map<String, Map<String, PortletURLGenerationListener>> _pool;

}