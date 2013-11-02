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

package com.liferay.portal.kernel.messaging.jmx;

import com.liferay.portal.kernel.messaging.MessageBus;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class MessageBusManager implements MessageBusManagerMBean {

	public static ObjectName createObjectName() {
		try {
			return new ObjectName(_OBJECT_NAME);
		}
		catch (MalformedObjectNameException mone) {
			throw new IllegalStateException(mone);
		}
	}

	public MessageBusManager(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	@Override
	public int getDestinationCount() {
		return _messageBus.getDestinationCount();
	}

	private static final String _OBJECT_NAME =
		"Liferay:product=Portal,type=MessageBusManager,host=localhost";

	private MessageBus _messageBus;

}