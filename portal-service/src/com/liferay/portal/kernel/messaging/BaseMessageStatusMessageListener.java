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

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.sender.MessageSender;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public abstract class BaseMessageStatusMessageListener
	implements MessageListener {

	public BaseMessageStatusMessageListener() {
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	public BaseMessageStatusMessageListener(
		SingleDestinationMessageSender statusSender,
		MessageSender responseSender) {

		_statusSender = statusSender;
		_responseSender = responseSender;
	}

	@Override
	public void receive(Message message) {
		MessageStatus messageStatus = new MessageStatus();

		messageStatus.startTimer();

		try {
			doReceive(message, messageStatus);
		}
		catch (Exception e) {
			_log.error(
				"Unable to process request " + message.getDestinationName(), e);

			messageStatus.setException(e);
		}
		finally {
			messageStatus.stopTimer();

			_statusSender.send(messageStatus);
		}
	}

	public void setResponseSender(MessageSender responseSender) {
		_responseSender = responseSender;
	}

	public void setStatusSender(SingleDestinationMessageSender statusSender) {
		_statusSender = statusSender;
	}

	protected abstract void doReceive(
			Message message, MessageStatus messageStatus)
		throws Exception;

	protected MessageSender getResponseSender() {
		return _responseSender;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseMessageStatusMessageListener.class);

	private MessageSender _responseSender;
	private SingleDestinationMessageSender _statusSender;

}