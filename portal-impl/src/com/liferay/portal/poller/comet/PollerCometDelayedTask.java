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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.UnknownChannelException;
import com.liferay.portal.kernel.poller.comet.CometRequest;
import com.liferay.portal.kernel.poller.comet.CometResponse;
import com.liferay.portal.kernel.poller.comet.CometSession;

import java.util.List;

/**
 * @author Edward Han
 */
public class PollerCometDelayedTask {

	public PollerCometDelayedTask(
		CometSession cometSession, JSONObject pollerResponseHeaderJSONObject) {

		_cometSession = cometSession;
		_pollerResponseHeaderJSONObject = pollerResponseHeaderJSONObject;
	}

	public void executeTask() throws Exception {
		CometRequest cometRequest = _cometSession.getCometRequest();

		try {
			List<NotificationEvent> notificationEvents =
				ChannelHubManagerUtil.getNotificationEvents(
					cometRequest.getCompanyId(), cometRequest.getUserId(),
					false);

			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			if (_pollerResponseHeaderJSONObject != null) {
				jsonArray.put(_pollerResponseHeaderJSONObject);
			}

			for (NotificationEvent notificationEvent : notificationEvents) {
				jsonArray.put(notificationEvent.toJSONObject());
			}

			CometResponse cometResponse = _cometSession.getCometResponse();

			cometResponse.writeData(jsonArray.toString());

			ChannelHubManagerUtil.removeTransientNotificationEvents(
				cometRequest.getCompanyId(), cometRequest.getUserId(),
				notificationEvents);
		}
		catch (UnknownChannelException uce) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to complete processing because user session ended",
					uce);
			}
		}
		finally {
			_cometSession.close();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PollerCometDelayedTask.class);

	private CometSession _cometSession;
	private JSONObject _pollerResponseHeaderJSONObject;

}