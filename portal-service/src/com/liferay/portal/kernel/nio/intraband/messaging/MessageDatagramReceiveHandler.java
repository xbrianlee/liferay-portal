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

package com.liferay.portal.kernel.nio.intraband.messaging;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.nio.intraband.BaseAsyncDatagramReceiveHandler;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;

import java.nio.ByteBuffer;

import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public class MessageDatagramReceiveHandler
	extends BaseAsyncDatagramReceiveHandler {

	public MessageDatagramReceiveHandler(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	@Override
	protected void doReceive(
			RegistrationReference registrationReference, Datagram datagram)
		throws Exception {

		ByteBuffer byteBuffer = datagram.getDataByteBuffer();

		MessageRoutingBag messageRoutingBag = MessageRoutingBag.fromByteArray(
			byteBuffer.array());

		Destination destination = _messageBus.getDestination(
			messageRoutingBag.getDestinationName());

		if (destination != null) {
			Set<MessageListener> messageListeners =
				destination.getMessageListeners();

			if (destination instanceof IntrabandBridgeDestination) {
				if (messageListeners.isEmpty()) {
					IntrabandBridgeDestination intrabandBridgeDestination =
						(IntrabandBridgeDestination)destination;

					intrabandBridgeDestination.sendMessageRoutingBag(
						messageRoutingBag);
				}
				else {
					destination.send(messageRoutingBag.getMessage());
				}
			}
			else {
				if (!messageListeners.isEmpty()) {
					for (MessageListener messageListener : messageListeners) {
						try {
							messageListener.receive(
								messageRoutingBag.getMessage());
						}
						catch (MessageListenerException mle) {
							throw new MessageBusException(mle);
						}
					}
				}
			}
		}

		if (messageRoutingBag.isSynchronizedBridge()) {
			Intraband intraband = registrationReference.getIntraband();

			intraband.sendDatagram(
				registrationReference,
				Datagram.createResponseDatagram(
					datagram, messageRoutingBag.toByteArray()));
		}
	}

	private MessageBus _messageBus;

}