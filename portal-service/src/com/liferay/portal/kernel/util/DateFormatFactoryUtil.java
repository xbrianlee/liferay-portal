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

import java.text.DateFormat;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class DateFormatFactoryUtil {

	public static DateFormat getDate(Locale locale) {
		return getDateFormatFactory().getDate(locale);
	}

	public static DateFormat getDate(Locale locale, TimeZone timeZone) {
		return getDateFormatFactory().getDate(locale, timeZone);
	}

	public static DateFormat getDate(TimeZone timeZone) {
		return getDateFormatFactory().getDate(timeZone);
	}

	public static DateFormatFactory getDateFormatFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			DateFormatFactoryUtil.class);

		return _fastDateFormatFactory;
	}

	public static DateFormat getDateTime(Locale locale) {
		return getDateFormatFactory().getDateTime(locale);
	}

	public static DateFormat getDateTime(Locale locale, TimeZone timeZone) {
		return getDateFormatFactory().getDateTime(locale, timeZone);
	}

	public static DateFormat getDateTime(TimeZone timeZone) {
		return getDateFormatFactory().getDateTime(timeZone);
	}

	public static DateFormat getSimpleDateFormat(String pattern) {
		return getDateFormatFactory().getSimpleDateFormat(pattern);
	}

	public static DateFormat getSimpleDateFormat(
		String pattern, Locale locale) {

		return getDateFormatFactory().getSimpleDateFormat(pattern, locale);
	}

	public static DateFormat getSimpleDateFormat(
		String pattern, Locale locale, TimeZone timeZone) {

		return getDateFormatFactory().getSimpleDateFormat(
			pattern, locale, timeZone);
	}

	public static DateFormat getSimpleDateFormat(
		String pattern, TimeZone timeZone) {

		return getDateFormatFactory().getSimpleDateFormat(pattern, timeZone);
	}

	public static DateFormat getTime(Locale locale) {
		return getDateFormatFactory().getTime(locale);
	}

	public static DateFormat getTime(Locale locale, TimeZone timeZone) {
		return getDateFormatFactory().getTime(locale, timeZone);
	}

	public static DateFormat getTime(TimeZone timeZone) {
		return getDateFormatFactory().getTime(timeZone);
	}

	public void setDateFormatFactory(DateFormatFactory fastDateFormatFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_fastDateFormatFactory = fastDateFormatFactory;
	}

	private static DateFormatFactory _fastDateFormatFactory;

}