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

package com.liferay.portal.cache.cluster.clusterlink;

import com.liferay.portal.kernel.cache.cluster.BasePortalCacheClusterChannel;
import com.liferay.portal.kernel.cache.cluster.PortalCacheClusterEvent;
import com.liferay.portal.kernel.cluster.ClusterLinkUtil;
import com.liferay.portal.kernel.cluster.Priority;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Shuyang Zhou
 */
public class ClusterLinkPortalCacheClusterChannel
	extends BasePortalCacheClusterChannel {

	public ClusterLinkPortalCacheClusterChannel(
		String destinationName, Priority priority) {

		_destinationName = destinationName;
		_priority = priority;
	}

	@Override
	public void dispatchEvent(PortalCacheClusterEvent portalCacheClusterEvent) {
		Message message = new Message();

		message.setDestinationName(_destinationName);
		message.setPayload(portalCacheClusterEvent);

		ClusterLinkUtil.sendMulticastMessage(message, _priority);
	}

	private String _destinationName;
	private Priority _priority;

}