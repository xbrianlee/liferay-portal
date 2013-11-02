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

import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.resiliency.spi.agent.SPIAgentRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Brian Myunghun Kim
 */
public class SharedSessionServletRequest extends HttpServletRequestWrapper {

	public SharedSessionServletRequest(
		HttpServletRequest request, boolean shared) {

		super(request);

		_portalSession = request.getSession();
		_shared = shared;
	}

	@Override
	public HttpSession getSession() {
		return getSession(true);
	}

	@Override
	public HttpSession getSession(boolean create) {
		if (create) {
			checkPortalSession();
		}

		if (_shared) {
			return _portalSession;
		}

		HttpSession portletSession = super.getSession(create);

		if ((portletSession != null) && (portletSession != _portalSession)) {
			SPIAgentRequest.populatePortletSessionAttributes(
				this, portletSession);

			return getSharedSessionWrapper(_portalSession, portletSession);
		}

		return portletSession;
	}

	public HttpSession getSharedSession() {
		return _portalSession;
	}

	protected void checkPortalSession() {
		try {
			_portalSession.isNew();
		}
		catch (IllegalStateException ise) {
			_portalSession = super.getSession(true);
		}
	}

	protected HttpSession getSharedSessionWrapper(
		HttpSession portalSession, HttpSession portletSession) {

		if (ServerDetector.isJetty()) {
			return new JettySharedSessionWrapper(portalSession, portletSession);
		}
		else {
			return new SharedSessionWrapper(portalSession, portletSession);
		}
	}

	private HttpSession _portalSession;
	private boolean _shared;

}