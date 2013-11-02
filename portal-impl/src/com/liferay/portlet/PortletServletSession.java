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

import com.liferay.portal.kernel.servlet.HttpSessionWrapper;

import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletServletSession extends HttpSessionWrapper {

	public PortletServletSession(
		HttpSession session, PortletRequestImpl portletRequestImpl) {

		super(session);

		_portletRequestImpl = portletRequestImpl;
	}

	@Override
	public void invalidate() {
		super.invalidate();

		_portletRequestImpl.invalidateSession();
	}

	private PortletRequestImpl _portletRequestImpl;

}