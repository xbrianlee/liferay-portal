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

import java.io.Serializable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

/**
 * @author Michael C. Han
 */
public class SharedSessionAttributeCache implements Serializable {

	public static SharedSessionAttributeCache getInstance(HttpSession session) {
		synchronized (session) {
			SharedSessionAttributeCache cache =
				(SharedSessionAttributeCache)session.getAttribute(_SESSION_KEY);

			if (cache == null) {
				cache = new SharedSessionAttributeCache();

				session.setAttribute(_SESSION_KEY, cache);
			}

			return cache;
		}
	}

	public boolean contains(String name) {
		return _attributes.containsKey(name);
	}

	public Map<String, Object> getValues() {
		return _attributes;
	}

	public void removeAttribute(String key) {
		_attributes.remove(key);
	}

	public void setAttribute(String key, Object value) {
		_attributes.put(key, value);
	}

	private SharedSessionAttributeCache() {
		_attributes = new ConcurrentHashMap<String, Object>();
	}

	private static final String _SESSION_KEY =
		SharedSessionAttributeCache.class.getName();

	private Map<String, Object> _attributes;

}