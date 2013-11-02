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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class CalendarFactoryUtil {

	public static Calendar getCalendar() {
		return getCalendarFactory().getCalendar();
	}

	public static Calendar getCalendar(int year, int month, int date) {
		return getCalendarFactory().getCalendar(year, month, date);
	}

	public static Calendar getCalendar(
		int year, int month, int date, int hour, int minute) {

		return getCalendarFactory().getCalendar(
			year, month, date, hour, minute);
	}

	public static Calendar getCalendar(
		int year, int month, int date, int hour, int minute, int second) {

		return getCalendarFactory().getCalendar(
			year, month, date, hour, minute, second);
	}

	public static Calendar getCalendar(
		int year, int month, int date, int hour, int minute, int second,
		int millisecond) {

		return getCalendarFactory().getCalendar(
			year, month, date, hour, minute, second, millisecond);
	}

	public static Calendar getCalendar(
		int year, int month, int date, int hour, int minute, int second,
		int millisecond, TimeZone timeZone) {

		return getCalendarFactory().getCalendar(
			year, month, date, hour, minute, second, millisecond, timeZone);
	}

	public static Calendar getCalendar(Locale locale) {
		return getCalendarFactory().getCalendar(locale);
	}

	public static Calendar getCalendar(TimeZone timeZone) {
		return getCalendarFactory().getCalendar(timeZone);
	}

	public static Calendar getCalendar(TimeZone timeZone, Locale locale) {
		return getCalendarFactory().getCalendar(timeZone, locale);
	}

	public static CalendarFactory getCalendarFactory() {
		PortalRuntimePermission.checkGetBeanProperty(CalendarFactoryUtil.class);

		return _calendarFactory;
	}

	public Calendar getCalendar(long time) {
		return getCalendarFactory().getCalendar(time);
	}

	public Calendar getCalendar(long time, TimeZone timeZone) {
		return getCalendarFactory().getCalendar(time, timeZone);
	}

	public void setCalendarFactory(CalendarFactory calendarFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_calendarFactory = calendarFactory;
	}

	private static CalendarFactory _calendarFactory;

}