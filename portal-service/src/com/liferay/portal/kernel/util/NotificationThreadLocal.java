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

package com.liferay.portal.kernel.util;

/**
 * @author Jorge Ferrer
 */
public class NotificationThreadLocal {

	public static boolean isEnabled() {
		return _enabled.get();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #isEnabled}
	 */
	public static boolean isNotificationEnabled() {
		return isEnabled();
	}

	public static void setEnabled(boolean enabled) {
		_enabled.set(enabled);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setEnabled(boolean)}
	 */
	public static void setNotificationEnabled(boolean notificationEnabled) {
		setEnabled(notificationEnabled);
	}

	private static ThreadLocal<Boolean> _enabled =
		new AutoResetThreadLocal<Boolean>(
			NotificationThreadLocal.class + "._enabled", true);

}