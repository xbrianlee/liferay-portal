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

package com.liferay.util;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

/**
 * @author Shuyang Zhou
 */
public class RSSThreadLocal {

	public static boolean isExportRSS() {
		return _exportRSS.get();
	}

	public static void setExportRSS(boolean exportRSS) {
		_exportRSS.set(exportRSS);
	}

	private static ThreadLocal<Boolean> _exportRSS =
		new AutoResetThreadLocal<Boolean>(
			RSSThreadLocal.class + "._exportRSS", false);

}