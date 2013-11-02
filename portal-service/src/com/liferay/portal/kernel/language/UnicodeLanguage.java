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

import java.util.Locale;

import javax.portlet.PortletConfig;

import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public interface UnicodeLanguage {

	public String format(Locale locale, String pattern, Object argument);

	public String format(
		Locale locale, String pattern, Object argument,
		boolean translateArguments);

	public String format(Locale locale, String pattern, Object[] arguments);

	public String format(
		Locale locale, String pattern, Object[] arguments,
		boolean translateArguments);

	public String format(
		PageContext pageContext, String pattern, LanguageWrapper argument);

	public String format(
		PageContext pageContext, String pattern, LanguageWrapper argument,
		boolean translateArguments);

	public String format(
		PageContext pageContext, String pattern, LanguageWrapper[] arguments);

	public String format(
		PageContext pageContext, String pattern, LanguageWrapper[] arguments,
		boolean translateArguments);

	public String format(
		PageContext pageContext, String pattern, Object argument);

	public String format(
		PageContext pageContext, String pattern, Object argument,
		boolean translateArguments);

	public String format(
		PageContext pageContext, String pattern, Object[] arguments);

	public String format(
		PageContext pageContext, String pattern, Object[] arguments,
		boolean translateArguments);

	public String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object argument);

	public String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object argument, boolean translateArguments);

	public String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object[] arguments);

	public String format(
		PortletConfig portletConfig, Locale locale, String pattern,
		Object[] arguments, boolean translateArguments);

	public String get(Locale locale, String key);

	public String get(Locale locale, String key, String defaultValue);

	public String get(PageContext pageContext, String key);

	public String get(PageContext pageContext, String key, String defaultValue);

	public String get(PortletConfig portletConfig, Locale locale, String key);

	public String get(
		PortletConfig portletConfig, Locale locale, String key,
		String defaultValue);

	public String getTimeDescription(
		PageContext pageContext, long milliseconds);

	public String getTimeDescription(
		PageContext pageContext, Long milliseconds);

}