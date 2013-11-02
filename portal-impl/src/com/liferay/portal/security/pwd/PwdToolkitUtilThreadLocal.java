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

package com.liferay.portal.security.pwd;

import com.liferay.portal.kernel.util.InitialThreadLocal;

/**
 * @author Brian Wing Shun Chan
 */
public class PwdToolkitUtilThreadLocal {

	public static boolean isValidate() {
		return _validate.get();
	}

	public static void setValidate(boolean validate) {
		_validate.set(validate);
	}

	private static ThreadLocal<Boolean> _validate =
		new InitialThreadLocal<Boolean>(
			PwdToolkitUtilThreadLocal.class + "._validate", true);

}