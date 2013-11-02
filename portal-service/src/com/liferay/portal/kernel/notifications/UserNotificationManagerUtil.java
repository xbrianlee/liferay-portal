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
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;

import java.util.List;
import java.util.Map;

/**
 * @author Jonathan Lee
 */
public class UserNotificationManagerUtil {

	public static void addUserNotificationDefinition(
		String portletId,
		UserNotificationDefinition userNotificationDefinition) {

		getUserNotificationManager().addUserNotificationDefinition(
			portletId, userNotificationDefinition);
	}

	public static void addUserNotificationHandler(
		UserNotificationHandler userNotificationHandler) {

		getUserNotificationManager().addUserNotificationHandler(
			userNotificationHandler);
	}

	public static void deleteUserNotificationDefinitions(String portletId) {
		getUserNotificationManager().deleteUserNotificationDefinitions(
			portletId);
	}

	public static void deleteUserNotificationHandler(
		UserNotificationHandler userNotificationHandler) {

		getUserNotificationManager().deleteUserNotificationHandler(
			userNotificationHandler);
	}

	public static UserNotificationDefinition fetchUserNotificationDefinition(
		String portletId, long classNameId, int notificationType) {

		return getUserNotificationManager().fetchUserNotificationDefinition(
			portletId, classNameId, notificationType);
	}

	public static Map<String, List<UserNotificationDefinition>>
		getUserNotificationDefinitions() {

		return getUserNotificationManager().getUserNotificationDefinitions();
	}

	public static Map<String, Map<String, UserNotificationHandler>>
		getUserNotificationHandlers() {

		return getUserNotificationManager().getUserNotificationHandlers();
	}

	public static UserNotificationManager getUserNotificationManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			UserNotificationManagerUtil.class);

		return _userNotificationManager;
	}

	public static UserNotificationFeedEntry interpret(
			String selector, UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws PortalException {

		return getUserNotificationManager().interpret(
			selector, userNotificationEvent, serviceContext);
	}

	public static boolean isDeliver(
			long userId, String portletId, long classNameId,
			int notificationType, int deliveryType)
		throws PortalException, SystemException {

		return getUserNotificationManager().isDeliver(
			userId, portletId, classNameId, notificationType, deliveryType);
	}

	public static boolean isDeliver(
			long userId, String selector, String portletId, long classNameId,
			int notificationType, int deliveryType,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getUserNotificationManager().isDeliver(
			userId, selector, portletId, classNameId, notificationType,
			deliveryType, serviceContext);
	}

	public void setUserNotificationManager(
		UserNotificationManager userNotificationManager) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_userNotificationManager = userNotificationManager;
	}

	private static UserNotificationManager _userNotificationManager;

}