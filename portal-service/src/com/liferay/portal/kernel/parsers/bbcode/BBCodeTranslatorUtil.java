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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Iliyan Peychev
 * @author Miguel Pastor
 */
public class BBCodeTranslatorUtil {

	public static BBCodeTranslator getBBCodeTranslator() {
		PortalRuntimePermission.checkGetBeanProperty(
			BBCodeTranslatorUtil.class);

		return _bbCodeTranslator;
	}

	public static String[] getEmoticonDescriptions() {
		return getBBCodeTranslator().getEmoticonDescriptions();
	}

	public static String[] getEmoticonFiles() {
		return getBBCodeTranslator().getEmoticonFiles();
	}

	public static String[][] getEmoticons() {
		return getBBCodeTranslator().getEmoticons();
	}

	public static String[] getEmoticonSymbols() {
		return getBBCodeTranslator().getEmoticonSymbols();
	}

	public static String getHTML(String bbcode) {
		return getBBCodeTranslator().getHTML(bbcode);
	}

	public static String parse(String message) {
		return getBBCodeTranslator().parse(message);
	}

	public void setBBCodeTranslator(BBCodeTranslator bbCodeTranslator) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_bbCodeTranslator = bbCodeTranslator;
	}

	private static BBCodeTranslator _bbCodeTranslator;

}