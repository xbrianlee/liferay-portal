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
import com.liferay.portal.kernel.messaging.MessageBusException;

/**
 * @author Michael C. Han
 */
public class DefaultSingleDestinationSynchronousMessageSender
	implements SingleDestinationSynchronousMessageSender {

	public DefaultSingleDestinationSynchronousMessageSender() {
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	public DefaultSingleDestinationSynchronousMessageSender(
		String destinationName,
		SynchronousMessageSender synchronousMessageSender) {

		_destinationName = destinationName;
		_synchronousMessageSender = synchronousMessageSender;
	}

	@Override
	public Object send(Message message) throws MessageBusException {
		return _synchronousMessageSender.send(_destinationName, message);
	}

	@Override
	public Object send(Message message, long timeout)
		throws MessageBusException {

		return _synchronousMessageSender.send(
			_destinationName, message, timeout);
	}

	@Override
	public Object send(Object payload) throws MessageBusException {
		Message message = new Message();

		message.setPayload(payload);

		return send(message);
	}

	@Override
	public Object send(Object payload, long timeout)
		throws MessageBusException {

		Message message = new Message();

		message.setPayload(payload);

		return send(message, timeout);
	}

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

	public void setSynchronousMessageSender(
		SynchronousMessageSender synchronousMessageSender) {

		_synchronousMessageSender = synchronousMessageSender;
	}

	private String _destinationName;
	private SynchronousMessageSender _synchronousMessageSender;

}