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

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public interface CalendarFactory {

	public Calendar getCalendar();

	public Calendar getCalendar(int year, int month, int date);

	public Calendar getCalendar(
		int year, int month, int date, int hour, int minute);

	public Calendar getCalendar(
		int year, int month, int date, int hour, int minute, int second);

	public Calendar getCalendar(
		int year, int month, int date, int hour, int minute, int second,
		int millisecond);

	public Calendar getCalendar(
		int year, int month, int date, int hour, int minute, int second,
		int millisecond, TimeZone timeZone);

	public Calendar getCalendar(Locale locale);

	public Calendar getCalendar(long time);

	public Calendar getCalendar(long time, TimeZone timeZone);

	public Calendar getCalendar(TimeZone timeZone);

	public Calendar getCalendar(TimeZone timeZone, Locale locale);

}