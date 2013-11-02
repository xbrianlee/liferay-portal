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
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;

/**
 * @author Shuyang Zhou
 */
public class DestinationConfigurationProcessCallable
	implements ProcessCallable<Boolean> {

	public DestinationConfigurationProcessCallable(String destinationName) {
		_destinationName = destinationName;
	}

	@Override
	public Boolean call() throws ProcessException {
		MessageBus messageBus = MessageBusUtil.getMessageBus();

		Destination destination = messageBus.getDestination(_destinationName);

		if (destination == null) {
			throw new ProcessException("No such destination " + destination);
		}

		if (destination instanceof IntrabandBridgeDestination) {
			return Boolean.FALSE;
		}

		IntrabandBridgeDestination intrabandBridgeDestination =
			new IntrabandBridgeDestination(destination);

		messageBus.addDestination(intrabandBridgeDestination);

		return Boolean.TRUE;
	}

	private static final long serialVersionUID = 1L;

	private String _destinationName;

}