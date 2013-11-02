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

package com.liferay.portal.kernel.cache;

import java.io.Serializable;

import java.util.Collection;

/**
 * @author Brian Wing Shun Chan
 * @author Edward Han
 * @author Shuyang Zhou
 */
public interface PortalCache<K extends Serializable, V> {

	public void destroy();

	public Collection<V> get(Collection<K> keys);

	public V get(K key);

	public String getName();

	public void put(K key, V value);

	public void put(K key, V value, int timeToLive);

	public void registerCacheListener(CacheListener<K, V> cacheListener);

	public void registerCacheListener(
		CacheListener<K, V> cacheListener,
		CacheListenerScope cacheListenerScope);

	public void remove(K key);

	public void removeAll();

	public void unregisterCacheListener(CacheListener<K, V> cacheListener);

	public void unregisterCacheListeners();

}