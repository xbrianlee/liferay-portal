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

import com.liferay.portal.kernel.cache.ThreadLocalCacheManager;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageListener;

/**
 * @author Michael C. Han
 */
public class SynchronousMessageListener implements MessageListener {

	public SynchronousMessageListener(
		MessageBus messageBus, Message message, long timeout) {

		_messageBus = messageBus;
		_message = message;
		_timeout = timeout;
		_responseId = _message.getResponseId();
	}

	public Object getResults() {
		return _results;
	}

	@Override
	public void receive(Message message) {
		if (!message.getResponseId().equals(_responseId)) {
			return;
		}

		synchronized (this) {
			_results = message.getPayload();

			notify();
		}
	}

	public Object send() throws MessageBusException {
		String destinationName = _message.getDestinationName();
		String responseDestinationName = _message.getResponseDestinationName();

		_messageBus.registerMessageListener(responseDestinationName, this);

		try {
			synchronized (this) {
				_messageBus.sendMessage(destinationName, _message);

				wait(_timeout);

				if (_results == null) {
					throw new MessageBusException(
						"No reply received for message: " + _message);
				}
			}

			return _results;
		}
		catch (InterruptedException ie) {
			throw new MessageBusException(
				"Message sending interrupted for: " + _message, ie);
		}
		finally {
			_messageBus.unregisterMessageListener(
				responseDestinationName, this);

			EntityCacheUtil.clearLocalCache();
			FinderCacheUtil.clearLocalCache();
			ThreadLocalCacheManager.destroy();
		}
	}

	private Message _message;
	private MessageBus _messageBus;
	private String _responseId;
	private Object _results;
	private long _timeout;

}