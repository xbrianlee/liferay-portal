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

package com.liferay.portal.cache.ehcache;

import com.liferay.portal.kernel.cache.CacheListener;
import com.liferay.portal.kernel.cache.CacheListenerScope;
import com.liferay.portal.kernel.cache.PortalCache;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.NotificationScope;
import net.sf.ehcache.event.RegisteredEventListeners;

/**
 * @author Brian Wing Shun Chan
 * @author Edward Han
 * @author Shuyang Zhou
 */
public class EhcachePortalCache<K extends Serializable, V>
	implements PortalCache<K, V> {

	public EhcachePortalCache(Ehcache ehcache) {
		_ehcache = ehcache;
	}

	@Override
	public void destroy() {
	}

	@Override
	public Collection<V> get(Collection<K> keys) {
		List<V> values = new ArrayList<V>(keys.size());

		for (K key : keys) {
			values.add(get(key));
		}

		return values;
	}

	@Override
	public V get(K key) {
		Element element = _ehcache.get(key);

		if (element == null) {
			return null;
		}
		else {
			return (V)element.getObjectValue();
		}
	}

	@Override
	public String getName() {
		return _ehcache.getName();
	}

	@Override
	public void put(K key, V value) {
		Element element = new Element(key, value);

		_ehcache.put(element);
	}

	@Override
	public void put(K key, V value, int timeToLive) {
		Element element = new Element(key, value);

		element.setTimeToLive(timeToLive);

		_ehcache.put(element);
	}

	@Override
	public void registerCacheListener(CacheListener<K, V> cacheListener) {
		registerCacheListener(cacheListener, CacheListenerScope.ALL);
	}

	@Override
	public void registerCacheListener(
		CacheListener<K, V> cacheListener,
		CacheListenerScope cacheListenerScope) {

		if (_cacheEventListeners.containsKey(cacheListener)) {
			return;
		}

		CacheEventListener cacheEventListener =
			new PortalCacheCacheEventListener<K, V>(cacheListener, this);

		_cacheEventListeners.put(cacheListener, cacheEventListener);

		NotificationScope notificationScope = getNotificationScope(
			cacheListenerScope);

		RegisteredEventListeners registeredEventListeners =
			_ehcache.getCacheEventNotificationService();

		registeredEventListeners.registerListener(
			cacheEventListener, notificationScope);
	}

	@Override
	public void remove(K key) {
		_ehcache.remove(key);
	}

	@Override
	public void removeAll() {
		_ehcache.removeAll();
	}

	public void setEhcache(Ehcache ehcache) {
		_ehcache = ehcache;
	}

	@Override
	public void unregisterCacheListener(CacheListener<K, V> cacheListener) {
		CacheEventListener cacheEventListener = _cacheEventListeners.get(
			cacheListener);

		if (cacheEventListener != null) {
			RegisteredEventListeners registeredEventListeners =
				_ehcache.getCacheEventNotificationService();

			registeredEventListeners.unregisterListener(cacheEventListener);
		}

		_cacheEventListeners.remove(cacheListener);
	}

	@Override
	public void unregisterCacheListeners() {
		RegisteredEventListeners registeredEventListeners =
			_ehcache.getCacheEventNotificationService();

		for (CacheEventListener cacheEventListener :
				_cacheEventListeners.values()) {

			registeredEventListeners.unregisterListener(cacheEventListener);
		}

		_cacheEventListeners.clear();
	}

	protected NotificationScope getNotificationScope(
		CacheListenerScope cacheListenerScope) {

		if (cacheListenerScope.equals(CacheListenerScope.ALL)) {
			return NotificationScope.ALL;
		}
		else if (cacheListenerScope.equals(CacheListenerScope.LOCAL)) {
			return NotificationScope.LOCAL;
		}
		else {
			return NotificationScope.REMOTE;
		}
	}

	private Map<CacheListener<K, V>, CacheEventListener> _cacheEventListeners =
		new ConcurrentHashMap<CacheListener<K, V>, CacheEventListener>();
	private Ehcache _ehcache;

}