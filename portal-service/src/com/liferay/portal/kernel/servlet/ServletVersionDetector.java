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

package com.liferay.portal.kernel.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class ServletVersionDetector {

	public static boolean is2_5() {
		if (_2_5 != null) {
			return _2_5.booleanValue();
		}

		try {
			ServletContext.class.getMethod("getContextPath");

			_2_5 = Boolean.TRUE;
		}
		catch (Exception e) {
			_2_5 = Boolean.FALSE;
		}

		return _2_5.booleanValue();
	}

	public static boolean is3_0() {
		if (_3_0 != null) {
			return _3_0.booleanValue();
		}

		try {
			Cookie.class.getMethod("isHttpOnly");

			_3_0 = Boolean.TRUE;
		}
		catch (Exception e) {
			_3_0 = Boolean.FALSE;
		}

		return _3_0.booleanValue();
	}

	private static Boolean _2_5;
	private static Boolean _3_0;

}