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

package com.liferay.portal.notifications;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.notifications.UserNotificationFeedEntry;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Jonathan Lee
 */
public class UserNotificationHandlerImpl implements UserNotificationHandler {

	public UserNotificationHandlerImpl(
		UserNotificationHandler userNotificationHandler) {

		_userNotificationHandler = userNotificationHandler;
	}

	@Override
	public String getPortletId() {
		return _userNotificationHandler.getPortletId();
	}

	@Override
	public String getSelector() {
		return _userNotificationHandler.getSelector();
	}

	@Override
	public UserNotificationFeedEntry interpret(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws PortalException {

		return _userNotificationHandler.interpret(
			userNotificationEvent, serviceContext);
	}

	@Override
	public boolean isDeliver(
			long userId, long classNameId, int notificationType,
			int deliveryType, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return _userNotificationHandler.isDeliver(
			userId, classNameId, notificationType, deliveryType,
			serviceContext);
	}

	private UserNotificationHandler _userNotificationHandler;

}