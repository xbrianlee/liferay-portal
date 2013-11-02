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

package com.liferay.portal.kernel.cache.cluster;

import com.liferay.portal.kernel.concurrent.CoalescedPipe;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shuyang Zhou
 */
public abstract class BasePortalCacheClusterChannel
	implements PortalCacheClusterChannel, Runnable {

	public BasePortalCacheClusterChannel() {
		_dispatchThread = new Thread(
			this,
			"PortalCacheClusterChannel dispatch thread-" +
				_dispatchThreadCounter.getAndIncrement());
		_eventQueue = new CoalescedPipe<PortalCacheClusterEvent>(
			new PortalCacheClusterEventCoalesceComparator());
	}

	@Override
	public void destroy() {
		_destroy = true;

		_dispatchThread.interrupt();
	}

	public abstract void dispatchEvent(PortalCacheClusterEvent event);

	@Override
	public long getCoalescedEventNumber() {
		return _eventQueue.coalescedCount();
	}

	@Override
	public int getPendingEventNumber() {
		return _eventQueue.pendingCount();
	}

	@Override
	public long getSentEventNumber() {
		return _sentEventCounter.get();
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (_destroy) {
					Object[] events = _eventQueue.takeSnapshot();

					for (Object event : events) {
						dispatchEvent((PortalCacheClusterEvent)event);

						_sentEventCounter.incrementAndGet();
					}

					break;
				}
				else {
					try {
						PortalCacheClusterEvent portalCacheClusterEvent =
							_eventQueue.take();

						dispatchEvent(portalCacheClusterEvent);

						_sentEventCounter.incrementAndGet();
					}
					catch (InterruptedException ie) {
					}
				}
			}
			catch (Throwable t) {
				if (_log.isWarnEnabled()) {
					_log.warn("Please fix the unexpected throwable", t);
				}
			}
		}
	}

	@Override
	public void sendEvent(PortalCacheClusterEvent portalCacheClusterEvent) {
		if (_started == false) {
			synchronized (this) {
				if (_started == false) {
					_dispatchThread.start();

					_started = true;
				}
			}
		}

		if (_destroy) {
			dispatchEvent(portalCacheClusterEvent);
		}
		else {
			try {
				_eventQueue.put(portalCacheClusterEvent);
			}
			catch (InterruptedException ie) {
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		BasePortalCacheClusterChannel.class);

	private static AtomicInteger _dispatchThreadCounter = new AtomicInteger(0);

	private volatile boolean _destroy = false;
	private final Thread _dispatchThread;
	private final CoalescedPipe<PortalCacheClusterEvent> _eventQueue;
	private final AtomicLong _sentEventCounter = new AtomicLong(0);
	private volatile boolean _started = false;

}