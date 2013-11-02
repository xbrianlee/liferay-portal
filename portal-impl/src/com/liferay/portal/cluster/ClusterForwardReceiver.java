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

package com.liferay.portal.cluster;

import com.liferay.portal.kernel.cluster.messaging.ClusterForwardMessageListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import org.jgroups.Address;
import org.jgroups.Message;

/**
 * @author Shuyang Zhou
 */
public class ClusterForwardReceiver extends BaseReceiver {

	public ClusterForwardReceiver(
		List<Address> localTransportAddresses,
		ClusterForwardMessageListener clusterForwardMessageListener) {

		_localTransportAddresses = localTransportAddresses;
		_clusterForwardMessageListener = clusterForwardMessageListener;
	}

	@Override
	public void receive(Message message) {
		if (!_localTransportAddresses.contains(message.getSrc()) ||
			(message.getDest() != null)) {

			_clusterForwardMessageListener.receive(
				(com.liferay.portal.kernel.messaging.Message)
					message.getObject());
		}
		else {
			if (_log.isDebugEnabled()) {
				_log.debug("Block received message " + message);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ClusterForwardReceiver.class);

	private ClusterForwardMessageListener _clusterForwardMessageListener;
	private List<org.jgroups.Address> _localTransportAddresses;

}