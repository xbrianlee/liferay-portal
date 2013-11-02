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

package com.liferay.portal.kernel.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author Shuyang Zhou
 */
public class MarkerBlockingJob implements Runnable {

	public MarkerBlockingJob() {
		this(false);
	}

	public MarkerBlockingJob(boolean blocking) {
		this(blocking, false);
	}

	public MarkerBlockingJob(boolean blocking, boolean throwException) {
		_blocking = blocking;
		_throwException = throwException;
	}

	public Thread getRunThread() {
		return _runThread;
	}

	public boolean isEnded() {
		return _ended;
	}

	public boolean isInterrupted() {
		return _interrupted;
	}

	public boolean isStarted() {
		return _started;
	}

	@Override
	public void run() {
		_runThread = Thread.currentThread();

		if (_started) {
			throw new IllegalStateException("Job already started");
		}

		_started = true;

		if (_blocking) {
			_waitBlockingLatch.countDown();

			try {
				_blockingLatch.await();
			}
			catch (InterruptedException ie) {
				_interrupted = true;
			}
		}

		if (_throwException) {
			throw new RuntimeException();
		}

		_ended = true;

		_endedLatch.countDown();
	}

	public void unBlock() {
		_blockingLatch.countDown();
	}

	public void waitUntilBlock() throws InterruptedException {
		if (!_blocking) {
			throw new IllegalStateException("Blocking is not enabled");
		}

		_waitBlockingLatch.await();
	}

	public void waitUntilEnded() throws InterruptedException {
		_endedLatch.await();
	}

	private final boolean _blocking;
	private final CountDownLatch _blockingLatch = new CountDownLatch(1);
	private volatile boolean _ended;
	private final CountDownLatch _endedLatch = new CountDownLatch(1);
	private volatile boolean _interrupted;
	private volatile Thread _runThread;
	private volatile boolean _started;
	private final boolean _throwException;
	private final CountDownLatch _waitBlockingLatch = new CountDownLatch(1);

}