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

package com.liferay.util.mail;

import com.liferay.portal.kernel.util.ArrayUtil;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * @author Jorge Ferrer
 */
public class LiferayMimeMessage extends MimeMessage {

	public LiferayMimeMessage(Session session) {
		super(session);
	}

	@Override
	protected void updateMessageID() throws MessagingException {
		String[] messageIds = getHeader("Message-ID");

		if (ArrayUtil.isNotEmpty(messageIds)) {

			// Keep current value

			return;
		}

		super.updateMessageID();
	}

}