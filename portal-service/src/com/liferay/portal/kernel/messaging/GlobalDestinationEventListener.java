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

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.util.SetUtil;

import java.util.List;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public class GlobalDestinationEventListener
	extends BaseDestinationEventListener {

	public GlobalDestinationEventListener() {
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	public GlobalDestinationEventListener(
		MessageListener messageListener, List<String> ignoredDestinations) {

		_messageListener = messageListener;
		_ignoredDestinations = SetUtil.fromList(ignoredDestinations);
	}

	@Override
	public void destinationAdded(Destination destination) {
		if (!_ignoredDestinations.contains(destination.getName())) {
			destination.register(_messageListener);
		}
	}

	@Override
	public void destinationRemoved(Destination destination) {
		if (!_ignoredDestinations.contains(destination.getName())) {
			destination.unregister(_messageListener);
		}
	}

	public void setIgnoredDestinations(List<String> ignoredDestinations) {
		_ignoredDestinations = SetUtil.fromList(ignoredDestinations);
	}

	public void setMessageListener(MessageListener messageListener) {
		_messageListener = messageListener;
	}

	private Set<String> _ignoredDestinations;
	private MessageListener _messageListener;

}