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

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class PrefsParamUtil {

	public static boolean getBoolean(
		PortletPreferences preferences, HttpServletRequest request,
		String param) {

		return getBoolean(
			preferences, request, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(
		PortletPreferences preferences, HttpServletRequest request,
		String param, boolean defaultValue) {

		String preferencesValue = preferences.getValue(param, null);

		boolean getterUtilValue = GetterUtil.getBoolean(
			preferencesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static boolean getBoolean(
		PortletPreferences preferences, PortletRequest portletRequest,
		String param) {

		return getBoolean(
			preferences, portletRequest, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(
		PortletPreferences preferences, PortletRequest portletRequest,
		String param, boolean defaultValue) {

		String preferencesValue = preferences.getValue(param, null);

		boolean getterUtilValue = GetterUtil.getBoolean(
			preferencesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static double getDouble(
		PortletPreferences preferences, HttpServletRequest request,
		String param) {

		return getDouble(
			preferences, request, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDouble(
		PortletPreferences preferences, HttpServletRequest request,
		String param, double defaultValue) {

		String preferencesValue = preferences.getValue(param, null);

		double getterUtilValue = GetterUtil.getDouble(
			preferencesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static double getDouble(
		PortletPreferences preferences, PortletRequest portletRequest,
		String param) {

		return getDouble(
			preferences, portletRequest, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDouble(
		PortletPreferences preferences, PortletRequest portletRequest,
		String param, double defaultValue) {

		String preferencesValue = preferences.getValue(param, null);

		double getterUtilValue = GetterUtil.getDouble(
			preferencesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static int getInteger(
		PortletPreferences preferences, HttpServletRequest request,
		String param) {

		return getInteger(
			preferences, request, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getInteger(
		PortletPreferences preferences, HttpServletRequest request,
		String param, int defaultValue) {

		String preferencesValue = preferences.getValue(param, null);

		int getterUtilValue = GetterUtil.getInteger(
			preferencesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static int getInteger(
		PortletPreferences preferences, PortletRequest portletRequest,
		String param) {

		return getInteger(
			preferences, portletRequest, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getInteger(
		PortletPreferences preferences, PortletRequest portletRequest,
		String param, int defaultValue) {

		String preferencesValue = preferences.getValue(param, null);

		int getterUtilValue = GetterUtil.getInteger(
			preferencesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static long getLong(
		PortletPreferences preferences, HttpServletRequest request,
		String param) {

		return getLong(preferences, request, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLong(
		PortletPreferences preferences, HttpServletRequest request,
		String param, long defaultValue) {

		String preferencesValue = preferences.getValue(param, null);

		long getterUtilValue = GetterUtil.getLong(
			preferencesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static long getLong(
		PortletPreferences preferences, PortletRequest portletRequest,
		String param) {

		return getLong(
			preferences, portletRequest, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLong(
		PortletPreferences preferences, PortletRequest portletRequest,
		String param, long defaultValue) {

		String preferencesValue = preferences.getValue(param, null);

		long getterUtilValue = GetterUtil.getLong(
			preferencesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static String getString(
		PortletPreferences preferences, HttpServletRequest request,
		String param) {

		return getString(
			preferences, request, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getString(
		PortletPreferences preferences, HttpServletRequest request,
		String param, String defaultValue) {

		String preferencesValue = preferences.getValue(param, null);

		String getterUtilValue = GetterUtil.getString(
			preferencesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static String getString(
		PortletPreferences preferences, PortletRequest portletRequest,
		String param) {

		return getString(
			preferences, portletRequest, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getString(
		PortletPreferences preferences, PortletRequest portletRequest,
		String param, String defaultValue) {

		String preferencesValue = preferences.getValue(param, null);

		String getterUtilValue = GetterUtil.getString(
			preferencesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

}