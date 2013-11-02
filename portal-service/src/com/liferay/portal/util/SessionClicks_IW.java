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

package com.liferay.portal.util;

/**
 * @author Brian Wing Shun Chan
 */
public class SessionClicks_IW {
	public static SessionClicks_IW getInstance() {
		return _instance;
	}

	public java.lang.String get(javax.servlet.http.HttpServletRequest request,
		java.lang.String key, java.lang.String defaultValue) {
		return SessionClicks.get(request, key, defaultValue);
	}

	public java.lang.String get(javax.servlet.http.HttpServletRequest request,
		java.lang.String namespace, java.lang.String key,
		java.lang.String defaultValue) {
		return SessionClicks.get(request, namespace, key, defaultValue);
	}

	public java.lang.String get(javax.servlet.http.HttpSession session,
		java.lang.String key, java.lang.String defaultValue) {
		return SessionClicks.get(session, key, defaultValue);
	}

	public java.lang.String get(javax.servlet.http.HttpSession session,
		java.lang.String namespace, java.lang.String key,
		java.lang.String defaultValue) {
		return SessionClicks.get(session, namespace, key, defaultValue);
	}

	public void put(javax.servlet.http.HttpServletRequest request,
		java.lang.String key, java.lang.String value) {
		SessionClicks.put(request, key, value);
	}

	public void put(javax.servlet.http.HttpServletRequest request,
		java.lang.String namespace, java.lang.String key, java.lang.String value) {
		SessionClicks.put(request, namespace, key, value);
	}

	public void put(javax.servlet.http.HttpSession session,
		java.lang.String key, java.lang.String value) {
		SessionClicks.put(session, key, value);
	}

	public void put(javax.servlet.http.HttpSession session,
		java.lang.String namespace, java.lang.String key, java.lang.String value) {
		SessionClicks.put(session, namespace, key, value);
	}

	private SessionClicks_IW() {
	}

	private static SessionClicks_IW _instance = new SessionClicks_IW();
}