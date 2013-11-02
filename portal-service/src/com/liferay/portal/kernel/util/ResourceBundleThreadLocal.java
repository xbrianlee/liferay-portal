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
 * @author Shuyang Zhou
 */
public class ResourceBundleThreadLocal {

	public static boolean isReplace() {
		return _replace.get();
	}

	public static void setReplace(boolean replace) {
		_replace.set(replace);
	}

	private static ThreadLocal<Boolean> _replace =
		new AutoResetThreadLocal<Boolean>(
			ResourceBundleThreadLocal.class + "._replace", false);

}