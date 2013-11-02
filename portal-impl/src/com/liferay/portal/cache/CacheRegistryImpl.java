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

package com.liferay.portal.cache;

import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.cache.CacheRegistryItem;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class CacheRegistryImpl implements CacheRegistry {

	@Override
	public void clear() {
		for (Map.Entry<String, CacheRegistryItem> entry :
				_cacheRegistryItems.entrySet()) {

			CacheRegistryItem cacheRegistryItem = entry.getValue();

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Invalidating " + cacheRegistryItem.getRegistryName());
			}

			cacheRegistryItem.invalidate();
		}
	}

	@Override
	public void clear(String name) {
		CacheRegistryItem cacheRegistryItem = _cacheRegistryItems.get(name);

		if (cacheRegistryItem != null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Invalidating " + name);
			}

			cacheRegistryItem.invalidate();
		}
		else {
			_log.error("No cache registry found with name " + name);
		}
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void register(CacheRegistryItem cacheRegistryItem) {
		String name = cacheRegistryItem.getRegistryName();

		if (_log.isDebugEnabled()) {
			_log.debug("Registering " + name);
		}

		if (_cacheRegistryItems.containsKey(name)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Not registering duplicate " + name);
			}
		}
		else {
			_cacheRegistryItems.put(name, cacheRegistryItem);
		}
	}

	@Override
	public void setActive(boolean active) {
		_active = active;

		if (!active) {
			clear();
		}
	}

	@Override
	public void unregister(String name) {
		if (_log.isDebugEnabled()) {
			_log.debug("Unregistering " + name);
		}

		_cacheRegistryItems.remove(name);
	}

	private static Log _log = LogFactoryUtil.getLog(CacheRegistryImpl.class);

	private boolean _active = true;
	private Map<String, CacheRegistryItem> _cacheRegistryItems =
		new ConcurrentHashMap<String, CacheRegistryItem>();

}