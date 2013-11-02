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

package com.liferay.util.servlet;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class NullSession implements HttpSession {

	public NullSession() {
		_attributes = new HashMap<String, Object>();
		_creationTime = System.currentTimeMillis();
		_id =
			NullSession.class.getName() + StringPool.POUND +
				StringUtil.randomId();
		_lastAccessedTime = _creationTime;
		_maxInactiveInterval = 0;
		_servletContext = null;
		_new = true;
	}

	@Override
	public Object getAttribute(String name) {
		return _attributes.get(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return Collections.enumeration(_attributes.keySet());
	}

	@Override
	public long getCreationTime() {
		return _creationTime;
	}

	@Override
	public String getId() {
		return _id;
	}

	@Override
	public long getLastAccessedTime() {
		return _lastAccessedTime;
	}

	@Override
	public int getMaxInactiveInterval() {
		return _maxInactiveInterval;
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	@Override
	public javax.servlet.http.HttpSessionContext getSessionContext() {
		return null;
	}

	@Override
	public Object getValue(String name) {
		return getAttribute(name);
	}

	@Override
	public String[] getValueNames() {
		List<String> names = ListUtil.fromEnumeration(getAttributeNames());

		return names.toArray(new String[0]);
	}

	@Override
	public void invalidate() {
		_attributes.clear();
	}

	@Override
	public boolean isNew() {
		return _new;
	}

	@Override
	public void putValue(String name, Object value) {
		setAttribute(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		_attributes.remove(name);
	}

	@Override
	public void removeValue(String name) {
		removeAttribute(name);
	}

	@Override
	public void setAttribute(String name, Object value) {
		_attributes.put(name, value);
	}

	@Override
	public void setMaxInactiveInterval(int maxInactiveInterval) {
		_maxInactiveInterval = maxInactiveInterval;
	}

	private Map<String, Object> _attributes;
	private long _creationTime;
	private String _id;
	private long _lastAccessedTime;
	private int _maxInactiveInterval;
	private boolean _new;
	private ServletContext _servletContext;

}