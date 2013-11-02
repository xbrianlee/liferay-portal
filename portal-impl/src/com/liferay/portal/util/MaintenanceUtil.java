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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.PortalSessionContext;
import com.liferay.portal.kernel.util.Time;

import java.util.Collection;

import javax.servlet.http.HttpSession;

/**
 * @author Alexander Chow
 */
public class MaintenanceUtil {

	public static void appendStatus(String status) {
		_instance._appendStatus(status);
	}

	public static void cancel() {
		_instance._cancel();
	}

	public static String getClassName() {
		return _instance._getClassName();
	}

	public static String getSessionId() {
		return _instance._getSessionId();
	}

	public static String getStatus() {
		return _instance._getStatus();
	}

	public static boolean isMaintaining() {
		return _instance._isMaintaining();
	}

	public static void maintain(String sessionId, String className) {
		_instance._maintain(sessionId, className);
	}

	private MaintenanceUtil() {
	}

	private void _appendStatus(String status) {
		if (_log.isDebugEnabled()) {
			_log.debug(status);
		}

		_status.append(Time.getRFC822() + " " + status + "<br />");
	}

	private void _cancel() {
		HttpSession session = PortalSessionContext.get(_sessionId);

		if (session != null) {
			session.invalidate();
		}
		else {
			if (_log.isWarnEnabled()) {
				_log.warn("Session " + _sessionId + " is null");
			}
		}

		_maintaining = false;
	}

	private String _getClassName() {
		return _className;
	}

	private String _getSessionId() {
		return _sessionId;
	}

	private String _getStatus() {
		return _status.toString();
	}

	private boolean _isMaintaining() {
		return _maintaining;
	}

	private void _maintain(String sessionId, String className) {
		_sessionId = sessionId;
		_className = className;
		_maintaining = true;
		_status = new StringBuffer();

		_appendStatus("Executing " + _className);

		Collection<HttpSession> sessions = PortalSessionContext.values();

		for (HttpSession session : sessions) {
			if (!sessionId.equals(session.getId())) {
				try {
					session.invalidate();
				}
				catch (IllegalStateException ise) {
				}
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MaintenanceUtil.class);

	private static MaintenanceUtil _instance = new MaintenanceUtil();

	private String _className;
	private boolean _maintaining = false;
	private String _sessionId;
	private StringBuffer _status = new StringBuffer();

}