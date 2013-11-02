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

import java.util.Queue;

/**
 * @author Shuyang Zhou
 */
public class ChannelContext {

	public ChannelContext(Queue<Datagram> sendingQueue) {
		_sendingQueue = sendingQueue;
	}

	public Datagram getReadingDatagram() {
		return _readingDatagram;
	}

	public RegistrationReference getRegistrationReference() {
		return _registrationReference;
	}

	public Queue<Datagram> getSendingQueue() {
		return _sendingQueue;
	}

	public Datagram getWritingDatagram() {
		return _writingDatagram;
	}

	public void setReadingDatagram(Datagram readingDatagram) {
		_readingDatagram = readingDatagram;
	}

	public void setRegistrationReference(
		RegistrationReference registrationReference) {

		_registrationReference = registrationReference;
	}

	public void setWritingDatagram(Datagram writingDatagram) {
		_writingDatagram = writingDatagram;
	}

	// All nonfinal fields are not thread safe. They depend on external logic to
	// do thread safe publication and must be accessed solely by polling threads
	// to remain thread safety.

	private Datagram _readingDatagram;
	private RegistrationReference _registrationReference;
	private final Queue<Datagram> _sendingQueue;
	private Datagram _writingDatagram;

}