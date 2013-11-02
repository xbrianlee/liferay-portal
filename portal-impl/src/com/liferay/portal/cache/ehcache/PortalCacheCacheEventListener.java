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
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.Serializable;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

/**
 * @author Edward C. Han
 * @author Shuyang Zhou
 */
public class PortalCacheCacheEventListener<K extends Serializable, V>
	implements CacheEventListener {

	public PortalCacheCacheEventListener(
		CacheListener<K, V> cacheListener, PortalCache<K, V> portalCache) {

		_cacheListener = cacheListener;
		_portalCache = portalCache;
	}

	@Override
	public Object clone() {
		return new PortalCacheCacheEventListener<K, V>(
			_cacheListener, _portalCache);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void notifyElementEvicted(Ehcache ehcache, Element element) {
		K key = (K)element.getObjectKey();
		V value = (V)element.getObjectValue();

		_cacheListener.notifyEntryEvicted(_portalCache, key, value);

		if (_log.isDebugEnabled()) {
			_log.debug("Evicted " + key + " from " + ehcache.getName());
		}
	}

	@Override
	public void notifyElementExpired(Ehcache ehcache, Element element) {
		K key = (K)element.getObjectKey();
		V value = (V)element.getObjectValue();

		_cacheListener.notifyEntryExpired(_portalCache, key, value);

		if (_log.isDebugEnabled()) {
			_log.debug("Expired " + key + " from " + ehcache.getName());
		}
	}

	@Override
	public void notifyElementPut(Ehcache ehcache, Element element)
		throws CacheException {

		K key = (K)element.getObjectKey();
		V value = (V)element.getObjectValue();

		_cacheListener.notifyEntryPut(_portalCache, key, value);

		if (_log.isDebugEnabled()) {
			_log.debug("Inserted " + key + " into " + ehcache.getName());
		}
	}

	@Override
	public void notifyElementRemoved(Ehcache ehcache, Element element)
		throws CacheException {

		K key = (K)element.getObjectKey();
		V value = (V)element.getObjectValue();

		_cacheListener.notifyEntryRemoved(_portalCache, key, value);

		if (_log.isDebugEnabled()) {
			_log.debug("Removed " + key + " from " + ehcache.getName());
		}
	}

	@Override
	public void notifyElementUpdated(Ehcache ehcache, Element element)
		throws CacheException {

		K key = (K)element.getObjectKey();
		V value = (V)element.getObjectValue();

		_cacheListener.notifyEntryUpdated(_portalCache, key, value);

		if (_log.isDebugEnabled()) {
			_log.debug("Updated " + key + " in " + ehcache.getName());
		}
	}

	@Override
	public void notifyRemoveAll(Ehcache ehcache) {
		_cacheListener.notifyRemoveAll(_portalCache);

		if (_log.isDebugEnabled()) {
			_log.debug("Cleared " + ehcache.getName());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalCacheCacheEventListener.class);

	private CacheListener<K, V> _cacheListener;
	private PortalCache<K, V> _portalCache;

}