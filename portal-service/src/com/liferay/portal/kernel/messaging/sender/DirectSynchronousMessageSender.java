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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.messaging.SynchronousDestination;
import com.liferay.portal.kernel.nio.intraband.messaging.IntrabandBridgeDestination;

import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public class DirectSynchronousMessageSender
	implements SynchronousMessageSender {

	@Override
	public Object send(String destinationName, Message message)
		throws MessageBusException {

		Destination destination = _messageBus.getDestination(destinationName);

		if (destination == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Destination " + destinationName + " is not configured");
			}

			return null;
		}

		if ((destination instanceof IntrabandBridgeDestination) ||
			(destination instanceof SynchronousDestination)) {

			destination.send(message);
		}
		else {
			Set<MessageListener> messageListeners =
				destination.getMessageListeners();

			for (MessageListener messageListener : messageListeners) {
				try {
					messageListener.receive(message);
				}
				catch (MessageListenerException mle) {
					throw new MessageBusException(mle);
				}
			}
		}

		return message.getResponse();
	}

	@Override
	public Object send(String destinationName, Message message, long timeout)
		throws MessageBusException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				DirectSynchronousMessageSender.class.getName() +
					" does not support timeout");
		}

		return send(destinationName, message);
	}

	public void setMessageBus(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	private static Log _log = LogFactoryUtil.getLog(
		DirectSynchronousMessageSender.class);

	private MessageBus _messageBus;

}