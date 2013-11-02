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

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Edward Han
 * @author Raymond Augé
 */
public class NotificationEventFactoryUtil {

	public static NotificationEvent createNotificationEvent(
		long timestamp, String type, JSONObject payloadJSONObject) {

		return getNotificationEventFactory().createNotificationEvent(
			timestamp, type, payloadJSONObject);
	}

	public static NotificationEventFactory getNotificationEventFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			NotificationEventFactoryUtil.class);

		return _notificationEventFactory;
	}

	public void setNotificationEventFactory(
		NotificationEventFactory notificationEventFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_notificationEventFactory = notificationEventFactory;
	}

	private static NotificationEventFactory _notificationEventFactory;

}