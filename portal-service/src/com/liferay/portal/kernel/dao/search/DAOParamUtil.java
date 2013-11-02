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

package com.liferay.portal.kernel.dao.search;

import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;

import java.util.Calendar;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class DAOParamUtil {

	public static boolean getBoolean(HttpServletRequest request, String param) {
		return GetterUtil.getBoolean(getString(request, param));
	}

	public static boolean getBoolean(
		PortletRequest portletRequest, String param) {

		return GetterUtil.getBoolean(getString(portletRequest, param));
	}

	public static int getInteger(HttpServletRequest request, String param) {
		return GetterUtil.getInteger(getString(request, param));
	}

	public static int getInteger(PortletRequest portletRequest, String param) {
		return GetterUtil.getInteger(getString(portletRequest, param));
	}

	public static String getISODate(HttpServletRequest request, String param) {
		int month = ParamUtil.getInteger(request, param + "Month");
		int day = ParamUtil.getInteger(request, param + "Day");
		int year = ParamUtil.getInteger(request, param + "Year");
		int hour = ParamUtil.getInteger(request, param + "Hour", -1);
		int minute = ParamUtil.getInteger(request, param + "Minute", -1);
		int amPm = ParamUtil.getInteger(request, param + "AmPm");

		if ((month >= 0) && (day > 0) && (year > 0)) {
			Calendar cal = CalendarFactoryUtil.getCalendar();

			if ((hour == -1) || (minute == -1)) {
				cal.set(year, month, day);
			}
			else {
				if (amPm == Calendar.PM) {
					hour += 12;
				}

				cal.set(year, month, day, hour, minute, 0);
			}

			DateFormat isoFormat = DateUtil.getISOFormat();

			return isoFormat.format(cal.getTime());
		}
		else {
			return null;
		}
	}

	public static String getISODate(
		PortletRequest portletRequest, String param) {

		int month = ParamUtil.getInteger(portletRequest, param + "Month");
		int day = ParamUtil.getInteger(portletRequest, param + "Day");
		int year = ParamUtil.getInteger(portletRequest, param + "Year");
		int hour = ParamUtil.getInteger(portletRequest, param + "Hour", -1);
		int minute = ParamUtil.getInteger(portletRequest, param + "Minute", -1);
		int amPm = ParamUtil.getInteger(portletRequest, param + "AmPm");

		if ((month >= 0) && (day > 0) && (year > 0)) {
			Calendar cal = CalendarFactoryUtil.getCalendar();

			if ((hour == -1) || (minute == -1)) {
				cal.set(year, month, day);
			}
			else {
				if (amPm == Calendar.PM) {
					hour += 12;
				}

				cal.set(year, month, day, hour, minute, 0);
			}

			DateFormat isoFormat = DateUtil.getISOFormat();

			return isoFormat.format(cal.getTime());
		}
		else {
			return null;
		}
	}

	public static long getLong(HttpServletRequest request, String param) {
		return GetterUtil.getLong(getString(request, param));
	}

	public static long getLong(PortletRequest portletRequest, String param) {
		return GetterUtil.getLong(getString(portletRequest, param));
	}

	public static short getShort(HttpServletRequest request, String param) {
		return GetterUtil.getShort(getString(request, param));
	}

	public static short getShort(PortletRequest portletRequest, String param) {
		return GetterUtil.getShort(getString(portletRequest, param));
	}

	public static String getString(HttpServletRequest request, String param) {
		String value = ParamUtil.getString(request, param);

		if (Validator.isNull(value)) {
			return null;
		}
		else {
			return value;
		}
	}

	public static String getString(
		PortletRequest portletRequest, String param) {

		String value = ParamUtil.getString(portletRequest, param);

		if (Validator.isNull(value)) {
			return null;
		}
		else {
			return value;
		}
	}

}