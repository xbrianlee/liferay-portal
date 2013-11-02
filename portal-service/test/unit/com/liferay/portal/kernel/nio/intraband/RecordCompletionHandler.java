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

import java.io.IOException;

import java.nio.channels.Selector;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Shuyang Zhou
 */
public class RecordCompletionHandler<A> implements CompletionHandler<A> {

	@Override
	public void delivered(A attachment) {
		_attachment = attachment;

		_deliveredCountDownLatch.countDown();
	}

	@Override
	public void failed(A attachment, IOException ioe) {
		_attachment = attachment;
		_ioe = ioe;

		_failedCountDownLatch.countDown();
	}

	public A getAttachment() {
		return _attachment;
	}

	public IOException getIOException() {
		return _ioe;
	}

	@Override
	public void replied(A attachment, Datagram datagram) {
		_attachment = attachment;

		_repliedCountDownLatch.countDown();
	}

	@Override
	public void submitted(A attachment) {
		_attachment = attachment;

		_submittedCountDownLatch.countDown();
	}

	@Override
	public void timedOut(A attachment) {
		_attachment = attachment;

		_timeoutedCountDownLatch.countDown();
	}

	public void waitUntilDelivered() throws InterruptedException {
		_deliveredCountDownLatch.await();
	}

	public void waitUntilFailed() throws InterruptedException {
		_failedCountDownLatch.await();
	}

	public void waitUntilReplied() throws InterruptedException {
		_repliedCountDownLatch.await();
	}

	public void waitUntilSubmitted() throws InterruptedException {
		_submittedCountDownLatch.await();
	}

	public void waitUntilTimeouted() throws InterruptedException {
		_timeoutedCountDownLatch.await();
	}

	public void waitUntilTimeouted(Selector selector)
		throws InterruptedException {

		while (!_timeoutedCountDownLatch.await(10, TimeUnit.MILLISECONDS)) {
			selector.wakeup();
		}
	}

	private volatile A _attachment;
	private final CountDownLatch _deliveredCountDownLatch = new CountDownLatch(
		1);
	private final CountDownLatch _failedCountDownLatch = new CountDownLatch(1);
	private volatile IOException _ioe;
	private final CountDownLatch _repliedCountDownLatch = new CountDownLatch(1);
	private final CountDownLatch _submittedCountDownLatch = new CountDownLatch(
		1);
	private final CountDownLatch _timeoutedCountDownLatch = new CountDownLatch(
		1);

}