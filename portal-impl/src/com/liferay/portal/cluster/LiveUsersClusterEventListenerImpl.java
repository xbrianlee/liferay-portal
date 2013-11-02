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

import com.liferay.portal.kernel.cluster.ClusterEvent;
import com.liferay.portal.kernel.cluster.ClusterEventListener;
import com.liferay.portal.kernel.cluster.ClusterEventType;
import com.liferay.portal.kernel.cluster.ClusterLink;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class LiveUsersClusterEventListenerImpl implements ClusterEventListener {

	@Override
	public void processClusterEvent(ClusterEvent clusterEvent) {
		List<ClusterNode> clusterNodes = clusterEvent.getClusterNodes();

		ClusterEventType clusterEventType = clusterEvent.getClusterEventType();

		if (clusterEventType.equals(ClusterEventType.DEPART)) {
			for (ClusterNode clusterNode : clusterNodes) {
				_processDepartEvent(clusterNode);
			}
		}
		else if (clusterEventType.equals(ClusterEventType.JOIN)) {
			for (ClusterNode clusterNode : clusterNodes) {
				_processJoinEvent(clusterNode);
			}
		}
	}

	private void _processDepartEvent(ClusterNode clusterNode) {
		Message message = new Message();

		message.put(ClusterLink.CLUSTER_FORWARD_MESSAGE, true);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("clusterNodeId", clusterNode.getClusterNodeId());
		jsonObject.put("command", "removeClusterNode");

		message.setPayload(jsonObject.toString());

		MessageBusUtil.sendMessage(DestinationNames.LIVE_USERS, message);
	}

	private void _processJoinEvent(ClusterNode clusterNode) {
		Message message = new Message();

		message.put(ClusterLink.CLUSTER_FORWARD_MESSAGE, true);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("clusterNodeId", clusterNode.getClusterNodeId());
		jsonObject.put("command", "addClusterNode");

		message.setPayload(jsonObject.toString());

		MessageBusUtil.sendMessage(DestinationNames.LIVE_USERS, message);
	}

}