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
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Alexander Chow
 * @author Manuel de la Peña
 * @author Raymond Augé
 */
@PrepareForTest({CalendarFactoryUtil.class, DateFormatFactoryUtil.class})
@RunWith(PowerMockRunner.class)
public class DateUtilTest extends PowerMockito {

	@Test
	public void testGetDaysBetweenLeap() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		_testGetDaysBetween(
			dateFormat.parse("2-28-2012"), dateFormat.parse("3-1-2012"), 2);
	}

	@Test
	public void testGetDaysBetweenMonth() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		_testGetDaysBetween(
			dateFormat.parse("12-31-2011"), dateFormat.parse("1-1-2012"), 1);
	}

	@Test
	public void testGetDaysBetweenReverse() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		_testGetDaysBetween(
			dateFormat.parse("3-1-2012"), dateFormat.parse("2-28-2012"), 2);
	}

	@Test
	public void testGetDaysBetweenSame() throws Exception {
		_testGetDaysBetween(new Date(), new Date(), 0);
	}

	@Test
	public void testGetDaysBetweenYear() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		_testGetDaysBetween(
			dateFormat.parse("1-1-2011"), dateFormat.parse("1-1-2012"), 365);
	}

	@Test
	public void testGetISOFormatAny() {
		_testGetISOFormat(Mockito.anyString(),"yyyyMMddHHmmssz");
	}

	@Test
	public void testGetISOFormatLength8() {
		_testGetISOFormat("01234567","yyyyMMdd");
	}

	@Test
	public void testGetISOFormatLength12() {
		_testGetISOFormat("012345678901","yyyyMMddHHmm");
	}

	@Test
	public void testGetISOFormatLength13() {
		_testGetISOFormat("0123456789012","yyyyMMdd'T'HHmm");
	}

	@Test
	public void testGetISOFormatLength14() {
		_testGetISOFormat("01234567890123","yyyyMMddHHmmss");
	}

	@Test
	public void testGetISOFormatLength15() {
		_testGetISOFormat("012345678901234","yyyyMMdd'T'HHmmss");
	}

	@Test
	public void testGetISOFormatT() {
		_testGetISOFormat("01234567T9012345","yyyyMMdd'T'HHmmssz");
	}

	@Test
	public void testGetUTCFormat() {
		_testGetUTCFormat("19721223", "yyyyMMdd");
	}

	private void _mockDateUtilPattern(String pattern) {
		mockStatic(DateFormatFactoryUtil.class);

		when(
			DateFormatFactoryUtil.getSimpleDateFormat(pattern)
		).thenReturn(
			new SimpleDateFormat(pattern, LocaleUtil.SPAIN)
		);
	}

	private void _testGetDaysBetween(Date date1, Date date2, int expected) {
		mockStatic(CalendarFactoryUtil.class);

		when(
			CalendarFactoryUtil.getCalendar()
		).thenReturn(
			new GregorianCalendar()
		);

		Assert.assertEquals(
			expected, DateUtil.getDaysBetween(date1, date2, _timeZone));
	}

	private void _testGetISOFormat(String text, String pattern) {
		_mockDateUtilPattern(pattern);

		DateFormat dateFormat = DateUtil.getISOFormat(text);

		SimpleDateFormat simpleDateFormat = (SimpleDateFormat)dateFormat;

		Assert.assertEquals(pattern, simpleDateFormat.toPattern());
	}

	private void _testGetUTCFormat(String date, String pattern) {
		mockStatic(DateFormatFactoryUtil.class);

		when(
			DateFormatFactoryUtil.getSimpleDateFormat(
				Mockito.anyString(), Mockito.any(TimeZone.class))
		).thenAnswer(
			new Answer<SimpleDateFormat>() {

				@Override
				public SimpleDateFormat answer(
						InvocationOnMock invocationOnMock)
					throws Throwable {

					return new TestSimpleDateFormat(
						(String)invocationOnMock.getArguments()[0]);
				}

			}
		);

		DateFormat utcDateFormat = DateUtil.getUTCFormat(date);

		Assert.assertNotNull(utcDateFormat);
		Assert.assertTrue(utcDateFormat instanceof SimpleDateFormat);

		TestSimpleDateFormat testSimpleDateFormat =
			(TestSimpleDateFormat)utcDateFormat;

		Assert.assertEquals(testSimpleDateFormat.getPattern(), pattern);
	}

	@Mock
	private TimeZone _timeZone;

	private class TestSimpleDateFormat extends SimpleDateFormat {

		public TestSimpleDateFormat(String pattern) {
			super(pattern);

			_pattern = pattern;
		}

		public String getPattern() {
			return _pattern;
		}

		private String _pattern;

	}

}