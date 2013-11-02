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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.UserNotificationDelivery;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserNotificationDeliveryLocalServiceUtil;

/**
 * @author Jonathan Lee
 */
public abstract class BaseUserNotificationHandler
	implements UserNotificationHandler {

	@Override
	public String getPortletId() {
		return _portletId;
	}

	@Override
	public String getSelector() {
		return _selector;
	}

	@Override
	public UserNotificationFeedEntry interpret(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			UserNotificationFeedEntry userNotificationFeedEntry = doInterpret(
				userNotificationEvent, serviceContext);

			userNotificationFeedEntry.setPortletId(getPortletId());

			return userNotificationFeedEntry;
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Override
	public boolean isDeliver(
			long userId, long classNameId, int notificationType,
			int deliveryType, ServiceContext serviceContext)
		throws PortalException, SystemException {

		UserNotificationDefinition userNotificationDefinition =
			UserNotificationManagerUtil.fetchUserNotificationDefinition(
				_portletId, classNameId, notificationType);

		UserNotificationDeliveryType userNotificationDeliveryType =
			userNotificationDefinition.getUserNotificationDeliveryType(
				deliveryType);

		UserNotificationDelivery userNotificationDelivery =
			UserNotificationDeliveryLocalServiceUtil.
				getUserNotificationDelivery(
					userId, _portletId, classNameId, notificationType,
					deliveryType, userNotificationDeliveryType.isDefault());

		return userNotificationDelivery.isDeliver();
	}

	protected UserNotificationFeedEntry doInterpret(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		String body = getBody(userNotificationEvent, serviceContext);

		if (Validator.isNull(body)) {
			return null;
		}

		String link = getLink(userNotificationEvent, serviceContext);

		return new UserNotificationFeedEntry(body, link);
	}

	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		return StringPool.BLANK;
	}

	protected String getLink(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		return StringPool.BLANK;
	}

	protected void setPortletId(String portletId) {
		_portletId = portletId;
	}

	protected void setSelector(String selector) {
		_selector = selector;
	}

	private String _portletId;
	private String _selector = StringPool.BLANK;

}