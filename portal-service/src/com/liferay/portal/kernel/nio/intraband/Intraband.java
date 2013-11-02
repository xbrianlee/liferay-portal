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

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.portal.kernel.nio.intraband.CompletionHandler.CompletionType;

import java.io.IOException;

import java.nio.channels.Channel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Shuyang Zhou
 */
public interface Intraband {

	public void close() throws InterruptedException, IOException;

	public DatagramReceiveHandler[] getDatagramReceiveHandlers();

	public boolean isOpen();

	public RegistrationReference registerChannel(Channel channel)
		throws IOException;

	public RegistrationReference registerChannel(
			ScatteringByteChannel scatteringByteChannel,
			GatheringByteChannel gatheringByteChannel)
		throws IOException;

	public DatagramReceiveHandler registerDatagramReceiveHandler(
		byte type, DatagramReceiveHandler datagramReceiveHandler);

	public void sendDatagram(
		RegistrationReference registrationReference, Datagram datagram);

	public <A> void sendDatagram(
		RegistrationReference registrationReference, Datagram datagram,
		A attachment, EnumSet<CompletionType> completionTypes,
		CompletionHandler<A> completionHandler);

	public <A> void sendDatagram(
		RegistrationReference registrationReference, Datagram datagram,
		A attachment, EnumSet<CompletionType> completionTypes,
		CompletionHandler<A> completionHandler, long timeout,
		TimeUnit timeUnit);

	public Datagram sendSyncDatagram(
			RegistrationReference registrationReference, Datagram datagram)
		throws InterruptedException, IOException, TimeoutException;

	public Datagram sendSyncDatagram(
			RegistrationReference registrationReference, Datagram datagram,
			long timeout, TimeUnit timeUnit)
		throws InterruptedException, IOException, TimeoutException;

	public DatagramReceiveHandler unregisterDatagramReceiveHandler(byte type);

}