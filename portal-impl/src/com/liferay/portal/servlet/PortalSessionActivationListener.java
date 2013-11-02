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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.util.TransientValue;

import java.io.Serializable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * @author Alexander Chow
 */
public class PortalSessionActivationListener
	implements HttpSessionActivationListener, Serializable {

	public static PortalSessionActivationListener getInstance() {
		return _instance;
	}

	public static PortalSessionActivationListener getInstance(
		HttpSession session) {

		TransientValue<PortalSessionActivationListener> transientValue =
			(TransientValue<PortalSessionActivationListener>)
				session.getAttribute(
					PortalSessionActivationListener.class.getName());

		PortalSessionActivationListener portalSessionActivationListener = null;

		if (transientValue != null) {
			portalSessionActivationListener = transientValue.getValue();
		}

		return portalSessionActivationListener;
	}

	public static void setInstance(HttpSession session) {
		TransientValue<PortalSessionActivationListener> transientValue =
			new TransientValue<PortalSessionActivationListener>(
				PortalSessionActivationListener.getInstance());

		session.setAttribute(
			PortalSessionActivationListener.class.getName(), transientValue);
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
		new PortalSessionCreator(httpSessionEvent);
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
	}

	private static PortalSessionActivationListener _instance =
		new PortalSessionActivationListener();

}