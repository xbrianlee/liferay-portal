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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;

import java.util.List;
import java.util.Map;

/**
 * @author Jonathan Lee
 */
public interface UserNotificationManager {

	public void addUserNotificationDefinition(
		String portletIf,
		UserNotificationDefinition userNotificationDefinition);

	public void addUserNotificationHandler(
		UserNotificationHandler userNotificationHandler);

	public void deleteUserNotificationDefinitions(String portletId);

	public void deleteUserNotificationHandler(
		UserNotificationHandler userNotificationHandler);

	public UserNotificationDefinition fetchUserNotificationDefinition(
		String portletId, long classNameId, int notificationType);

	public Map<String, List<UserNotificationDefinition>>
		getUserNotificationDefinitions();

	public Map<String, Map<String, UserNotificationHandler>>
		getUserNotificationHandlers();

	public UserNotificationFeedEntry interpret(
			String selector, UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws PortalException;

	public boolean isDeliver(
			long userId, String portletId, long classNameId,
			int notificationType, int deliveryType)
		throws PortalException, SystemException;

	public boolean isDeliver(
			long userId, String selector, String portletId, long classNameId,
			int notificationType, int deliveryType,
			ServiceContext serviceContext)
		throws PortalException, SystemException;

}