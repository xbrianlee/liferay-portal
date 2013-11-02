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

package com.liferay.counter.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shuyang Zhou
 */
public class CounterHolder {

	public CounterHolder(long initValue, long rangeMax) {
		_counter = new AtomicLong(initValue);
		_rangeMax = rangeMax;
	}

	public long addAndGet(long delta) {
		return _counter.addAndGet(delta);
	}

	public long getCurrentValue() {
		return _counter.get();
	}

	public long getRangeMax() {
		return _rangeMax;
	}

	private final AtomicLong _counter;
	private final long _rangeMax;

}