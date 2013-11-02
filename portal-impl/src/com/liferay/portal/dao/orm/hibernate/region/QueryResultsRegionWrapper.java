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

package com.liferay.portal.dao.orm.hibernate.region;

import net.sf.ehcache.hibernate.regions.EhcacheQueryResultsRegion;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.QueryResultsRegion;

/**
 * @author Edward Han
 */
public class QueryResultsRegionWrapper
	extends BaseRegionWrapper implements QueryResultsRegion {

	public QueryResultsRegionWrapper(
		EhcacheQueryResultsRegion ehcacheQueryResultsRegion) {

		super(ehcacheQueryResultsRegion);
	}

	@Override
	public void evict(Object key) throws CacheException {
		EhcacheQueryResultsRegion ehcacheQueryResultsRegion =
			getEhcacheQueryResultsRegion();

		ehcacheQueryResultsRegion.evict(key);
	}

	@Override
	public void evictAll() throws CacheException {
		EhcacheQueryResultsRegion ehcacheQueryResultsRegion =
			getEhcacheQueryResultsRegion();

		ehcacheQueryResultsRegion.evictAll();
	}

	@Override
	public Object get(Object key) throws CacheException {
		EhcacheQueryResultsRegion ehcacheQueryResultsRegion =
			getEhcacheQueryResultsRegion();

		return ehcacheQueryResultsRegion.get(key);
	}

	@Override
	public void invalidate() {
		EhcacheQueryResultsRegion ehcacheQueryResultsRegion =
			getEhcacheQueryResultsRegion();

		ehcacheQueryResultsRegion.evictAll();
	}

	@Override
	public void put(Object key, Object value) throws CacheException {
		EhcacheQueryResultsRegion ehcacheQueryResultsRegion =
			getEhcacheQueryResultsRegion();

		ehcacheQueryResultsRegion.put(key, value);
	}

	protected EhcacheQueryResultsRegion getEhcacheQueryResultsRegion() {
		return (EhcacheQueryResultsRegion)getEhcacheDataRegion();
	}

}