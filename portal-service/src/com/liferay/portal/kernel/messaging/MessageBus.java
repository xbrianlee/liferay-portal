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

import java.util.Collection;

/**
 * @author Michael C. Han
 */
public interface MessageBus {

	public void addDestination(Destination destination);

	public void addDestinationEventListener(
		DestinationEventListener destinationEventListener);

	public void addDestinationEventListener(
		String destinationName,
		DestinationEventListener destinationEventListener);

	public Destination getDestination(String destinationName);

	public int getDestinationCount();

	public Collection<String> getDestinationNames();

	public Collection<Destination> getDestinations();

	public boolean hasDestination(String destinationName);

	public boolean hasMessageListener(String destinationName);

	public boolean registerMessageListener(
		String destinationName, MessageListener messageListener);

	public Destination removeDestination(String destinationName);

	public void removeDestinationEventListener(
		DestinationEventListener destinationEventListener);

	public void removeDestinationEventListener(
		String destinationName,
		DestinationEventListener destinationEventListener);

	public void replace(Destination destination);

	public void sendMessage(String destinationName, Message message);

	public void shutdown();

	public void shutdown(boolean force);

	public boolean unregisterMessageListener(
		String destinationName, MessageListener messageListener);

}