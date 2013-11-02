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

import java.lang.Thread.UncaughtExceptionHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shuyang Zhou
 */
public class RecordUncaughtExceptionHandler
	implements UncaughtExceptionHandler {

	public Map<Thread, Throwable> getUncaughtMap() {
		return _uncaughtMap;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		_uncaughtMap.put(thread, throwable);
	}

	private final Map<Thread, Throwable> _uncaughtMap =
		new ConcurrentHashMap<Thread, Throwable>();

}