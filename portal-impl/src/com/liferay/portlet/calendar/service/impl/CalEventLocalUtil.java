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

package com.liferay.portlet.calendar.service.impl;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.calendar.model.CalEvent;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
public class CalEventLocalUtil {

	protected static void clearEventsPool(long groupId) {
		String key = _encodeKey(groupId);

		_portalCache.remove(key);
	}

	protected static Map<String, List<CalEvent>> getEventsPool(long groupId) {
		String key = _encodeKey(groupId);

		Map<String, List<CalEvent>> eventsPool =
			(ConcurrentHashMap<String, List<CalEvent>>)_portalCache.get(key);

		if (eventsPool == null) {
			eventsPool = new ConcurrentHashMap<String, List<CalEvent>>();

			_portalCache.put(key, (Serializable)eventsPool);
		}

		return eventsPool;
	}

	private static String _encodeKey(long groupId) {
		return _CACHE_NAME.concat(StringPool.POUND).concat(
			StringUtil.toHexString(groupId));
	}

	private static final String _CACHE_NAME = CalEventLocalUtil.class.getName();

	private static PortalCache<String, Serializable> _portalCache =
		MultiVMPoolUtil.getCache(_CACHE_NAME);

}