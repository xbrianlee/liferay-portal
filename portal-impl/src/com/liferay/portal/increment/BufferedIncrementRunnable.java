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

package com.liferay.portal.increment;

import com.liferay.portal.kernel.cache.Lifecycle;
import com.liferay.portal.kernel.cache.ThreadLocalCacheManager;
import com.liferay.portal.kernel.concurrent.BatchablePipe;
import com.liferay.portal.kernel.increment.Increment;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CentralizedThreadLocal;
import com.liferay.portal.security.auth.CompanyThreadLocal;

import java.io.Serializable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Shuyang Zhou
 */
public class BufferedIncrementRunnable implements Runnable {

	public BufferedIncrementRunnable(
		BufferedIncrementConfiguration bufferedIncrementConfiguration,
		BatchablePipe<Serializable, Increment<?>> batchablePipe,
		AtomicInteger queueLengthTracker) {

		_bufferedIncrementConfiguration = bufferedIncrementConfiguration;
		_batchablePipe = batchablePipe;
		_queueLengthTracker = queueLengthTracker;

		if (_bufferedIncrementConfiguration.isStandbyEnabled()) {
			_queueLengthTracker.incrementAndGet();
		}

		_companyId = CompanyThreadLocal.getCompanyId();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void run() {
		CompanyThreadLocal.setCompanyId(_companyId);

		while (true) {
			BufferedIncreasableEntry bufferedIncreasableEntry =
				(BufferedIncreasableEntry)_batchablePipe.take();

			if (bufferedIncreasableEntry == null) {
				break;
			}

			try {
				bufferedIncreasableEntry.proceed();
			}
			catch (Throwable t) {
				_log.error(
					"Unable to write buffered increment value to the database",
					t);
			}

			if (_bufferedIncrementConfiguration.isStandbyEnabled()) {
				int queueLength = _queueLengthTracker.decrementAndGet();

				long standbyTime =
					_bufferedIncrementConfiguration.calculateStandbyTime(
						queueLength);

				try {
					Thread.sleep(standbyTime);
				}
				catch (InterruptedException ie) {
					break;
				}
			}
		}

		ThreadLocalCacheManager.clearAll(Lifecycle.REQUEST);

		CentralizedThreadLocal.clearShortLivedThreadLocals();
	}

	private static Log _log = LogFactoryUtil.getLog(
		BufferedIncrementRunnable.class);

	private final BatchablePipe<Serializable, Increment<?>> _batchablePipe;
	private final BufferedIncrementConfiguration
		_bufferedIncrementConfiguration;
	private final long _companyId;
	private final AtomicInteger _queueLengthTracker;

}