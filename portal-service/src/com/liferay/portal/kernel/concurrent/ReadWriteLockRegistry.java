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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * Registry for {@link ReadWriteLock} objects with {@link ReadWriteLockKey} as
 * keys. The behavior of acquiring and releasing locks is provided by a {@link
 * ConcurrentHashMap}. This class is completely thread safe and ensures that
 * only one {@link ReadWriteLock} exists per key.
 * </p>
 *
 * @author Shuyang Zhou
 * @see    ReadWriteLock
 * @see    ReadWriteLockKey
 */
public class ReadWriteLockRegistry {

	public Lock acquireLock(ReadWriteLockKey<?> readWriteLockKey) {
		ReadWriteLock readWriteLock = _readWriteLockMap.get(readWriteLockKey);

		if (readWriteLock == null) {
			ReadWriteLock newReadWriteLock = new ReentrantReadWriteLock();

			readWriteLock = _readWriteLockMap.putIfAbsent(
				readWriteLockKey, newReadWriteLock);

			if (readWriteLock == null) {
				readWriteLock = newReadWriteLock;
			}
		}

		if (readWriteLockKey.isWriteLock()) {
			return readWriteLock.writeLock();
		}
		else {
			return readWriteLock.readLock();
		}
	}

	public void releaseLock(ReadWriteLockKey<?> readWriteLockKey) {
		if (readWriteLockKey.isWriteLock()) {
			_readWriteLockMap.remove(readWriteLockKey);
		}
	}

	private ConcurrentMap<ReadWriteLockKey<?>, ReadWriteLock>
		_readWriteLockMap = new ConcurrentHashMap
			<ReadWriteLockKey<?>, ReadWriteLock>();

}