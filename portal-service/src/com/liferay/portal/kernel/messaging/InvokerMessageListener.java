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

package com.liferay.portal.kernel.messaging;

/**
 * @author Michael C. Han
 */
public class InvokerMessageListener implements MessageListener {

	public InvokerMessageListener(MessageListener messageListener) {
		this(messageListener, Thread.currentThread().getContextClassLoader());
	}

	public InvokerMessageListener(
		MessageListener messageListener, ClassLoader classLoader) {

		_messageListener = messageListener;
		_classLoader = classLoader;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InvokerMessageListener)) {
			return false;
		}

		InvokerMessageListener messageListenerInvoker =
			(InvokerMessageListener)obj;

		return _messageListener.equals(
			messageListenerInvoker.getMessageListener());
	}

	public ClassLoader getClassLoader() {
		return _classLoader;
	}

	public MessageListener getMessageListener() {
		return _messageListener;
	}

	@Override
	public int hashCode() {
		return _messageListener.hashCode();
	}

	@Override
	public void receive(Message message) throws MessageListenerException {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		currentThread.setContextClassLoader(_classLoader);

		try {
			_messageListener.receive(message);
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	private ClassLoader _classLoader;
	private MessageListener _messageListener;

}