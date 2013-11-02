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

import java.util.concurrent.TimeUnit;

/**
 * @author Shuyang Zhou
 */
public class TestUtil {

	public static final long KEEPALIVE_TIME = 50;

	public static final long KEEPALIVE_WAIT = KEEPALIVE_TIME * 2;

	public static final long LONG_WAIT = 30 * 1000;

	public static final long SHORT_WAIT = 10;

	public static void closePool(ThreadPoolExecutor threadPoolExecutor) {
		closePool(threadPoolExecutor, false);
	}

	public static void closePool(
		ThreadPoolExecutor threadPoolExecutor, boolean force) {

		try {
			if (force) {
				threadPoolExecutor.shutdownNow();
			}
			else {
				threadPoolExecutor.shutdown();
			}

			if (!threadPoolExecutor.awaitTermination(
					LONG_WAIT, TimeUnit.MILLISECONDS)) {

				throw new IllegalStateException();
			}

			if (!threadPoolExecutor.isTerminated()) {
				throw new IllegalStateException();
			}
		}
		catch (InterruptedException ie) {
			throw new RuntimeException();
		}
	}

	public static void unblock(MarkerBlockingJob... markerBlockingJobs) {
		for (MarkerBlockingJob markerBlockingJob : markerBlockingJobs) {
			markerBlockingJob.unBlock();
		}
	}

	public static void waitUntilBlock(MarkerBlockingJob... markerBlockingJobs)
		throws InterruptedException {

		for (MarkerBlockingJob markerBlockingJob : markerBlockingJobs) {
			markerBlockingJob.waitUntilBlock();
		}
	}

	public static void waitUntilEnded(MarkerBlockingJob... markerBlockingJobs)
		throws InterruptedException {

		for (MarkerBlockingJob markerBlockingJob : markerBlockingJobs) {
			markerBlockingJob.waitUntilEnded();
		}

		Thread.sleep(SHORT_WAIT);
	}

}