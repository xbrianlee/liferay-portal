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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class MessageBatch implements Serializable {

	public MessageBatch(int initialSize) {
		this(null, initialSize);
	}

	public MessageBatch(String messageBatchId) {
		this(messageBatchId, 10);
	}

	public MessageBatch(String messageBatchId, int initialSize) {
		_messageBatchId = messageBatchId;
		_messages = new ArrayList<Message>(initialSize);
	}

	public void addMessage(Message message) {
		_messages.add(message);
	}

	public String getMessageBatchId() {
		return _messageBatchId;
	}

	public List<Message> getMessages() {
		return _messages;
	}

	private String _messageBatchId;
	private List<Message> _messages;

}