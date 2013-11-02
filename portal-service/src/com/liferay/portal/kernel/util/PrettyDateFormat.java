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

import com.liferay.portal.kernel.language.LanguageUtil;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Alexander Chow
 */
public class PrettyDateFormat extends DateFormat {

	public PrettyDateFormat(Locale locale, TimeZone timeZone) {
		_locale = locale;
		_timeZone = timeZone;
		_todayString = LanguageUtil.get(_locale, "today");
		_yesterdayString = LanguageUtil.get(_locale, "yesterday");
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	public PrettyDateFormat(long companyId, Locale locale, TimeZone timeZone) {
		this(locale, timeZone);
	}

	@Override
	public StringBuffer format(Date date, StringBuffer sb, FieldPosition pos) {
		String dateString = StringPool.NBSP;

		if (date == null) {
			return sb.append(dateString);
		}

		Date today = new Date();

		Calendar cal = Calendar.getInstance(_timeZone, _locale);

		cal.setTime(today);
		cal.add(Calendar.DATE, -1);

		Date yesterday = cal.getTime();

		Format dateFormatDate = FastDateFormatFactoryUtil.getDate(
			_locale, _timeZone);
		Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(
			_locale, _timeZone);
		Format dateFormatTime = FastDateFormatFactoryUtil.getTime(
			_locale, _timeZone);

		dateString = dateFormatDate.format(date);

		if (dateString.equals(dateFormatDate.format(today))) {
			dateString =
				_todayString + StringPool.SPACE +
					dateFormatTime.format(date);
		}
		else if (dateString.equals(dateFormatDate.format(yesterday))) {
			dateString =
				_yesterdayString + StringPool.SPACE +
					dateFormatTime.format(date);
		}
		else {
			dateString = dateFormatDateTime.format(date);
		}

		return sb.append(dateString);
	}

	@Override
	public Date parse(String source, ParsePosition pos) {
		Format dateFormatDate = FastDateFormatFactoryUtil.getDate(
			_locale, _timeZone);
		DateFormat dateFormatDateTime = DateFormatFactoryUtil.getDateTime(
			_locale, _timeZone);

		Date today = new Date();

		String dateString = source.substring(pos.getIndex());

		if (dateString.startsWith(_todayString)) {
			dateString = dateString.replaceFirst(
				_todayString, dateFormatDate.format(today));
		}
		else if (dateString.startsWith(_yesterdayString)) {
			Calendar cal = Calendar.getInstance(_timeZone, _locale);

			cal.setTime(today);
			cal.add(Calendar.DATE, -1);

			Date yesterday = cal.getTime();

			dateString = dateString.replaceFirst(
				_todayString, dateFormatDate.format(yesterday));
		}

		return dateFormatDateTime.parse(dateString, new ParsePosition(0));
	}

	private Locale _locale;
	private TimeZone _timeZone;
	private String _todayString;
	private String _yesterdayString;

}