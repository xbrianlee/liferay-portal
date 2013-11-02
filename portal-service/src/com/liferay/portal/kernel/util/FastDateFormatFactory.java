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

import java.text.Format;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public interface FastDateFormatFactory {

	public Format getDate(int style, Locale locale, TimeZone timeZone);

	public Format getDate(Locale locale);

	public Format getDate(Locale locale, TimeZone timeZone);

	public Format getDate(TimeZone timeZone);

	public Format getDateTime(
		int dateStyle, int timeStyle, Locale locale, TimeZone timeZone);

	public Format getDateTime(Locale locale);

	public Format getDateTime(Locale locale, TimeZone timeZone);

	public Format getDateTime(TimeZone timeZone);

	public Format getSimpleDateFormat(String pattern);

	public Format getSimpleDateFormat(String pattern, Locale locale);

	public Format getSimpleDateFormat(
		String pattern, Locale locale, TimeZone timeZone);

	public Format getSimpleDateFormat(String pattern, TimeZone timeZone);

	public Format getTime(int style, Locale locale, TimeZone timeZone);

	public Format getTime(Locale locale);

	public Format getTime(Locale locale, TimeZone timeZone);

	public Format getTime(TimeZone timeZone);

}