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

package com.liferay.portal.kernel.messaging.sender;

import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Michael C. Han
 */
public class DefaultSingleDestinationMessageSender
	implements SingleDestinationMessageSender {

	public DefaultSingleDestinationMessageSender() {
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	public DefaultSingleDestinationMessageSender(
		String destinationName, MessageSender messageSender) {

		_destinationName = destinationName;
		_messageSender = messageSender;
	}

	@Override
	public void send(Message message) {
		_messageSender.send(_destinationName, message);
	}

	@Override
	public void send(Object payload) {
		Message message = new Message();

		message.setPayload(payload);

		send(message);
	}

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

	public void setMessageSender(MessageSender messageSender) {
		_messageSender = messageSender;
	}

	private String _destinationName;
	private MessageSender _messageSender;

}