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

package com.liferay.portal.kernel.notifications;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Edward Han
 */
public abstract class BaseChannelImpl implements Channel {

	@Override
	public void cleanUp() throws ChannelException {
		long currentTime = System.currentTimeMillis();

		long nextCleanUpTime = _nextCleanUpTime.get();

		if ((currentTime > nextCleanUpTime) &&
			_nextCleanUpTime.compareAndSet(
				nextCleanUpTime, currentTime + _cleanUpInterval)) {

			try {
				doCleanUp();
			}
			catch (ChannelException ce) {
				throw ce;
			}
			catch (Exception e) {
				throw new ChannelException(e);
			}
		}
	}

	@Override
	public void close() throws ChannelException {
		flush();
	}

	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public List<NotificationEvent> getNotificationEvents()
		throws ChannelException {

		return getNotificationEvents(true);
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	public boolean hasNotificationEvents() {
		try {
			List<NotificationEvent> notificationEvents = getNotificationEvents(
				false);

			if (!notificationEvents.isEmpty()) {
				return true;
			}
		}
		catch (ChannelException ce) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to fetch notifications", ce);
			}
		}

		return false;
	}

	@Override
	public void registerChannelListener(ChannelListener channelListener) {
		_channelListeners.add(channelListener);

		if (hasNotificationEvents()) {
			notifyChannelListeners();
		}
	}

	public void setCleanUpInterval(long cleanUpInterval) {
		_cleanUpInterval = cleanUpInterval;
	}

	@Override
	public void unregisterChannelListener(ChannelListener channelListener) {
		_channelListeners.remove(channelListener);

		channelListener.channelListenerRemoved(_userId);
	}

	protected BaseChannelImpl(long companyId, long usedId) {
		_companyId = companyId;
		_userId = usedId;
	}

	protected abstract void doCleanUp() throws Exception;

	protected void notifyChannelListeners() {
		for (ChannelListener channelListener : _channelListeners) {
			channelListener.notificationEventsAvailable(_userId);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BaseChannelImpl.class);

	private List<ChannelListener> _channelListeners =
		new CopyOnWriteArrayList<ChannelListener>();
	private long _cleanUpInterval;
	private long _companyId;
	private AtomicLong _nextCleanUpTime = new AtomicLong();
	private long _userId;

}