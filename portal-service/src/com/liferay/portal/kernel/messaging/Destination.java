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

import java.util.Set;

/**
 * @author Michael C. Han
 */
public interface Destination {

	public void addDestinationEventListener(
		DestinationEventListener destinationEventListener);

	public void close();

	public void close(boolean force);

	public void copyDestinationEventListeners(Destination destination);

	public void copyMessageListeners(Destination destination);

	public DestinationStatistics getDestinationStatistics();

	public int getMessageListenerCount();

	public Set<MessageListener> getMessageListeners();

	public String getName();

	public boolean isRegistered();

	public void open();

	public boolean register(MessageListener messageListener);

	public boolean register(
		MessageListener messageListener, ClassLoader classloader);

	public void removeDestinationEventListener(
		DestinationEventListener destinationEventListener);

	public void removeDestinationEventListeners();

	public void send(Message message);

	public boolean unregister(MessageListener messageListener);

	public void unregisterMessageListeners();

}