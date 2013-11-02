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

package com.liferay.support.tomcat.poller.comet;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.poller.comet.BaseCometSession;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.comet.CometEvent;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class CatalinaCometSession extends BaseCometSession {

	public CatalinaCometSession(CometEvent cometEvent) {
		_cometEvent = cometEvent;
	}

	@Override
	public Object getAttribute(String name) {
		HttpServletRequest request = _cometEvent.getHttpServletRequest();

		HttpSession session = request.getSession();

		return session.getAttribute(name);
	}

	@Override
	public void setAttribute(String name, Object object) {
		HttpServletRequest request = _cometEvent.getHttpServletRequest();

		HttpSession session = request.getSession();

		session.setAttribute(name, object);
	}

	@Override
	protected void doClose() throws SystemException {
		try {
			_cometEvent.close();
		}
		catch (IllegalStateException ise) {
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	private CometEvent _cometEvent;

}