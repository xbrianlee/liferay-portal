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

import com.liferay.portal.kernel.cache.cluster.PortalCacheClusterChannel;
import com.liferay.portal.kernel.cache.cluster.PortalCacheClusterChannelFactory;
import com.liferay.portal.kernel.cache.cluster.PortalCacheClusterException;
import com.liferay.portal.kernel.cluster.Priority;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Shuyang Zhou
 */
public class ClusterLinkPortalCacheClusterChannelFactory
	implements PortalCacheClusterChannelFactory {

	@Override
	public PortalCacheClusterChannel createPortalCacheClusterChannel()
		throws PortalCacheClusterException {

		int count = _counter.getAndIncrement();

		if (count >= _priorities.size()) {
			throw new IllegalStateException(
				"Cannot create more than " + _priorities.size() + " channels");
		}

		return new ClusterLinkPortalCacheClusterChannel(
			_destinationName, _priorities.get(count));
	}

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

	public void setPriorities(List<Priority> priorities) {
		_priorities = priorities;

		Collections.sort(priorities);
	}

	private AtomicInteger _counter = new AtomicInteger(0);
	private String _destinationName;
	private List<Priority> _priorities;

}