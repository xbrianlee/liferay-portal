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

package com.liferay.portal.poller.comet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.ChannelException;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.ChannelListener;
import com.liferay.portal.kernel.notifications.UnknownChannelException;
import com.liferay.portal.kernel.poller.comet.CometRequest;
import com.liferay.portal.kernel.poller.comet.CometSession;

/**
 * @author Edward Han
 */
public class PollerCometChannelListener implements ChannelListener {

	public PollerCometChannelListener(
		CometSession cometSession, JSONObject pollerResponseHeaderJSONObject) {

		_cometSession = cometSession;
		_pollerResponseHeaderJSONObject = pollerResponseHeaderJSONObject;
	}

	@Override
	public void channelListenerRemoved(long channelId) {
	}

	@Override
	public void notificationEventsAvailable(long channelId) {
		sendProcessMessage();
	}

	protected void sendProcessMessage() {
		CometRequest cometRequest = _cometSession.getCometRequest();

		try {
			ChannelHubManagerUtil.unregisterChannelListener(
				cometRequest.getCompanyId(), cometRequest.getUserId(), this);
		}
		catch (UnknownChannelException uce) {
		}
		catch (ChannelException ce) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to unregister channel listener", ce);
			}
		}

		PollerCometDelayedTask pollerCometDelayedTask =
			new PollerCometDelayedTask(
				_cometSession, _pollerResponseHeaderJSONObject);

		PollerCometDelayedJobUtil.addPollerCometDelayedTask(
			pollerCometDelayedTask);
	}

	private static Log _log = LogFactoryUtil.getLog(
		PollerCometChannelListener.class);

	private CometSession _cometSession;
	private JSONObject _pollerResponseHeaderJSONObject;

}