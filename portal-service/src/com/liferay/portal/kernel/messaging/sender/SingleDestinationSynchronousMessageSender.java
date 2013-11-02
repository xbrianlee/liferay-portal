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
public interface SingleDestinationSynchronousMessageSender {

	public Object send(Message message) throws MessageBusException;

	public Object send(Message message, long timeout)
		throws MessageBusException;

	public Object send(Object payload) throws MessageBusException;

	public Object send(Object payload, long timeout) throws MessageBusException;

}