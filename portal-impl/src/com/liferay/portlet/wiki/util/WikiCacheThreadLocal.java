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

package com.liferay.portlet.wiki.util;

import com.liferay.portal.kernel.util.InitialThreadLocal;

/**
 * @author Jorge Ferrer
 */
public class WikiCacheThreadLocal {

	public static boolean isClearCache() {
		return _clearCache.get();
	}

	public static void setClearCache(boolean clearCache) {
		_clearCache.set(clearCache);
	}

	private static ThreadLocal<Boolean> _clearCache =
		new InitialThreadLocal<Boolean>(
			WikiCacheThreadLocal.class + "._clearCache", true);

}