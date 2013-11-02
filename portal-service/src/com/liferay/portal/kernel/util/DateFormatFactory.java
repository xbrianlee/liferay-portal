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
 * @author Brian Wing Shun Chan
 */
public interface DateFormatFactory {

	public DateFormat getDate(Locale locale);

	public DateFormat getDate(Locale locale, TimeZone timeZone);

	public DateFormat getDate(TimeZone timeZone);

	public DateFormat getDateTime(Locale locale);

	public DateFormat getDateTime(Locale locale, TimeZone timeZone);

	public DateFormat getDateTime(TimeZone timeZone);

	public DateFormat getSimpleDateFormat(String pattern);

	public DateFormat getSimpleDateFormat(String pattern, Locale locale);

	public DateFormat getSimpleDateFormat(
		String pattern, Locale locale, TimeZone timeZone);

	public DateFormat getSimpleDateFormat(String pattern, TimeZone timeZone);

	public DateFormat getTime(Locale locale);

	public DateFormat getTime(Locale locale, TimeZone timeZone);

	public DateFormat getTime(TimeZone timeZone);

}