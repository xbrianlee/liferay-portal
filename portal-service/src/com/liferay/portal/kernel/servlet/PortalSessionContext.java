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

import com.liferay.portal.kernel.util.Validator;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalSessionContext {

	public static int count() {
		return _instance._count();
	}

	public static HttpSession get(String sessionId) {
		return _instance._get(sessionId);
	}

	public static void put(String sessionId, HttpSession session) {
		_instance._put(sessionId, session);
	}

	public static HttpSession remove(String sessionId) {
		return _instance._remove(sessionId);
	}

	public static Collection<HttpSession> values() {
		return _instance._values();
	}

	protected PortalSessionContext() {
		_sessionPool = new ConcurrentHashMap<String, HttpSession>();
	}

	private int _count() {
		return _sessionPool.size();
	}

	private HttpSession _get(String sessionId) {
		if (Validator.isNull(sessionId)) {
			return null;
		}

		return _sessionPool.get(sessionId);
	}

	private void _put(String sessionId, HttpSession session) {
		_sessionPool.put(sessionId, session);
	}

	private HttpSession _remove(String sessionId) {
		return _sessionPool.remove(sessionId);
	}

	private Collection<HttpSession> _values() {
		return _sessionPool.values();
	}

	private static PortalSessionContext _instance = new PortalSessionContext();

	private Map<String, HttpSession> _sessionPool;

}