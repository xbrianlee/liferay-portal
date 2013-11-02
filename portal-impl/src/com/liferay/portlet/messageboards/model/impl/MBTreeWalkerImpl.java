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

package com.liferay.portlet.messageboards.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBTreeWalker;
import com.liferay.portlet.messageboards.service.MBMessageLocalService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class MBTreeWalkerImpl implements MBTreeWalker {

	public MBTreeWalkerImpl(
		MBMessage message, int status,
		MBMessageLocalService messageLocalService) {

		_messageIdsMap = new HashMap<Long, Integer>();

		try {
			_messages = messageLocalService.getThreadMessages(
				message.getThreadId(), status);

			for (int i = 0; i < _messages.size(); i++) {
				MBMessage curMessage = _messages.get(i);

				long parentMessageId = curMessage.getParentMessageId();

				if (!curMessage.isRoot() &&
					!_messageIdsMap.containsKey(parentMessageId)) {

					_messageIdsMap.put(parentMessageId, i);
				}
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public List<MBMessage> getChildren(MBMessage message) {
		List<MBMessage> children = new ArrayList<MBMessage>();

		int[] range = getChildrenRange(message);

		for (int i = range[0]; i < range[1]; i++) {
			children.add(_messages.get(i));
		}

		return children;
	}

	@Override
	public int[] getChildrenRange(MBMessage message) {
		long messageId = message.getMessageId();

		Integer pos = _messageIdsMap.get(messageId);

		if (pos == null) {
			return new int[] {0, 0};
		}

		int[] range = new int[2];
		range[0] = pos.intValue();

		for (int i = range[0]; i < _messages.size(); i++) {
			MBMessage curMessage = _messages.get(i);

			if (curMessage.getParentMessageId() == messageId) {
				range[1] = i + 1;
			}
			else {
				break;
			}
		}

		return range;
	}

	@Override
	public List<MBMessage> getMessages() {
		return _messages;
	}

	@Override
	public MBMessage getRoot() {
		return _messages.get(0);
	}

	@Override
	public boolean isLeaf(MBMessage message) {
		Long messageIdObj = new Long(message.getMessageId());

		if (_messageIdsMap.containsKey(messageIdObj)) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean isOdd() {
		_odd = !_odd;

		return _odd;
	}

	private static Log _log = LogFactoryUtil.getLog(MBTreeWalkerImpl.class);

	private Map<Long, Integer> _messageIdsMap;
	private List<MBMessage> _messages;
	private boolean _odd;

}