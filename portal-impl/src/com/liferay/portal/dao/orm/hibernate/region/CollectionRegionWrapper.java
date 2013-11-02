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

import net.sf.ehcache.hibernate.regions.EhcacheCollectionRegion;

import org.hibernate.cache.CacheDataDescription;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CollectionRegion;
import org.hibernate.cache.access.AccessType;
import org.hibernate.cache.access.CollectionRegionAccessStrategy;

/**
 * @author Edward Han
 */
public class CollectionRegionWrapper
	extends BaseRegionWrapper implements CollectionRegion {

	public CollectionRegionWrapper(
		EhcacheCollectionRegion ehcacheCollectionRegion) {

		super(ehcacheCollectionRegion);
	}

	@Override
	public CollectionRegionAccessStrategy buildAccessStrategy(
			AccessType accessType)
		throws CacheException {

		EhcacheCollectionRegion ehcacheCollectionRegion =
			getEhcacheCollectionRegion();

		return ehcacheCollectionRegion.buildAccessStrategy(accessType);
	}

	@Override
	public CacheDataDescription getCacheDataDescription() {
		EhcacheCollectionRegion ehcacheCollectionRegion =
			getEhcacheCollectionRegion();

		return ehcacheCollectionRegion.getCacheDataDescription();
	}

	@Override
	public void invalidate() {
		EhcacheCollectionRegion ehcacheCollectionRegion =
			getEhcacheCollectionRegion();

		ehcacheCollectionRegion.clear();
	}

	@Override
	public boolean isTransactionAware() {
		EhcacheCollectionRegion ehcacheCollectionRegion =
			getEhcacheCollectionRegion();

		return ehcacheCollectionRegion.isTransactionAware();
	}

	protected EhcacheCollectionRegion getEhcacheCollectionRegion() {
		return (EhcacheCollectionRegion)getEhcacheDataRegion();
	}

}