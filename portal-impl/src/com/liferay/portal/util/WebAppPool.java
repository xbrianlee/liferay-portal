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

package com.liferay.portal.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Brian Wing Shun Chan
 */
public class WebAppPool {

	public static void clear() {
		_instance._webAppPool.clear();
	}

	public static Object get(Long webAppId, String key) {
		return _instance._get(webAppId, key);
	}

	public static void put(Long webAppId, String key, Object obj) {
		_instance._put(webAppId, key, obj);
	}

	public static Object remove(Long webAppId, String key) {
		return _instance._remove(webAppId, key);
	}

	private WebAppPool() {
		_webAppPool = new ConcurrentHashMap<Long, Map<String, Object>>();
	}

	private Object _get(Long webAppId, String key) {
		Map<String, Object> map = _webAppPool.get(webAppId);

		if (map == null) {
			return null;
		}
		else {
			return map.get(key);
		}
	}

	private void _put(Long webAppId, String key, Object obj) {
		Map<String, Object> map = _webAppPool.get(webAppId);

		if (map == null) {
			map = new ConcurrentHashMap<String, Object>();

			Map<String, Object> previousMap = _webAppPool.putIfAbsent(
				webAppId, map);

			if (previousMap != null) {
				map = previousMap;
			}
		}

		map.put(key, obj);
	}

	private Object _remove(Long webAppId, String key) {
		Map<String, Object> map = _webAppPool.get(webAppId);

		if (map == null) {
			return null;
		}
		else {
			return map.remove(key);
		}
	}

	private static WebAppPool _instance = new WebAppPool();

	private ConcurrentMap<Long, Map<String, Object>> _webAppPool;

}