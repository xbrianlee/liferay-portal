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
import com.liferay.portal.kernel.messaging.MessageBus;

/**
 * @author Michael C. Han
 */
public class DefaultMessageSender implements MessageSender {

	@Override
	public void send(String destinationName, Message message) {
		_messageBus.sendMessage(destinationName, message);
	}

	public void setMessageBus(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	private MessageBus _messageBus;

}