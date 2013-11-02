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

package com.liferay.util;

/**
 * @author Brian Wing Shun Chan
 */
public class SimpleCounter {

	public SimpleCounter() {
		this(_DEFAULT_COUNTER);
	}

	public SimpleCounter(long counter) {
		_counter = counter;
	}

	public synchronized long get() {
		return _counter++;
	}

	public String getString() {
		return String.valueOf(get());
	}

	private static final long _DEFAULT_COUNTER = 1;

	private long _counter;

}