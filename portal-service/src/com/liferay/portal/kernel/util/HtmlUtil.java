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

/**
 * @author Brian Wing Shun Chan
 * @author Clarence Shen
 * @author Harry Mark
 * @author Samuel Kong
 */
public class HtmlUtil {

	public static String escape(String html) {
		return getHtml().escape(html);
	}

	public static String escape(String html, int mode) {
		return getHtml().escape(html, mode);
	}

	public static String escapeAttribute(String attribute) {
		return getHtml().escapeAttribute(attribute);
	}

	public static String escapeCSS(String css) {
		return getHtml().escapeCSS(css);
	}

	public static String escapeHREF(String href) {
		return getHtml().escapeHREF(href);
	}

	public static String escapeJS(String js) {
		return getHtml().escapeJS(js);
	}

	public static String escapeURL(String url) {
		return getHtml().escapeURL(url);
	}

	public static String escapeXPath(String xPath) {
		return getHtml().escapeXPath(xPath);
	}

	public static String escapeXPathAttribute(String xPathAttribute) {
		return getHtml().escapeXPathAttribute(xPathAttribute);
	}

	public static String extractText(String html) {
		return getHtml().extractText(html);
	}

	public static String fromInputSafe(String html) {
		return getHtml().fromInputSafe(html);
	}

	public static Html getHtml() {
		PortalRuntimePermission.checkGetBeanProperty(HtmlUtil.class);

		return _html;
	}

	public static String render(String html) {
		return getHtml().render(html);
	}

	public static String replaceMsWordCharacters(String html) {
		return getHtml().replaceMsWordCharacters(html);
	}

	public static String replaceNewLine(String html) {
		return getHtml().replaceNewLine(html);
	}

	public static String stripBetween(String html, String tag) {
		return getHtml().stripBetween(html, tag);
	}

	public static String stripComments(String html) {
		return getHtml().stripComments(html);
	}

	public static String stripHtml(String html) {
		return getHtml().stripHtml(html);
	}

	public static String toInputSafe(String html) {
		return getHtml().toInputSafe(html);
	}

	public static String unescape(String html) {
		return getHtml().unescape(html);
	}

	public static String unescapeCDATA(String html) {
		return getHtml().unescapeCDATA(html);
	}

	public static String wordBreak(String html, int columns) {
		return getHtml().wordBreak(html, columns);
	}

	public void setHtml(Html html) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_html = html;
	}

	private static Html _html;

}