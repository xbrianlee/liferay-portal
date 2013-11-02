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

/**
 * @author Brian Wing Shun Chan
 * @author Clarence Shen
 * @author Harry Mark
 * @author Samuel Kong
 */
public interface Html {

	public String escape(String text);

	public String escape(String text, int mode);

	public String escapeAttribute(String attribute);

	public String escapeCSS(String css);

	public String escapeHREF(String href);

	public String escapeJS(String js);

	public String escapeURL(String url);

	public String escapeXPath(String xPath);

	public String escapeXPathAttribute(String xPathAttribute);

	public String extractText(String html);

	public String fromInputSafe(String text);

	public String render(String html);

	public String replaceMsWordCharacters(String text);

	public String replaceNewLine(String html);

	public String stripBetween(String text, String tag);

	public String stripComments(String text);

	public String stripHtml(String text);

	public String toInputSafe(String text);

	public String unescape(String text);

	public String unescapeCDATA(String text);

	public String wordBreak(String text, int columns);

}