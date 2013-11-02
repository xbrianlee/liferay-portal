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
 * @author Shuyang Zhou
 */
public class DestinationWrapper implements Destination {

	public DestinationWrapper(Destination destination) {
		this.destination = destination;
	}

	@Override
	public void addDestinationEventListener(
		DestinationEventListener destinationEventListener) {

		destination.addDestinationEventListener(destinationEventListener);
	}

	@Override
	public void close() {
		destination.close();
	}

	@Override
	public void close(boolean force) {
		destination.close(force);
	}

	@Override
	public void copyDestinationEventListeners(Destination destination) {
		this.destination.copyDestinationEventListeners(destination);
	}

	@Override
	public void copyMessageListeners(Destination destination) {
		this.destination.copyMessageListeners(destination);
	}

	@Override
	public DestinationStatistics getDestinationStatistics() {
		return destination.getDestinationStatistics();
	}

	@Override
	public int getMessageListenerCount() {
		return destination.getMessageListenerCount();
	}

	@Override
	public Set<MessageListener> getMessageListeners() {
		return destination.getMessageListeners();
	}

	@Override
	public String getName() {
		return destination.getName();
	}

	@Override
	public boolean isRegistered() {
		return destination.isRegistered();
	}

	@Override
	public void open() {
		destination.open();
	}

	@Override
	public boolean register(MessageListener messageListener) {
		return destination.register(messageListener);
	}

	@Override
	public boolean register(
		MessageListener messageListener, ClassLoader classloader) {

		return destination.register(messageListener, classloader);
	}

	@Override
	public void removeDestinationEventListener(
		DestinationEventListener destinationEventListener) {

		destination.removeDestinationEventListener(destinationEventListener);
	}

	@Override
	public void removeDestinationEventListeners() {
		destination.removeDestinationEventListeners();
	}

	@Override
	public void send(Message message) {
		destination.send(message);
	}

	@Override
	public boolean unregister(MessageListener messageListener) {
		return destination.unregister(messageListener);
	}

	@Override
	public void unregisterMessageListeners() {
		destination.unregisterMessageListeners();
	}

	protected Destination destination;

}