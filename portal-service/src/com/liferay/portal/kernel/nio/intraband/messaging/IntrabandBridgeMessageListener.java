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

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.SystemDataType;

/**
 * @author Shuyang Zhou
 */
public class IntrabandBridgeMessageListener implements MessageListener {

	public IntrabandBridgeMessageListener(
		RegistrationReference registrationReference) {

		_registrationReference = registrationReference;

		_intraband = registrationReference.getIntraband();

		SystemDataType systemDataType = SystemDataType.MESSAGE;

		_messageType = systemDataType.getValue();
	}

	@Override
	public void receive(Message message) {
		MessageRoutingBag messageRoutingBag = new MessageRoutingBag(
			message, false);

		_intraband.sendDatagram(
			_registrationReference,
			Datagram.createRequestDatagram(
				_messageType, messageRoutingBag.toByteArray()));
	}

	private final Intraband _intraband;
	private final byte _messageType;
	private final RegistrationReference _registrationReference;

}