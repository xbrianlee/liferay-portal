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

import java.util.concurrent.Future;

/**
 * Handles rejected tasks by canceling them immediately.
 *
 * <p>
 * Use this policy for efficiently discarding rejected tasks. Unlike {@link
 * CallerRunsPolicy}, this policy maintains the order of tasks in the task
 * queue. Unlike {@link DiscardOldestPolicy} and {@link DiscardPolicy}, which
 * ultimately call {@link Future#get()}, threads do not block waiting for a
 * timeout.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class DiscardWithCancelPolicy implements RejectedExecutionHandler {

	/**
	 * Rejects execution of the {@link Runnable} task by canceling it
	 * immediately.
	 *
	 * <p>
	 * Important: The task can only be canceled if it is a subtype of {@link
	 * Future}.
	 * </p>
	 *
	 * @param runnable the task
	 * @param threadPoolExecutor the executor
	 */
	@Override
	public void rejectedExecution(
		Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

		if (runnable instanceof Future<?>) {
			Future<?> future = (Future<?>)runnable;

			// There is no point to try and interrupt the runner thread since
			// being rejected means it is not yet running

			future.cancel(false);
		}
	}

}