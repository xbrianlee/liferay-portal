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

/**
 * @author Shuyang Zhou
 */
public class MarkerThreadPoolHandler implements ThreadPoolHandler {

	@Override
	public void afterExecute(Runnable runnable, Throwable throwable) {
		_afterExecute = true;
	}

	@Override
	public void beforeExecute(Thread thread, Runnable runnable) {
		_beforeExecute = true;
	}

	@Override
	public void beforeThreadEnd(Thread thread) {
		_beforeThreadEnd = true;
	}

	@Override
	public void beforeThreadStart(Thread thread) {
		_beforeThreadStart = true;
	}

	public boolean isAfterExecuteRan() {
		return _afterExecute;
	}

	public boolean isBeforeExecuteRan() {
		return _beforeExecute;
	}

	public boolean isBeforeThreadEndRan() {
		return _beforeThreadEnd;
	}

	public boolean isBeforeThreadStartRan() {
		return _beforeThreadStart;
	}

	public boolean isTerminatedRan() {
		return _terminated;
	}

	@Override
	public void terminated() {
		_terminated = true;
	}

	private volatile boolean _afterExecute;
	private volatile boolean _beforeExecute;
	private volatile boolean _beforeThreadEnd;
	private volatile boolean _beforeThreadStart;
	private volatile boolean _terminated;

}