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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;

import java.util.Properties;

import org.hibernate.cache.CacheDataDescription;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CollectionRegion;
import org.hibernate.cache.EntityRegion;
import org.hibernate.cache.QueryResultsRegion;
import org.hibernate.cache.RegionFactory;
import org.hibernate.cache.TimestampsRegion;
import org.hibernate.cache.access.AccessType;
import org.hibernate.cfg.Settings;

/**
 * @author Edward Han
 * @author Shuyang Zhou
 */
public class SingletonLiferayEhcacheRegionFactory implements RegionFactory {

	public static LiferayEhcacheRegionFactory getInstance() {
		return _liferayEhcacheRegionFactory;
	}

	public SingletonLiferayEhcacheRegionFactory(Properties properties) {
		synchronized (this) {
			boolean useQueryCache = GetterUtil.getBoolean(
				properties.get(PropsKeys.HIBERNATE_CACHE_USE_QUERY_CACHE));
			boolean useSecondLevelCache = GetterUtil.getBoolean(
				properties.get(
					PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));

			if (useQueryCache || useSecondLevelCache) {
				_enabled = true;
			}

			if (_liferayEhcacheRegionFactory == null) {
				_liferayEhcacheRegionFactory = new LiferayEhcacheRegionFactory(
					properties);
			}
		}
	}

	@Override
	public CollectionRegion buildCollectionRegion(
			String regionName, Properties properties,
			CacheDataDescription cacheDataDescription)
		throws CacheException {

		return _liferayEhcacheRegionFactory.buildCollectionRegion(
			regionName, properties, cacheDataDescription);
	}

	@Override
	public EntityRegion buildEntityRegion(
			String regionName, Properties properties,
			CacheDataDescription cacheDataDescription)
		throws CacheException {

		return _liferayEhcacheRegionFactory.buildEntityRegion(
			regionName, properties, cacheDataDescription);
	}

	@Override
	public QueryResultsRegion buildQueryResultsRegion(
			String regionName, Properties properties)
		throws CacheException {

		return _liferayEhcacheRegionFactory.buildQueryResultsRegion(
			regionName, properties);
	}

	@Override
	public TimestampsRegion buildTimestampsRegion(
			String regionName, Properties properties)
		throws CacheException {

		return _liferayEhcacheRegionFactory.buildTimestampsRegion(
			regionName, properties);
	}

	@Override
	public AccessType getDefaultAccessType() {
		return _liferayEhcacheRegionFactory.getDefaultAccessType();
	}

	@Override
	public boolean isMinimalPutsEnabledByDefault() {
		return _liferayEhcacheRegionFactory.isMinimalPutsEnabledByDefault();
	}

	@Override
	public long nextTimestamp() {
		return _liferayEhcacheRegionFactory.nextTimestamp();
	}

	@Override
	public synchronized void start(Settings settings, Properties properties)
		throws CacheException {

		if (_enabled && (_instanceCounter++ == 0)) {
			_liferayEhcacheRegionFactory.start(settings, properties);
		}
	}

	@Override
	public synchronized void stop() {
		if (_enabled && (--_instanceCounter == 0)) {
			_liferayEhcacheRegionFactory.stop();
		}
	}

	private static boolean _enabled;
	private static int _instanceCounter;
	private static LiferayEhcacheRegionFactory _liferayEhcacheRegionFactory;

}