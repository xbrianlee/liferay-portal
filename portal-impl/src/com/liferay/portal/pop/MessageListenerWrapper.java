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

package com.liferay.portal.pop;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.pop.MessageListener;
import com.liferay.portal.kernel.pop.MessageListenerException;

import javax.mail.Message;

/**
 * @author Brian Wing Shun Chan
 */
public class MessageListenerWrapper implements MessageListener {

	public MessageListenerWrapper(MessageListener listener) {
		_listener = listener;
	}

	@Override
	public boolean accept(String from, String recipient, Message message) {
		if (_log.isDebugEnabled()) {
			_log.debug("Listener " + _listener.getClass().getName());
			_log.debug("From " + from);
			_log.debug("Recipient " + recipient);
		}

		boolean value = _listener.accept(from, recipient, message);

		if (_log.isDebugEnabled()) {
			_log.debug("Accept " + value);
		}

		return value;
	}

	@Override
	public void deliver(String from, String recipient, Message message)
		throws MessageListenerException {

		if (_log.isDebugEnabled()) {
			_log.debug("Listener " + _listener.getClass().getName());
			_log.debug("From " + from);
			_log.debug("Recipient " + recipient);
			_log.debug("Message " + message);
		}

		_listener.deliver(from, recipient, message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MessageListenerWrapper)) {
			return false;
		}

		MessageListenerWrapper listener = (MessageListenerWrapper)obj;

		String id = listener.getId();

		return getId().equals(id);
	}

	@Override
	public String getId() {
		return _listener.getId();
	}

	@Override
	public int hashCode() {
		return _listener.getId().hashCode();
	}

	private static Log _log = LogFactoryUtil.getLog(
		MessageListenerWrapper.class);

	private MessageListener _listener;

}