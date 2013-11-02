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
public interface ChannelHub {

	public void cleanUp() throws ChannelException;

	public void cleanUp(long userId) throws ChannelException;

	public ChannelHub clone(long companyId);

	public void confirmDelivery(
			long userId, Collection<String> notificationEventUuids)
		throws ChannelException;

	public void confirmDelivery(
			long userId, Collection<String> notificationEventUuids,
			boolean archive)
		throws ChannelException;

	public void confirmDelivery(long userId, String notificationEventUuid)
		throws ChannelException;

	public void confirmDelivery(
			long userId, String notificationEventUuid, boolean archive)
		throws ChannelException;

	public Channel createChannel(long userId) throws ChannelException;

	public void deleteUserNotificiationEvent(
			long userId, String notificationEventUuid)
		throws ChannelException;

	public void deleteUserNotificiationEvents(
			long userId, Collection<String> notificationEventUuids)
		throws ChannelException;

	public void destroy() throws ChannelException;

	public Channel destroyChannel(long userId) throws ChannelException;

	public Channel fetchChannel(long userId) throws ChannelException;

	public Channel fetchChannel(long userId, boolean createIfAbsent)
		throws ChannelException;

	public List<NotificationEvent> fetchNotificationEvents(long userId)
		throws ChannelException;

	public List<NotificationEvent> fetchNotificationEvents(
			long userId, boolean flush)
		throws ChannelException;

	public void flush() throws ChannelException;

	public void flush(long userId) throws ChannelException;

	public void flush(long userId, long timestamp) throws ChannelException;

	public Channel getChannel(long userId) throws ChannelException;

	public Channel getChannel(long userId, boolean createIfAbsent)
		throws ChannelException;

	public List<NotificationEvent> getNotificationEvents(long userId)
		throws ChannelException;

	public List<NotificationEvent> getNotificationEvents(
			long userId, boolean flush)
		throws ChannelException;

	public Collection<Long> getUserIds();

	public void registerChannelListener(
			long userId, ChannelListener channelListener)
		throws ChannelException;

	public void removeTransientNotificationEvents(
			long userId, Collection<NotificationEvent> notificationEvents)
		throws ChannelException;

	public void removeTransientNotificationEventsByUuid(
			long userId, Collection<String> notificationEventUuids)
		throws ChannelException;

	public void sendNotificationEvent(
			long userId, NotificationEvent notificationEvent)
		throws ChannelException;

	public void sendNotificationEvents(
			long userId, Collection<NotificationEvent> notificationEvents)
		throws ChannelException;

	public void unregisterChannelListener(
			long userId, ChannelListener channelListener)
		throws ChannelException;

}