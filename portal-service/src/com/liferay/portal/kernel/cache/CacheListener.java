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

/**
 * @author Edward Han
 * @author Shuyang Zhou
 */
public interface CacheListener<K extends Serializable, V> {

	public void notifyEntryEvicted(
			PortalCache<K, V> portalCache, K key, V value)
		throws PortalCacheException;

	public void notifyEntryExpired(
			PortalCache<K, V> portalCache, K key, V value)
		throws PortalCacheException;

	public void notifyEntryPut(PortalCache<K, V> portalCache, K key, V value)
		throws PortalCacheException;

	public void notifyEntryRemoved(
			PortalCache<K, V> portalCache, K key, V value)
		throws PortalCacheException;

	public void notifyEntryUpdated(
			PortalCache<K, V> portalCache, K key, V value)
		throws PortalCacheException;

	public void notifyRemoveAll(PortalCache<K, V> portalCache)
		throws PortalCacheException;

}