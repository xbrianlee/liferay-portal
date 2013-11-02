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

package com.liferay.portal.kernel.memory;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Shuyang Zhou
 */
public class SoftReferencePool<V, P> {

	public static final int DEFAULT_IDLE_SIZE = 8;

	public SoftReferencePool(PoolAction<V, P> poolAction) {
		this(poolAction, DEFAULT_IDLE_SIZE);
	}

	public SoftReferencePool(PoolAction<V, P> poolAction, int maxIdleSize) {
		this(poolAction, maxIdleSize, true);
	}

	public SoftReferencePool(
		PoolAction<V, P> poolAction, int maxIdleSize, boolean useWeakCounter) {

		_poolAction = poolAction;
		_maxIdleSize = maxIdleSize;
		_useWeakCounter = useWeakCounter;

		if (_useWeakCounter) {
			_weakCounter = new AtomicInteger();
		}
	}

	public V borrowObject(P parameter) {
		while (true) {
			SoftReference<? extends V> softReference = _softReferences.poll();

			if (softReference == null) {
				return _poolAction.onCreate(parameter);
			}
			else if (_useWeakCounter) {
				_weakCounter.getAndDecrement();
			}

			V value = softReference.get();

			if (value != null) {
				return _poolAction.onBorrow(value, parameter);
			}
		}
	}

	public void returnObject(V value) {
		if (_getCount() < _maxIdleSize) {
			SoftReference<V> softReference = new SoftReference<V>(
				value, _referenceQueue);

			_poolAction.onReturn(value);

			_softReferences.offer(softReference);

			if (_useWeakCounter) {
				_weakCounter.getAndIncrement();
			}
		}
		else {
			while (_getCount() > _maxIdleSize) {
				if ((_softReferences.poll() != null) && _useWeakCounter) {
					_weakCounter.getAndDecrement();
				}
			}
		}

		SoftReference<? extends V> softReference = null;

		while (true) {
			softReference = (SoftReference<? extends V>)_referenceQueue.poll();

			if (softReference == null) {
				break;
			}

			if (_softReferences.remove(softReference) && _useWeakCounter) {
				_weakCounter.getAndDecrement();
			}
		}
	}

	private int _getCount() {
		if (_useWeakCounter) {
			return _weakCounter.get();
		}
		else {
			return _softReferences.size();
		}
	}

	private int _maxIdleSize;
	private PoolAction<V, P> _poolAction;
	private ReferenceQueue<V> _referenceQueue = new ReferenceQueue<V>();
	private Queue<SoftReference<? extends V>> _softReferences =
		new ConcurrentLinkedQueue<SoftReference<? extends V>>();
	private boolean _useWeakCounter;
	private AtomicInteger _weakCounter;

}