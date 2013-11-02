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

import com.liferay.portal.kernel.util.Validator;

/**
 * <p>
 * Represents a key that is used by ReadWriteLockRegistry. T must also be
 * immutable and properly implement the equals and hashCode methods.
 * </p>
 *
 * @author Shuyang Zhou
 * @see    ReadWriteLockRegistry
 */
public class ReadWriteLockKey<T> {

	public ReadWriteLockKey(T key, boolean writeLock) {
		_key = key;
		_writeLock = writeLock;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReadWriteLockKey<?>)) {
			return false;
		}

		ReadWriteLockKey<T> readWriteLockKey = (ReadWriteLockKey<T>)obj;

		if (Validator.equals(_key, readWriteLockKey._key)) {
			return true;
		}

		return false;
	}

	public T getKey() {
		return _key;
	}

	@Override
	public int hashCode() {
		return _key.hashCode();
	}

	public boolean isWriteLock() {
		return _writeLock;
	}

	private final T _key;
	private final boolean _writeLock;

}