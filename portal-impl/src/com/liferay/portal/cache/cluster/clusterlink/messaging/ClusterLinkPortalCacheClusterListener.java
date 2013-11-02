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

package com.liferay.portal.cache.cluster.clusterlink.messaging;

import com.liferay.portal.cache.ehcache.EhcachePortalCacheManager;
import com.liferay.portal.dao.orm.hibernate.region.LiferayEhcacheRegionFactory;
import com.liferay.portal.dao.orm.hibernate.region.SingletonLiferayEhcacheRegionFactory;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.cache.cluster.PortalCacheClusterEvent;
import com.liferay.portal.kernel.cache.cluster.PortalCacheClusterEventType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import java.io.Serializable;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * @author Shuyang Zhou
 */
public class ClusterLinkPortalCacheClusterListener extends BaseMessageListener {

	public ClusterLinkPortalCacheClusterListener() {
		LiferayEhcacheRegionFactory liferayEhcacheRegionFactory =
			SingletonLiferayEhcacheRegionFactory.getInstance();

		_hibernateCacheManager = liferayEhcacheRegionFactory.getCacheManager();

		EhcachePortalCacheManager<?, ?> ehcachePortalCacheManager =
			(EhcachePortalCacheManager<?, ?>)PortalBeanLocatorUtil.locate(
				_MULTI_VM_PORTAL_CACHE_MANAGER_BEAN_NAME);

		_portalCacheManager = ehcachePortalCacheManager.getEhcacheManager();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		PortalCacheClusterEvent portalCacheClusterEvent =
			(PortalCacheClusterEvent)message.getPayload();

		if (portalCacheClusterEvent == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Payload is null");
			}

			return;
		}

		String cacheName = portalCacheClusterEvent.getCacheName();

		Ehcache ehcache = _portalCacheManager.getEhcache(cacheName);

		if ((ehcache == null) && (_hibernateCacheManager != null)) {
			ehcache = _hibernateCacheManager.getEhcache(cacheName);
		}

		if (ehcache != null) {
			PortalCacheClusterEventType portalCacheClusterEventType =
				portalCacheClusterEvent.getEventType();

			if (portalCacheClusterEventType.equals(
					PortalCacheClusterEventType.REMOVE_ALL)) {

				ehcache.removeAll(true);
			}
			else if (portalCacheClusterEventType.equals(
						PortalCacheClusterEventType.PUT) ||
					 portalCacheClusterEventType.equals(
						PortalCacheClusterEventType.UPDATE)) {

				Serializable elementKey =
					portalCacheClusterEvent.getElementKey();
				Serializable elementValue =
					portalCacheClusterEvent.getElementValue();

				if (elementValue == null) {
					ehcache.remove(elementKey, true);
				}
				else {
					ehcache.put(new Element(elementKey, elementValue), true);
				}
			}
			else {
				ehcache.remove(portalCacheClusterEvent.getElementKey(), true);
			}
		}
	}

	private static final String _MULTI_VM_PORTAL_CACHE_MANAGER_BEAN_NAME =
		"com.liferay.portal.kernel.cache.MultiVMPortalCacheManager";

	private static Log _log = LogFactoryUtil.getLog(
		ClusterLinkPortalCacheClusterListener.class);

	private CacheManager _hibernateCacheManager;
	private CacheManager _portalCacheManager;

}