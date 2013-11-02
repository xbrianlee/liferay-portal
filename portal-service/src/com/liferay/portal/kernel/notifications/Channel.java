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

import java.util.Collection;
import java.util.List;

/**
 * @author Edward Han
 */
public interface Channel {

	public void cleanUp() throws ChannelException;

	public Channel clone(long companyId, long userId);

	public void close() throws ChannelException;

	public void confirmDelivery(Collection<String> notificationEventUuids)
		throws ChannelException;

	public void confirmDelivery(
			Collection<String> notificationEventUuids, boolean archive)
		throws ChannelException;

	public void confirmDelivery(String notificationEventUuid)
		throws ChannelException;

	public void confirmDelivery(String notificationEventUuid, boolean archive)
		throws ChannelException;

	public void deleteUserNotificiationEvent(String notificationEventUuid)
		throws ChannelException;

	public void deleteUserNotificiationEvents(
			Collection<String> notificationEventUuids)
		throws ChannelException;

	public void flush() throws ChannelException;

	public void flush(long timestamp) throws ChannelException;

	public List<NotificationEvent> getNotificationEvents()
		throws ChannelException;

	public List<NotificationEvent> getNotificationEvents(boolean flush)
		throws ChannelException;

	public long getUserId();

	public void init() throws ChannelException;

	public void registerChannelListener(ChannelListener channelListener);

	public void removeTransientNotificationEvents(
		Collection<NotificationEvent> notificationEvents);

	public void removeTransientNotificationEventsByUuid(
		Collection<String> notificationEventUuids);

	public void sendNotificationEvent(NotificationEvent notificationEvent)
		throws ChannelException;

	public void sendNotificationEvents(
			Collection<NotificationEvent> notificationEvents)
		throws ChannelException;

	public void unregisterChannelListener(ChannelListener channelListener);

}