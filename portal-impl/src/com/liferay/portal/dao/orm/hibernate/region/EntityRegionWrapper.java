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

import net.sf.ehcache.hibernate.regions.EhcacheEntityRegion;

import org.hibernate.cache.CacheDataDescription;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.EntityRegion;
import org.hibernate.cache.access.AccessType;
import org.hibernate.cache.access.EntityRegionAccessStrategy;

/**
 * @author Edward Han
 */
public class EntityRegionWrapper
	extends BaseRegionWrapper implements EntityRegion {

	public EntityRegionWrapper(EhcacheEntityRegion ehcacheEntityRegion) {
		super(ehcacheEntityRegion);
	}

	@Override
	public EntityRegionAccessStrategy buildAccessStrategy(AccessType accessType)
		throws CacheException {

		EhcacheEntityRegion ehcacheEntityRegion = getEhcacheEntityRegion();

		return ehcacheEntityRegion.buildAccessStrategy(accessType);
	}

	@Override
	public CacheDataDescription getCacheDataDescription() {
		EhcacheEntityRegion ehcacheEntityRegion = getEhcacheEntityRegion();

		return ehcacheEntityRegion.getCacheDataDescription();
	}

	@Override
	public void invalidate() {
		EhcacheEntityRegion ehcacheEntityRegion = getEhcacheEntityRegion();

		ehcacheEntityRegion.clear();
	}

	@Override
	public boolean isTransactionAware() {
		EhcacheEntityRegion ehcacheEntityRegion = getEhcacheEntityRegion();

		return ehcacheEntityRegion.isTransactionAware();
	}

	protected EhcacheEntityRegion getEhcacheEntityRegion() {
		return (EhcacheEntityRegion)getEhcacheDataRegion();
	}

}