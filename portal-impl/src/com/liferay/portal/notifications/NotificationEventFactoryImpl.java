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

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactory;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class NotificationEventFactoryImpl implements NotificationEventFactory {

	@Override
	public NotificationEvent createNotificationEvent(
		long timestamp, String type, JSONObject payloadJSONObject) {

		return new NotificationEvent(timestamp, type, payloadJSONObject);
	}

}