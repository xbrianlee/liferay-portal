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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jonathan Lee
 */
public class UserNotificationDefinition {

	public UserNotificationDefinition(
		String portletId, long classNameId, int notificationType,
		String description) {

		_classNameId = classNameId;
		_description = description;
		_notificationType = notificationType;
		_portletId = portletId;
	}

	public void addUserNotificationDeliveryType(
		UserNotificationDeliveryType userNotificationDeliveryType) {

		_userNotificationDeliveryTypes.put(
			userNotificationDeliveryType.getType(),
			userNotificationDeliveryType);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public String getDescription() {
		return _description;
	}

	public int getNotificationType() {
		return _notificationType;
	}

	public String getPortletId() {
		return _portletId;
	}

	public UserNotificationDeliveryType getUserNotificationDeliveryType(
		int deliveryType) {

		return _userNotificationDeliveryTypes.get(deliveryType);
	}

	public Map<Integer, UserNotificationDeliveryType>
		getUserNotificationDeliveryTypes() {

		return _userNotificationDeliveryTypes;
	}

	private long _classNameId;
	private String _description;
	private int _notificationType;
	private String _portletId;
	private Map<Integer, UserNotificationDeliveryType>
		_userNotificationDeliveryTypes =
			new HashMap<Integer, UserNotificationDeliveryType>();

}