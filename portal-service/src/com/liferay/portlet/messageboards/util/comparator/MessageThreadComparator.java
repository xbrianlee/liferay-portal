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

package com.liferay.portlet.messageboards.util.comparator;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portlet.messageboards.model.MBMessage;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class MessageThreadComparator
	implements Comparator<MBMessage>, Serializable {

	@Override
	public int compare(MBMessage msg1, MBMessage msg2) {
		Long parentMessageId1 = new Long(msg1.getParentMessageId());
		Long parentMessageId2 = new Long(msg2.getParentMessageId());

		int value = parentMessageId1.compareTo(parentMessageId2);

		if (value == 0) {
			value = DateUtil.compareTo(
				msg1.getCreateDate(), msg2.getCreateDate());
		}

		if (value == 0) {
			Long messageId1 = new Long(msg1.getMessageId());
			Long messageId2 = new Long(msg2.getMessageId());

			value = messageId1.compareTo(messageId2);
		}

		return value;
	}

}