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

import java.text.DateFormat;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of 6.2.0, replaced by {@link DateFormatFactoryUtil} or {@link
 *             FastDateFormatFactoryUtil}
 */
public class DateFormats {

	public static DateFormat getDate(Locale locale) {
		return DateFormatFactoryUtil.getDate(locale);
	}

	public static DateFormat getDate(Locale locale, TimeZone timeZone) {
		return DateFormatFactoryUtil.getDate(locale, timeZone);
	}

	public static DateFormat getDateTime(Locale locale) {
		return DateFormatFactoryUtil.getDateTime(locale);
	}

	public static DateFormat getDateTime(Locale locale, TimeZone timeZone) {
		return DateFormatFactoryUtil.getDateTime(locale, timeZone);
	}

	public static DateFormat getTime(Locale locale) {
		return DateFormatFactoryUtil.getTime(locale);
	}

	public static DateFormat getTime(Locale locale, TimeZone timeZone) {
		return DateFormatFactoryUtil.getTime(locale, timeZone);
	}

}