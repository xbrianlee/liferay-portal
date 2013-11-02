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

package com.liferay.portal.kernel.parsers.bbcode;

/**
 * @author Iliyan Peychev
 * @author Miguel Pastor
 */
public interface BBCodeTranslator {

	public String[] getEmoticonDescriptions();

	public String[] getEmoticonFiles();

	public String[][] getEmoticons();

	public String[] getEmoticonSymbols();

	public String getHTML(String bbcode);

	public String parse(String message);

}