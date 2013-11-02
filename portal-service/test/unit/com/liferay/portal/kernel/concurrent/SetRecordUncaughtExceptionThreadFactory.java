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

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Shuyang Zhou
 */
public class SetRecordUncaughtExceptionThreadFactory implements ThreadFactory {

	public RecordUncaughtExceptionHandler getRecordUncaughtExceptionHandler() {
		return recordUncaughtExceptionHandler;
	}

	@Override
	public Thread newThread(Runnable runnable) {
		Thread thread = _threadFactory.newThread(runnable);

		thread.setUncaughtExceptionHandler(recordUncaughtExceptionHandler);

		return thread;
	}

	private final ThreadFactory _threadFactory =
		Executors.defaultThreadFactory();
	private final RecordUncaughtExceptionHandler
		recordUncaughtExceptionHandler = new RecordUncaughtExceptionHandler();

}