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

import com.liferay.portal.kernel.cluster.Address;
import com.liferay.portal.kernel.cluster.ClusterLinkUtil;
import com.liferay.portal.kernel.cluster.Priority;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Shuyang Zhou
 */
public class ClusterBridgeMessageListener extends BaseMessageListener {

	public void setActive(boolean active) {
		_active = active;
	}

	public void setPriority(Priority priority) {
		_priority = priority;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		if (!_active) {
			return;
		}

		if (ClusterLinkUtil.isForwardMessage(message)) {
			return;
		}

		Address address = ClusterLinkUtil.getAddress(message);

		if (address == null) {
			if (_log.isInfoEnabled()) {
				_log.info("Bridging cluster link multicast message " + message);
			}

			ClusterLinkUtil.sendMulticastMessage(message, _priority);
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Bridging cluster link unicast message " + message +
						" to " + address);
			}

			ClusterLinkUtil.sendUnicastMessage(address, message, _priority);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ClusterBridgeMessageListener.class);

	private boolean _active = true;
	private Priority _priority;

}