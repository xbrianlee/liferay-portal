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

package com.liferay.portal.kernel.servlet;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class HttpSessionWrapper implements HttpSession {

	public HttpSessionWrapper(HttpSession session) {
		_session = session;
	}

	@Override
	public Object getAttribute(String name) {
		return _session.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return _session.getAttributeNames();
	}

	@Override
	public long getCreationTime() {
		return _session.getCreationTime();
	}

	@Override
	public String getId() {
		return _session.getId();
	}

	@Override
	public long getLastAccessedTime() {
		return _session.getLastAccessedTime();
	}

	@Override
	public int getMaxInactiveInterval() {
		return _session.getMaxInactiveInterval();
	}

	@Override
	public ServletContext getServletContext() {
		return _session.getServletContext();
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	@Override
	public javax.servlet.http.HttpSessionContext getSessionContext() {
		return _session.getSessionContext();
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	@Override
	public Object getValue(String name) {
		return _session.getValue(name);
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	@Override
	public String[] getValueNames() {
		return _session.getValueNames();
	}

	public HttpSession getWrappedSession() {
		return _session;
	}

	@Override
	public void invalidate() {
		_session.invalidate();
	}

	@Override
	public boolean isNew() {
		return _session.isNew();
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	@Override
	public void putValue(String name, Object value) {
		_session.putValue(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		_session.removeAttribute(name);
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	@Override
	public void removeValue(String name) {
		_session.removeValue(name);
	}

	@Override
	public void setAttribute(String name, Object value) {
		_session.setAttribute(name, value);
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		_session.setMaxInactiveInterval(interval);
	}

	private HttpSession _session;

}