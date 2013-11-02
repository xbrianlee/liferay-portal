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

package com.liferay.portal.poller.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.notifications.UnknownChannelException;
import com.liferay.portal.kernel.poller.PollerHeader;
import com.liferay.portal.kernel.poller.PollerResponse;

/**
 * @author Edward Han
 */
public class PollerNotificationsBridgeMessageListener
	extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		Object messagePayload = message.getPayload();

		if (!(messagePayload instanceof PollerResponse)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Received message with payload not of type PollerResponse");
			}

			return;
		}

		PollerResponse pollerResponse = (PollerResponse)messagePayload;

		if (pollerResponse.isEmpty()) {
			return;
		}

		PollerHeader pollerHeader = pollerResponse.getPollerHeader();

		NotificationEvent notificationEvent =
			NotificationEventFactoryUtil.createNotificationEvent(
				System.currentTimeMillis(),
				PollerNotificationsBridgeMessageListener.class.getName(),
				pollerResponse.toJSONObject());

		try {
			ChannelHubManagerUtil.sendNotificationEvent(
				pollerHeader.getCompanyId(), pollerHeader.getUserId(),
				notificationEvent);
		}
		catch (UnknownChannelException uce) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to complete processing because user session ended",
					uce);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PollerNotificationsBridgeMessageListener.class);

}