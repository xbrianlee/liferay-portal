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
import com.liferay.portal.kernel.poller.PollerException;
import com.liferay.portal.kernel.poller.PollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;
import com.liferay.portal.poller.PollerProcessorUtil;
import com.liferay.portal.poller.PollerRequestResponsePair;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class PollerRequestMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		PollerRequestResponsePair pollerRequestResponsePair =
			(PollerRequestResponsePair)message.getPayload();

		PollerRequest pollerRequest =
			pollerRequestResponsePair.getPollerRequest();

		PollerResponse pollerResponse =
			pollerRequestResponsePair.getPollerResponse();

		String portletId = pollerRequest.getPortletId();

		PollerProcessor pollerProcessor =
			PollerProcessorUtil.getPollerProcessor(portletId);

		if (pollerRequest.isReceiveRequest()) {
			pollerResponse.createResponseMessage(message);

			try {
				pollerProcessor.receive(pollerRequest, pollerResponse);
			}
			catch (PollerException pe) {
				_log.error(
					"Unable to receive poller request " + pollerRequest, pe);

				pollerResponse.setParameter("pollerException", pe.getMessage());
			}
			finally {
				pollerResponse.close();
			}
		}
		else {
			try {
				pollerProcessor.send(pollerRequest);
			}
			catch (PollerException pe) {
				_log.error(
					"Unable to send poller request " + pollerRequest, pe);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PollerRequestMessageListener.class);

}