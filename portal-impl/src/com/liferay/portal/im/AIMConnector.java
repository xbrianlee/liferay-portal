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

package com.liferay.portal.im;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;

import org.walluck.oscar.AIMConnection;
import org.walluck.oscar.AIMSession;
import org.walluck.oscar.client.Oscar;

/**
 * @author Brian Wing Shun Chan
 * @author Brett Randall
 * @author Bruno Farache
 */
public class AIMConnector {

	public static void disconnect() {
		if (_instance != null) {
			_instance._disconnect();
		}
	}

	public static void send(String to, String msg) {
		_instance._send(to, msg);
	}

	private AIMConnector() {
	}

	private void _connect() {
		String login = PropsUtil.get(PropsKeys.AIM_LOGIN);
		String password = PropsUtil.get(PropsKeys.AIM_PASSWORD);

		AIMSession ses = new AIMSession();

		ses.setSN(login);

		Oscar oscar = new Oscar();

		oscar.setSN(login);
		oscar.setPassword(password);

		ses.init();
	}

	private void _disconnect() {
		if (_aim != null) {
			AIMConnection.killAllInSess(_aim);
		}
	}

	private void _send(String to, String msg) {
		try {
			if (_aim == null) {
				_connect();

				// Daim's listeners are buggy. Instead, just wait a second
				// before sending the first message.

				Thread.sleep(1000);
			}

			_oscar.sendIM(_aim, to, msg, Oscar.getICQCaps());
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Could not send AIM message");
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AIMConnector.class);

	private static AIMConnector _instance = new AIMConnector();

	private AIMSession _aim;
	private Oscar _oscar;

}