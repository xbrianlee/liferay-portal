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

package com.liferay.portal.model.impl;

import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PortletURLListener;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletURLListenerImpl implements PortletURLListener {

	public PortletURLListenerImpl(String listenerClass, PortletApp portletApp) {
		_listenerClass = listenerClass;
		_portletApp = portletApp;
	}

	@Override
	public String getListenerClass() {
		return _listenerClass;
	}

	@Override
	public PortletApp getPortletApp() {
		return _portletApp;
	}

	@Override
	public void setListenerClass(String listenerClass) {
		_listenerClass = listenerClass;
	}

	@Override
	public void setPortletApp(PortletApp portletApp) {
		_portletApp = portletApp;
	}

	private String _listenerClass;
	private PortletApp _portletApp;

}