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

package com.liferay.portal.backgroundtask;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskStatusRegistryImpl
	implements BackgroundTaskStatusRegistry {

	@Override
	public BackgroundTaskStatus getBackgroundTaskStatus(long backgroundTaskId) {
		Lock lock = _readWriteLock.readLock();

		lock.lock();

		try {
			return _backgroundTaskStatuses.get(backgroundTaskId);
		}
		finally {
			lock.unlock();
		}
	}

	@Override
	public BackgroundTaskStatus registerBackgroundTaskStatus(
		long backgroundTaskId) {

		Lock lock = _readWriteLock.writeLock();

		lock.lock();

		try {
			BackgroundTaskStatus backgroundTaskStatus =
				_backgroundTaskStatuses.get(backgroundTaskId);

			if (backgroundTaskStatus == null) {
				backgroundTaskStatus = new BackgroundTaskStatus();

				_backgroundTaskStatuses.put(
					backgroundTaskId, backgroundTaskStatus);
			}

			return backgroundTaskStatus;
		}
		finally {
			lock.unlock();
		}
	}

	@Override
	public BackgroundTaskStatus unregisterBackgroundTaskStatus(
		long backgroundTaskId) {

		Lock lock = _readWriteLock.writeLock();

		lock.lock();

		try {
			BackgroundTaskStatus backgroundTaskStatus =
				_backgroundTaskStatuses.remove(backgroundTaskId);

			return backgroundTaskStatus;
		}
		finally {
			lock.unlock();
		}
	}

	private Map<Long, BackgroundTaskStatus> _backgroundTaskStatuses =
		new HashMap<Long, BackgroundTaskStatus>();
	private ReadWriteLock _readWriteLock = new ReentrantReadWriteLock();

}