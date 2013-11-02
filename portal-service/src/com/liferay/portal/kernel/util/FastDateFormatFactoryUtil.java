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

import java.text.Format;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class FastDateFormatFactoryUtil {

	public static Format getDate(int style, Locale locale, TimeZone timeZone) {
		return getFastDateFormatFactory().getDate(style, locale, timeZone);
	}

	public static Format getDate(Locale locale) {
		return getFastDateFormatFactory().getDate(locale);
	}

	public static Format getDate(Locale locale, TimeZone timeZone) {
		return getFastDateFormatFactory().getDate(locale, timeZone);
	}

	public static Format getDate(TimeZone timeZone) {
		return getFastDateFormatFactory().getDate(timeZone);
	}

	public static Format getDateTime(
		int dateStyle, int timeStyle, Locale locale, TimeZone timeZone) {

		return getFastDateFormatFactory().getDateTime(
			dateStyle, timeStyle, locale, timeZone);
	}

	public static Format getDateTime(Locale locale) {
		return getFastDateFormatFactory().getDateTime(locale);
	}

	public static Format getDateTime(Locale locale, TimeZone timeZone) {
		return getFastDateFormatFactory().getDateTime(locale, timeZone);
	}

	public static Format getDateTime(TimeZone timeZone) {
		return getFastDateFormatFactory().getDateTime(timeZone);
	}

	public static FastDateFormatFactory getFastDateFormatFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			FastDateFormatFactoryUtil.class);

		return _fastDateFormatFactory;
	}

	public static Format getSimpleDateFormat(String pattern) {
		return getFastDateFormatFactory().getSimpleDateFormat(pattern);
	}

	public static Format getSimpleDateFormat(String pattern, Locale locale) {
		return getFastDateFormatFactory().getSimpleDateFormat(pattern, locale);
	}

	public static Format getSimpleDateFormat(
		String pattern, Locale locale, TimeZone timeZone) {

		return getFastDateFormatFactory().getSimpleDateFormat(
			pattern, locale, timeZone);
	}

	public static Format getSimpleDateFormat(
		String pattern, TimeZone timeZone) {

		return getFastDateFormatFactory().getSimpleDateFormat(
			pattern, timeZone);
	}

	public static Format getTime(int style, Locale locale, TimeZone timeZone) {
		return getFastDateFormatFactory().getTime(style, locale, timeZone);
	}

	public static Format getTime(Locale locale) {
		return getFastDateFormatFactory().getTime(locale);
	}

	public static Format getTime(Locale locale, TimeZone timeZone) {
		return getFastDateFormatFactory().getTime(locale, timeZone);
	}

	public static Format getTime(TimeZone timeZone) {
		return getFastDateFormatFactory().getTime(timeZone);
	}

	public void setFastDateFormatFactory(
		FastDateFormatFactory fastDateFormatFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_fastDateFormatFactory = fastDateFormatFactory;
	}

	private static FastDateFormatFactory _fastDateFormatFactory;

}