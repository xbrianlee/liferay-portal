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

package com.liferay.portal.kernel.language;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Locale;

import javax.portlet.PortletConfig;

import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public class UnicodeLanguageUtil {

	public static String format(
		Locale locale, String pattern, Object argument) {

		return getUnicodeLanguage().format(locale, pattern, argument);
	}

	public static String format(
		Locale locale, String pattern, Object argument,
		boolean translateArguments) {

		return getUnicodeLanguage().format(
			locale, pattern, argument, translateArguments);
	}

	public static String format(
		Locale locale, String pattern, Object[] arguments) {

		return getUnicodeLanguage().format(locale, pattern, arguments);
	}

	public static String format(
		Locale locale, String pattern, Object[] arguments,
		boolean translateArguments) {

		return getUnicodeLanguage().format(
			locale, pattern, arguments, translateArguments);
	}

	public static String format(
		PageContext pageContext, String pattern, LanguageWrapper argument) {

		return getUnicodeLanguage().format(pageContext, pattern, argument);
	}

	public static String format(
		PageContext pageContext, String pattern, LanguageWrapper argument,
		boolean translateArguments) {

		return getUnicodeLanguage().format(
			pageContext, pattern, argument, translateArguments);
	}

	public static String format(
		PageContext pageContext, String pattern, LanguageWrapper[] arguments) {

		return getUnicodeLanguage().format(pageContext, pattern, arguments);
	}

	public static String format(
		PageContext pageContext, String pattern, LanguageWrapper[] arguments,
		boolean translateArguments) {

		return getUnicodeLanguage().format(
			pageContext, pattern, arguments, translateArguments);
	}

	public static String format(
		PageContext pageContext, String pattern, Object argument) {

		return getUnicodeLanguage().format(pageContext, pattern, argument);
	}

	public static String format(
		PageContext pageContext, String pattern, Object argument,
		boolean translateArguments) {

		return getUnicodeLanguage().format(
			pageContext, pattern, argument, translateArguments);
	}

	public static String format(
		PageContext pageContext, String pattern, Object[] arguments) {

		return getUnicodeLanguage().format(pageContext, pattern, arguments);
	}

	public static String format(
		PageContext pageContext, String pattern, Object[] arguments,
		boolean translateArguments) {

		return getUnicodeLanguage().format(
			pageContext, pattern, arguments, translateArguments);
	}

	public static String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object argument) {

		return getUnicodeLanguage().format(
			portletConfig, locale, pattern, argument);
	}

	public static String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object argument, boolean translateArguments) {

		return getUnicodeLanguage().format(
			portletConfig, locale, pattern, argument, translateArguments);
	}

	public static String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object[] arguments) {

		return getUnicodeLanguage().format(
			portletConfig, locale, pattern, arguments);
	}

	public static String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object[] arguments, boolean translateArguments) {

		return getUnicodeLanguage().format(
			portletConfig, locale, pattern, arguments, translateArguments);
	}

	public static String get(Locale locale, String key) {
		return getUnicodeLanguage().get(locale, key);
	}

	public static String get(Locale locale, String key, String defaultValue) {
		return getUnicodeLanguage().get(locale, key, defaultValue);
	}

	public static String get(PageContext pageContext, String key) {
		return getUnicodeLanguage().get(pageContext, key);
	}

	public static String get(
		PageContext pageContext, String key, String defaultValue) {

		return getUnicodeLanguage().get(pageContext, key, defaultValue);
	}

	public static String get(
		PortletConfig portletConfig, Locale locale, String key) {

		return getUnicodeLanguage().get(portletConfig, locale, key);
	}

	public static String get(
		PortletConfig portletConfig, Locale locale, String key,
		String defaultValue) {

		return getUnicodeLanguage().get(
			portletConfig, locale, key, defaultValue);
	}

	public static String getTimeDescription(
		PageContext pageContext, long milliseconds) {

		return getUnicodeLanguage().getTimeDescription(
			pageContext, milliseconds);
	}

	public static String getTimeDescription(
		PageContext pageContext, Long milliseconds) {

		return getUnicodeLanguage().getTimeDescription(
			pageContext, milliseconds);
	}

	public static UnicodeLanguage getUnicodeLanguage() {
		PortalRuntimePermission.checkGetBeanProperty(UnicodeLanguageUtil.class);

		return _unicodeLanguage;
	}

	public void setUnicodeLanguage(UnicodeLanguage unicodeLanguage) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_unicodeLanguage = unicodeLanguage;
	}

	private static UnicodeLanguage _unicodeLanguage;

}