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

import java.nio.channels.Channel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * @author Shuyang Zhou
 */
public class MockIntraband extends BaseIntraband {

	public MockIntraband() {
		this(10000);
	}

	public MockIntraband(long defaultTimeout) {
		super(defaultTimeout);
	}

	public Datagram getDatagram() {
		return _datagram;
	}

	public RegistrationReference getRegistrationReference() {
		return _registrationReference;
	}

	@Override
	public RegistrationReference registerChannel(Channel duplexChannel) {
		return new MockRegistrationReference(
			(ScatteringByteChannel)duplexChannel,
			(GatheringByteChannel)duplexChannel);
	}

	@Override
	public RegistrationReference registerChannel(
		ScatteringByteChannel scatteringByteChannel,
		GatheringByteChannel gatheringByteChannel) {

		return new MockRegistrationReference(
			scatteringByteChannel, gatheringByteChannel);
	}

	@Override
	protected void doSendDatagram(
		RegistrationReference registrationReference, Datagram datagram) {

		_registrationReference = registrationReference;
		_datagram = datagram;
	}

	private Datagram _datagram;
	private RegistrationReference _registrationReference;

}