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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.util.PropsValues;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

/**
 * @author Brian Wing Shun Chan
 */
public class LiferayCacheEventListenerFactory
	extends CacheEventListenerFactory {

	public LiferayCacheEventListenerFactory() {
		String className = PropsValues.EHCACHE_CACHE_EVENT_LISTENER_FACTORY;

		if (_log.isDebugEnabled()) {
			_log.debug("Instantiating " + className + " " + hashCode());
		}

		try {
			_cacheEventListenerFactory =
				(CacheEventListenerFactory)InstanceFactory.newInstance(
					className);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public CacheEventListener createCacheEventListener(Properties properties) {
		return _cacheEventListenerFactory.createCacheEventListener(properties);
	}

	private static Log _log = LogFactoryUtil.getLog(
		LiferayCacheEventListenerFactory.class);

	private CacheEventListenerFactory _cacheEventListenerFactory;

}