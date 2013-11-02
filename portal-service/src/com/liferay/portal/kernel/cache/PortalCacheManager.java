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

import java.net.URL;

/**
 * @author Joseph Shum
 */
public interface PortalCacheManager<K extends Serializable, V> {

	public void clearAll() throws PortalCacheException;

	public PortalCache<K, V> getCache(String name) throws PortalCacheException;

	public PortalCache<K, V> getCache(String name, boolean blocking)
		throws PortalCacheException;

	public void reconfigureCaches(URL configurationURL);

	public void removeCache(String name);

}