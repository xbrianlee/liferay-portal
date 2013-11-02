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
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Tina Tian
 */
public class DebuggingClusterEventListenerImpl implements ClusterEventListener {

	@Override
	public void processClusterEvent(ClusterEvent clusterEvent) {
		if (!_log.isInfoEnabled()) {
			return;
		}

		ClusterEventType clusterEventType = clusterEvent.getClusterEventType();

		List<ClusterNode> clusterNodes = clusterEvent.getClusterNodes();

		StringBundler sb = new StringBundler(clusterNodes.size() * 3 + 3);

		sb.append("Cluster event ");
		sb.append(clusterEventType);
		sb.append(StringPool.NEW_LINE);

		for (ClusterNode clusterNode : clusterNodes) {
			sb.append("Cluster node ");
			sb.append(clusterNode);
			sb.append(StringPool.NEW_LINE);
		}

		sb.setIndex(sb.index() - 1);

		_log.info(sb.toString());
	}

	private static Log _log = LogFactoryUtil.getLog(
		DebuggingClusterEventListenerImpl.class);

}