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

package com.liferay.portal.kernel.cache.cluster;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class PortalCacheClusterLink {

	public void afterPropertiesSet() {
		_portalCacheClusterChannels = new ArrayList<PortalCacheClusterChannel>(
			_channelNumber);

		for (int i = 0; i < _channelNumber; i++) {
			_portalCacheClusterChannels.add(
				_portalCacheClusterChannelFactory.
					createPortalCacheClusterChannel());
		}

		if (_portalCacheClusterChannelSelector == null) {
			_portalCacheClusterChannelSelector =
				new UniformPortalCacheClusterChannelSelector();
		}
	}

	public void destroy() {
		for (PortalCacheClusterChannel portalCacheClusterChannel :
				_portalCacheClusterChannels) {

			portalCacheClusterChannel.destroy();
		}
	}

	public long getSubmittedEventNumber() {
		return _portalCacheClusterChannelSelector.getSelectedNumber();
	}

	public void sendEvent(PortalCacheClusterEvent portalCacheClusterEvent) {
		PortalCacheClusterChannel portalCacheClusterChannel =
			_portalCacheClusterChannelSelector.select(
				_portalCacheClusterChannels, portalCacheClusterEvent);

		portalCacheClusterChannel.sendEvent(portalCacheClusterEvent);
	}

	public void setChannelNumber(int channelNumber) {
		_channelNumber = channelNumber;
	}

	public void setPortalCacheClusterChannelFactory(
		PortalCacheClusterChannelFactory portalCacheClusterChannelFactory) {

		_portalCacheClusterChannelFactory = portalCacheClusterChannelFactory;
	}

	public void setPortalCacheClusterChannelSelector(
		PortalCacheClusterChannelSelector portalCacheClusterChannelSelector) {

		_portalCacheClusterChannelSelector = portalCacheClusterChannelSelector;
	}

	private static final int _DEFAULT_CHANNEL_NUMBER = 10;

	private int _channelNumber = _DEFAULT_CHANNEL_NUMBER;
	private PortalCacheClusterChannelFactory _portalCacheClusterChannelFactory;
	private List<PortalCacheClusterChannel> _portalCacheClusterChannels;
	private PortalCacheClusterChannelSelector
		_portalCacheClusterChannelSelector;

}