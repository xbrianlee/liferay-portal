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

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;

/**
 * @author Shuyang Zhou
 */
public class BufferedIncrementConfiguration {

	public BufferedIncrementConfiguration(String configuration) {
		Filter filter = new Filter(configuration);

		_enabled = GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.BUFFERED_INCREMENT_ENABLED, filter));
		_standbyQueueThreshold = GetterUtil.getInteger(
			PropsUtil.get(
				PropsKeys.BUFFERED_INCREMENT_STANDBY_QUEUE_THRESHOLD, filter));
		_standbyTimeUpperLimit = GetterUtil.getLong(
			PropsUtil.get(
				PropsKeys.BUFFERED_INCREMENT_STANDBY_TIME_UPPER_LIMIT, filter));

		long threadpoolKeepAliveTime = GetterUtil.getLong(
			PropsUtil.get(
				PropsKeys.BUFFERED_INCREMENT_THREADPOOL_KEEP_ALIVE_TIME,
				filter));

		if (threadpoolKeepAliveTime < 0) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					PropsKeys.BUFFERED_INCREMENT_THREADPOOL_KEEP_ALIVE_TIME +
						"[" + configuration + "]=" + threadpoolKeepAliveTime +
							". Auto reset to 0.");
			}

			threadpoolKeepAliveTime = 0;
		}

		_threadpoolKeepAliveTime = threadpoolKeepAliveTime;

		int threadpoolMaxSize = GetterUtil.getInteger(
			PropsUtil.get(
				PropsKeys.BUFFERED_INCREMENT_THREADPOOL_MAX_SIZE, filter));

		if (threadpoolMaxSize < 1) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					PropsKeys.BUFFERED_INCREMENT_THREADPOOL_MAX_SIZE +
						"[" + configuration + "]=" + threadpoolMaxSize +
							". Auto reset to 1.");
			}

			threadpoolMaxSize = 1;
		}

		_threadpoolMaxSize = threadpoolMaxSize;

		if ((_standbyQueueThreshold > 0) && (_standbyTimeUpperLimit > 0)) {
			_standbyEnabled = true;
		}
		else {
			_standbyEnabled = false;
		}
	}

	public long calculateStandbyTime(int queueLength) {
		if (queueLength < 0) {
			throw new IllegalArgumentException(
				"Negative queue length " + queueLength);
		}

		if (!_standbyEnabled) {
			throw new IllegalStateException("Standby is disabled");
		}

		if (queueLength > _standbyQueueThreshold) {
			return 0;
		}

		return (_standbyQueueThreshold - queueLength) * _standbyTimeUpperLimit *
			1000 / _standbyQueueThreshold;
	}

	public int getStandbyQueueThreshold() {
		return _standbyQueueThreshold;
	}

	public long getStandbyTimeUpperLimit() {
		return _standbyTimeUpperLimit;
	}

	public long getThreadpoolKeepAliveTime() {
		return _threadpoolKeepAliveTime;
	}

	public int getThreadpoolMaxSize() {
		return _threadpoolMaxSize;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public boolean isStandbyEnabled() {
		return _standbyEnabled;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BufferedIncrementConfiguration.class);

	private final boolean _enabled;
	private final boolean _standbyEnabled;
	private final int _standbyQueueThreshold;
	private final long _standbyTimeUpperLimit;
	private final long _threadpoolKeepAliveTime;
	private final int _threadpoolMaxSize;

}