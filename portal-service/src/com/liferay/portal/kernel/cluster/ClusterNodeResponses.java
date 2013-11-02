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

package com.liferay.portal.kernel.cluster;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Michael C. Han
 */
public class ClusterNodeResponses implements Serializable {

	public static final ClusterNodeResponses EMPTY_CLUSTER_NODE_RESPONSES =
		new ClusterNodeResponses();

	public void addClusterResponse(ClusterNodeResponse clusterNodeResponse) {
		_clusterResponsesByAddress.put(
			clusterNodeResponse.getAddress(), clusterNodeResponse);

		ClusterNode clusterNode = clusterNodeResponse.getClusterNode();

		_clusterResponsesByClusterNode.put(
			clusterNode.getClusterNodeId(), clusterNodeResponse);

		_clusterResponsesQueue.offer(clusterNodeResponse);
	}

	public ClusterNodeResponse getClusterResponse(Address address) {
		return _clusterResponsesByAddress.get(address);
	}

	public ClusterNodeResponse getClusterResponse(ClusterNode clusterNode) {
		return getClusterResponse(clusterNode.getClusterNodeId());
	}

	public ClusterNodeResponse getClusterResponse(String clusterNodeId) {
		return _clusterResponsesByClusterNode.get(clusterNodeId);
	}

	public BlockingQueue<ClusterNodeResponse> getClusterResponses() {
		return _clusterResponsesQueue;
	}

	public int size() {
		return _clusterResponsesByClusterNode.size();
	}

	private Map<Address, ClusterNodeResponse> _clusterResponsesByAddress =
		new HashMap<Address, ClusterNodeResponse>();
	private Map<String, ClusterNodeResponse> _clusterResponsesByClusterNode =
		new HashMap<String, ClusterNodeResponse>();
	private BlockingQueue<ClusterNodeResponse> _clusterResponsesQueue =
		new LinkedBlockingQueue<ClusterNodeResponse>();

}