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

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Manuel de la Peña
 */
public class MailServiceTestUtil {

	public static int getInboxSize() {
		return _simpleSmtpServer.getReceivedEmailSize();
	}

	public static List<SmtpMessage> getMessages(
		String headerName, String headerValue) {

		List<SmtpMessage> smtpMessages = new ArrayList<SmtpMessage>();

		Iterator<SmtpMessage> iterator = _simpleSmtpServer.getReceivedEmail();

		while (iterator.hasNext()) {
			SmtpMessage smtpMessage = iterator.next();

			if (headerName.equals("Body")) {
				String body = smtpMessage.getBody();

				if (body.equals(headerValue)) {
					smtpMessages.add(smtpMessage);
				}
			}
			else {
				String smtpMessageHeaderValue = smtpMessage.getHeaderValue(
					headerName);

				if (smtpMessageHeaderValue.equals(headerValue)) {
					smtpMessages.add(smtpMessage);
				}
			}
		}

		return smtpMessages;
	}

	public static void start() {
		if (_simpleSmtpServer != null) {
			throw new IllegalStateException("Server is already running");
		}

		_simpleSmtpServer = SimpleSmtpServer.start(
			PropsValues.MAIL_SESSION_MAIL_SMTP_PORT);
	}

	public static void stop() {
		if ((_simpleSmtpServer != null) && _simpleSmtpServer.isStopped()) {
			throw new IllegalStateException("Server is already stopped");
		}

		_simpleSmtpServer.stop();

		_simpleSmtpServer = null;
	}

	private static SimpleSmtpServer _simpleSmtpServer;

}