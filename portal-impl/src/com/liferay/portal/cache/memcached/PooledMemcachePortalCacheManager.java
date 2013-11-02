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

package com.liferay.portal.cache.memcached;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheManager;

import java.net.URL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Michael C. Han
 */
public class PooledMemcachePortalCacheManager<V>
	implements PortalCacheManager<String, V> {

	public void afterPropertiesSet() {
	}

	@Override
	public void clearAll() {
		_portalCaches.clear();
	}

	public void destroy() throws Exception {
		for (PortalCache<String, V> portalCache : _portalCaches.values()) {
			portalCache.destroy();
		}
	}

	@Override
	public PortalCache<String, V> getCache(String name) {
		return getCache(name, false);
	}

	@Override
	public PortalCache<String, V> getCache(String name, boolean blocking) {
		PortalCache<String, V> portalCache = _portalCaches.get(name);

		if (portalCache == null) {
			portalCache = new PooledMemcachePortalCache<V>(
				name, _memcachedClientFactory, _timeout, _timeoutTimeUnit);

			_portalCaches.put(name, portalCache);
		}

		return portalCache;
	}

	@Override
	public void reconfigureCaches(URL configurationURL) {
	}

	@Override
	public void removeCache(String name) {
		_portalCaches.remove(name);
	}

	public void setMemcachedClientPool(
		MemcachedClientFactory memcachedClientFactory) {

		_memcachedClientFactory = memcachedClientFactory;
	}

	public void setTimeout(int timeout) {
		_timeout = timeout;
	}

	public void setTimeoutTimeUnit(String timeoutTimeUnit) {
		_timeoutTimeUnit = TimeUnit.valueOf(timeoutTimeUnit);
	}

	private MemcachedClientFactory _memcachedClientFactory;
	private Map<String, PortalCache<String, V>> _portalCaches =
		new ConcurrentHashMap<String, PortalCache<String, V>>();
	private int _timeout;
	private TimeUnit _timeoutTimeUnit;

}