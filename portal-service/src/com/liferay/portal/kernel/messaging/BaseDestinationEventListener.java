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

/**
 * @author Michael C. Han
 */
public class BaseDestinationEventListener implements DestinationEventListener {

	@Override
	public void destinationAdded(Destination destination) {
	}

	@Override
	public void destinationRemoved(Destination destination) {
	}

	@Override
	public void messageListenerRegistered(
		String destinationName, MessageListener messageListener) {
	}

	@Override
	public void messageListenerUnregistered(
		String destinationName, MessageListener messageListener) {
	}

}