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

import com.liferay.portal.kernel.test.CodeCoverageAssertor;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class CallerRunsPolicyTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testCallerRunsPolicy1() {
		MarkerThreadPoolHandler markerThreadPoolHandler =
			new MarkerThreadPoolHandler();

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			1, 2, TestUtil.KEEPALIVE_TIME, TimeUnit.MILLISECONDS, true, 3,
			new CallerRunsPolicy(), Executors.defaultThreadFactory(),
			markerThreadPoolHandler);

		threadPoolExecutor.shutdown();

		MarkerBlockingJob markerBlockingJob = new MarkerBlockingJob();

		threadPoolExecutor.execute(markerBlockingJob);

		Assert.assertFalse(markerBlockingJob.isStarted());
		Assert.assertFalse(markerThreadPoolHandler.isBeforeExecuteRan());
		Assert.assertFalse(markerThreadPoolHandler.isAfterExecuteRan());
	}

	@Test
	public void testCallerRunsPolicy2() {
		MarkerThreadPoolHandler markerThreadPoolHandler =
			new MarkerThreadPoolHandler();

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			1, 1, TestUtil.KEEPALIVE_TIME, TimeUnit.MILLISECONDS, true, 1,
			new CallerRunsPolicy(), Executors.defaultThreadFactory(),
			markerThreadPoolHandler);

		try {
			threadPoolExecutor.execute(new MarkerBlockingJob(true));
			threadPoolExecutor.execute(new MarkerBlockingJob(true));

			MarkerBlockingJob markerBlockingJob = new MarkerBlockingJob();

			threadPoolExecutor.execute(markerBlockingJob);

			Assert.assertTrue(markerBlockingJob.isEnded());
			Assert.assertTrue(markerThreadPoolHandler.isBeforeExecuteRan());
			Assert.assertTrue(markerThreadPoolHandler.isAfterExecuteRan());
		}
		finally {
			TestUtil.closePool(threadPoolExecutor, true);
		}
	}

	@Test
	public void testCallerRunsPolicy3() {
		MarkerThreadPoolHandler markerThreadPoolHandler =
			new MarkerThreadPoolHandler();

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			1, 1, TestUtil.KEEPALIVE_TIME, TimeUnit.MILLISECONDS, true, 1,
			new CallerRunsPolicy(), Executors.defaultThreadFactory(),
			markerThreadPoolHandler);

		try {
			threadPoolExecutor.execute(new MarkerBlockingJob(true));
			threadPoolExecutor.execute(new MarkerBlockingJob(true));

			MarkerBlockingJob markerBlockingJob = new MarkerBlockingJob(
				false, true);

			try {
				threadPoolExecutor.execute(markerBlockingJob);

				Assert.fail();
			}
			catch (RuntimeException re) {
			}

			Assert.assertTrue(markerBlockingJob.isStarted());
			Assert.assertFalse(markerBlockingJob.isEnded());
			Assert.assertTrue(markerThreadPoolHandler.isBeforeExecuteRan());
			Assert.assertTrue(markerThreadPoolHandler.isAfterExecuteRan());
		}
		finally {
			TestUtil.closePool(threadPoolExecutor, true);
		}
	}

}