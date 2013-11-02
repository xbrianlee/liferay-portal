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

package com.liferay.portal.kernel.webcache;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class WebCachePoolUtil {

	public static void clear() {
		getWebCachePool().clear();
	}

	public static Object get(String key, WebCacheItem wci) {
		return getWebCachePool().get(key, wci);
	}

	public static WebCachePool getWebCachePool() {
		PortalRuntimePermission.checkGetBeanProperty(WebCachePoolUtil.class);

		return _webCachePool;
	}

	public static void remove(String key) {
		getWebCachePool().remove(key);
	}

	public void setWebCachePool(WebCachePool webCachePool) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_webCachePool = webCachePool;
	}

	private static WebCachePool _webCachePool;

}