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

package com.liferay.portal.service.impl;

import com.liferay.portal.NoSuchUserNotificationDeliveryException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationDelivery;
import com.liferay.portal.service.base.UserNotificationDeliveryLocalServiceBaseImpl;

/**
 * @author Jonathan Lee
 */
public class UserNotificationDeliveryLocalServiceImpl
	extends UserNotificationDeliveryLocalServiceBaseImpl {

	@Override
	public UserNotificationDelivery addUserNotificationDelivery(
			long userId, String portletId, long classNameId,
			int notificationType, int deliveryType, boolean deliver)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		long userNotificationDeliveryId = counterLocalService.increment();

		UserNotificationDelivery userNotificationDelivery =
			userNotificationDeliveryPersistence.create(
				userNotificationDeliveryId);

		userNotificationDelivery.setCompanyId(user.getCompanyId());
		userNotificationDelivery.setUserId(user.getUserId());
		userNotificationDelivery.setPortletId(portletId);
		userNotificationDelivery.setClassNameId(classNameId);
		userNotificationDelivery.setNotificationType(notificationType);
		userNotificationDelivery.setDeliveryType(deliveryType);
		userNotificationDelivery.setDeliver(deliver);

		return userNotificationDeliveryPersistence.update(
			userNotificationDelivery);
	}

	@Override
	public void deleteUserNotificationDeliveries(long userId)
		throws SystemException {

		userNotificationDeliveryPersistence.removeByUserId(userId);
	}

	@Override
	public void deleteUserNotificationDelivery(
			long userId, String portletId, long classNameId,
			int notificationType, int deliveryType)
		throws SystemException {

		try {
			userNotificationDeliveryPersistence.removeByU_P_C_N_D(
				userId, portletId, classNameId, notificationType, deliveryType);
		}
		catch (NoSuchUserNotificationDeliveryException nsnde) {
		}
	}

	@Override
	public UserNotificationDelivery fetchUserNotificationDelivery(
			long userId, String portletId, long classNameId,
			int notificationType, int deliveryType)
		throws SystemException {

		return userNotificationDeliveryPersistence.fetchByU_P_C_N_D(
			userId, portletId, classNameId, notificationType, deliveryType);
	}

	@Override
	public UserNotificationDelivery getUserNotificationDelivery(
			long userId, String portletId, long classNameId,
			int notificationType, int deliveryType, boolean deliver)
		throws PortalException, SystemException {

		UserNotificationDelivery userNotificationDelivery =
			userNotificationDeliveryPersistence.fetchByU_P_C_N_D(
				userId, portletId, classNameId, notificationType, deliveryType);

		if (userNotificationDelivery != null) {
			return userNotificationDelivery;
		}

		return userNotificationDeliveryLocalService.addUserNotificationDelivery(
			userId, portletId, classNameId, notificationType, deliveryType,
			deliver);
	}

	@Override
	public UserNotificationDelivery updateUserNotificationDelivery(
			long userNotificationDeliveryId, boolean deliver)
		throws SystemException {

		UserNotificationDelivery userNotificationDelivery =
			fetchUserNotificationDelivery(userNotificationDeliveryId);

		userNotificationDelivery.setDeliver(deliver);

		return userNotificationDeliveryPersistence.update(
			userNotificationDelivery);
	}

}