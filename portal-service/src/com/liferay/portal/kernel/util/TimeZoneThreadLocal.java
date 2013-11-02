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

import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class TimeZoneThreadLocal {

	public static TimeZone getDefaultTimeZone() {
		return _defaultTimeZone.get();
	}

	public static TimeZone getThemeDisplayTimeZone() {
		return _themeDisplayTimeZone.get();
	}

	public static void setDefaultTimeZone(TimeZone timeZonee) {
		_defaultTimeZone.set(timeZonee);
	}

	public static void setThemeDisplayTimeZone(TimeZone timeZonee) {
		_themeDisplayTimeZone.set(timeZonee);
	}

	private static ThreadLocal<TimeZone> _defaultTimeZone =
		new AutoResetThreadLocal<TimeZone>(
			TimeZoneThreadLocal.class + "._defaultTimeZone");
	private static ThreadLocal<TimeZone> _themeDisplayTimeZone =
		new AutoResetThreadLocal<TimeZone>(
			TimeZoneThreadLocal.class + "._themeDisplayTimeZone");

}