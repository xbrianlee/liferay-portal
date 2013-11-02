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

package com.liferay.portlet.calendar.model;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Time;

/**
 * @author Brian Wing Shun Chan
 */
public class CalEventConstants {

	public static final String BIRTHDAY = "birthday";

	public static final int END_DATE_TYPE_END_AFTER = 1;

	public static final int END_DATE_TYPE_END_BY = 2;

	public static final int END_DATE_TYPE_NONE = 0;

	public static final int REMIND_BY_AIM = 3;

	public static final int REMIND_BY_EMAIL = 1;

	public static final int REMIND_BY_ICQ = 4;

	public static final int REMIND_BY_MSN = 5;

	public static final int REMIND_BY_NONE = 0;

	public static final int REMIND_BY_SMS = 2;

	public static final int REMIND_BY_YM = 6;

	public static final long[] REMINDERS = {
		Time.MINUTE * 5, Time.MINUTE * 15, Time.MINUTE * 30, Time.HOUR,
		Time.HOUR * 2, Time.HOUR * 3, Time.HOUR * 6, Time.HOUR * 12, Time.DAY,
		Time.DAY * 2, Time.DAY * 3, Time.DAY * 4, Time.DAY * 5, Time.DAY * 6,
		Time.DAY * 7, Time.DAY * 8, Time.DAY * 9, Time.DAY * 10, Time.DAY * 11,
		Time.DAY * 12, Time.DAY * 13, Time.DAY * 14
	};

	public static final String[] TYPES = PropsUtil.getArray(
		PropsKeys.CALENDAR_EVENT_TYPES);

}