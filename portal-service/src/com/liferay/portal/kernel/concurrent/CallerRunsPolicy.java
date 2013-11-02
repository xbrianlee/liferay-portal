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
 * Implements the same behavior as {@link
 * java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy}.
 *
 * @author Shuyang Zhou
 * @see    java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy
 */
public class CallerRunsPolicy implements RejectedExecutionHandler {

	/**
	 * @see java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy#rejectedExecution(
	 *      Runnable, java.util.concurrent.ThreadPoolExecutor)
	 */
	@Override
	public void rejectedExecution(
		Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

		if (threadPoolExecutor.isShutdown()) {
			return;
		}

		ThreadPoolHandler threadPoolHandler =
			threadPoolExecutor.getThreadPoolHandler();

		Throwable throwable = null;

		threadPoolHandler.beforeExecute(Thread.currentThread(), runnable);

		try {
			runnable.run();
		}
		catch (RuntimeException re) {
			throwable = re;

			throw re;
		}
		finally {
			threadPoolHandler.afterExecute(runnable, throwable);
		}
	}

}