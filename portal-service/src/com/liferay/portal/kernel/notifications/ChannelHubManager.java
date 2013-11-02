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
public interface ChannelHubManager {

	public void confirmDelivery(
			long companyId, long userId,
			Collection<String> notificationEventUuids)
		throws ChannelException;

	public void confirmDelivery(
			long companyId, long userId,
			Collection<String> notificationEventUuids, boolean archive)
		throws ChannelException;

	public void confirmDelivery(
			long companyId, long userId, String notificationEventUuid)
		throws ChannelException;

	public void confirmDelivery(
			long companyId, long userId, String notificationEventUuid,
			boolean archive)
		throws ChannelException;

	public Channel createChannel(long companyId, long userId)
		throws ChannelException;

	public ChannelHub createChannelHub(long companyId) throws ChannelException;

	public void deleteUserNotificiationEvent(
			long companyId, long userId, String notificationEventUuid)
		throws ChannelException;

	public void deleteUserNotificiationEvents(
			long companyId, long userId,
			Collection<String> notificationEventUuids)
		throws ChannelException;

	public void destroyChannel(long companyId, long userId)
		throws ChannelException;

	public void destroyChannelHub(long companyId) throws ChannelException;

	public ChannelHub fetchChannelHub(long companyId) throws ChannelException;

	public ChannelHub fetchChannelHub(long companyId, boolean createIfAbsent)
		throws ChannelException;

	public List<NotificationEvent> fetchNotificationEvents(
			long companyId, long userId, boolean flush)
		throws ChannelException;

	public void flush() throws ChannelException;

	public void flush(long companyId) throws ChannelException;

	public void flush(long companyId, long userId, long timestamp)
		throws ChannelException;

	public Channel getChannel(long companyId, long userId)
		throws ChannelException;

	public Channel getChannel(
			long companyId, long userId, boolean createIfAbsent)
		throws ChannelException;

	public ChannelHub getChannelHub(long companyId) throws ChannelException;

	public ChannelHub getChannelHub(long companyId, boolean createIfAbsent)
		throws ChannelException;

	public List<NotificationEvent> getNotificationEvents(
			long companyId, long userId)
		throws ChannelException;

	public List<NotificationEvent> getNotificationEvents(
			long companyId, long userId, boolean flush)
		throws ChannelException;

	public Collection<Long> getUserIds(long companyId) throws ChannelException;

	public void registerChannelListener(
			long companyId, long userId, ChannelListener channelListener)
		throws ChannelException;

	public void removeTransientNotificationEvents(
			long companyId, long userId,
			Collection<NotificationEvent> notificationEvents)
		throws ChannelException;

	public void removeTransientNotificationEventsByUuid(
			long companyId, long userId,
			Collection<String> notificationEventUuids)
		throws ChannelException;

	public void sendNotificationEvent(
			long companyId, long userId, NotificationEvent notificationEvent)
		throws ChannelException;

	public void sendNotificationEvents(
			long companyId, long userId,
			Collection<NotificationEvent> notificationEvents)
		throws ChannelException;

	public void unregisterChannelListener(
			long companyId, long userId, ChannelListener channelListener)
		throws ChannelException;

}