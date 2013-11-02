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

import com.liferay.portal.kernel.concurrent.ConcurrentHashSet;
import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdSplitterUtil;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * See http://issues.liferay.com/browse/LEP-1466.
 * </p>
 *
 * @author Rudy Hilado
 * @author Shuyang Zhou
 */
public class PortletSessionTracker {

	public static void add(HttpSession session) {
		String sessionId = session.getId();

		if (CompoundSessionIdSplitterUtil.hasSessionDelimiter()) {
			sessionId = CompoundSessionIdSplitterUtil.parseSessionId(sessionId);
		}

		Set<HttpSession> sessions = _sessions.get(sessionId);

		if (sessions == null) {
			sessions = new ConcurrentHashSet<HttpSession>();

			Set<HttpSession> previousSessions = _sessions.putIfAbsent(
				sessionId, sessions);

			if (previousSessions != null) {
				sessions = previousSessions;
			}
		}

		sessions.add(session);
	}

	public static void invalidate(String sessionId) {
		if (CompoundSessionIdSplitterUtil.hasSessionDelimiter()) {
			sessionId = CompoundSessionIdSplitterUtil.parseSessionId(sessionId);
		}

		Set<HttpSession> sessions = _sessions.remove(sessionId);

		if (sessions == null) {
			return;
		}

		for (HttpSession session : sessions) {
			try {
				session.invalidate();
			}
			catch (Exception e) {
			}
		}
	}

	private static ConcurrentMap<String, Set<HttpSession>> _sessions =
		new ConcurrentHashMap<String, Set<HttpSession>>();

}