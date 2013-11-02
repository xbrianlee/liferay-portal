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
 * @author Brian Wing Shun Chan
 */
public class TimeZoneUtil_IW {
	public static TimeZoneUtil_IW getInstance() {
		return _instance;
	}

	public java.util.TimeZone getDefault() {
		return TimeZoneUtil.getDefault();
	}

	public com.liferay.portal.kernel.util.TimeZoneUtil getWrappedInstance() {
		return TimeZoneUtil.getInstance();
	}

	public java.util.TimeZone getTimeZone(java.lang.String timeZoneId) {
		return TimeZoneUtil.getTimeZone(timeZoneId);
	}

	public void setDefault(java.lang.String timeZoneId) {
		TimeZoneUtil.setDefault(timeZoneId);
	}

	private TimeZoneUtil_IW() {
	}

	private static TimeZoneUtil_IW _instance = new TimeZoneUtil_IW();
}