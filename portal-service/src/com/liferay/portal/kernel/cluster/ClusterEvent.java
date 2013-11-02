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

import java.util.Arrays;
import java.util.List;

/**
 * @author Tina Tian
 */
public class ClusterEvent implements Serializable {

	public static ClusterEvent depart(ClusterNode... clusterNodes) {
		return new ClusterEvent(
			ClusterEventType.DEPART, Arrays.asList(clusterNodes));
	}

	public static ClusterEvent depart(List<ClusterNode> clusterNodes) {
		return new ClusterEvent(ClusterEventType.DEPART, clusterNodes);
	}

	public static ClusterEvent join(ClusterNode... clusterNodes) {
		return new ClusterEvent(
			ClusterEventType.JOIN, Arrays.asList(clusterNodes));
	}

	public static ClusterEvent join(List<ClusterNode> clusterNodes) {
		return new ClusterEvent(ClusterEventType.JOIN, clusterNodes);
	}

	public ClusterEvent(ClusterEventType clusterEventType) {
		_clusterEventType = clusterEventType;
	}

	public ClusterEvent(
		ClusterEventType clusterEventType, List<ClusterNode> clusterNodes) {

		_clusterEventType = clusterEventType;
		_clusterNodes = clusterNodes;
	}

	public ClusterEventType getClusterEventType() {
		return _clusterEventType;
	}

	public List<ClusterNode> getClusterNodes() {
		return _clusterNodes;
	}

	public void setClusterNodes(List<ClusterNode> clusterNodes) {
		_clusterNodes = clusterNodes;
	}

	private ClusterEventType _clusterEventType;
	private List<ClusterNode> _clusterNodes;

}