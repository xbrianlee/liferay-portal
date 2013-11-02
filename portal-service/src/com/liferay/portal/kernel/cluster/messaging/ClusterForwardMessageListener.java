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

package com.liferay.portal.kernel.cluster.messaging;

import com.liferay.portal.kernel.cluster.ClusterLinkUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Shuyang Zhou
 */
public class ClusterForwardMessageListener implements ClusterMessageListener {

	@Override
	public void receive(Message message) {
		String destinationName = message.getDestinationName();

		if (Validator.isNotNull(destinationName)) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Forwarding cluster link message " + message + " to " +
						destinationName);
			}

			ClusterLinkUtil.setForwardMessage(message);

			MessageBusUtil.sendMessage(destinationName, message);
		}
		else {
			if (_log.isErrorEnabled()) {
				_log.error(
					"Forwarded cluster link message has no destination " +
						message);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ClusterForwardMessageListener.class);

}