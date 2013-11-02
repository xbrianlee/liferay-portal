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

package com.liferay.portal.spring.hibernate;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;

import org.hibernate.Session;

/**
 * @author Shuyang Zhou
 */
public class LastSessionRecorderUtil {

	public static void syncLastSessionState() throws SystemException {
		Session session = _lastSessionThreadLocal.get();

		if ((session != null) && session.isOpen()) {
			try {
				session.flush();
				session.clear();
			}
			catch (Exception e) {
				throw new SystemException(e);
			}
		}
	}

	protected static void setLastSession(Session session) {
		_lastSessionThreadLocal.set(session);
	}

	private static ThreadLocal<Session> _lastSessionThreadLocal =
		new AutoResetThreadLocal<Session>(
			LastSessionRecorderUtil.class.getName() +
				"._lastSessionThreadLocal");

}