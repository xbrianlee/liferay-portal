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

import net.spy.memcached.MemcachedClientIF;

/**
 * @author Michael C. Han
 */
public interface MemcachedClientFactory {

	public void clear() throws Exception;

	public void close() throws Exception;

	public MemcachedClientIF getMemcachedClient() throws Exception;

	public int getNumActive();

	public int getNumIdle();

	public void invalidateMemcachedClient(MemcachedClientIF memcachedClient)
		throws Exception;

	public void returnMemcachedObject(MemcachedClientIF memcachedClient)
		throws Exception;

}