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

package com.liferay.portal.backgroundtask.messaging;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusMessageTranslator;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskStatusMessageListener extends BaseMessageListener {

	public BackgroundTaskStatusMessageListener(
		long backgroundTaskId, BackgroundTaskStatusMessageTranslator
		backgroundTaskStatusMessageTranslator) {

		_backgroundTaskId = backgroundTaskId;
		_backgroundTaskStatusMessageTranslator =
			backgroundTaskStatusMessageTranslator;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		long backgroundTaskId = message.getLong("backgroundTaskId");

		if (backgroundTaskId != _backgroundTaskId) {
			return;
		}

		BackgroundTaskStatus backgroundTaskStatus =
			BackgroundTaskStatusRegistryUtil.getBackgroundTaskStatus(
				backgroundTaskId);

		if (backgroundTaskStatus == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to locate status for background task " +
						backgroundTaskId + " to process " + message);
			}

			return;
		}

		_backgroundTaskStatusMessageTranslator.translate(
			backgroundTaskStatus, message);
	}

	private static Log _log = LogFactoryUtil.getLog(
		BackgroundTaskStatusMessageListener.class);

	private long _backgroundTaskId;
	private BackgroundTaskStatusMessageTranslator
		_backgroundTaskStatusMessageTranslator;

}