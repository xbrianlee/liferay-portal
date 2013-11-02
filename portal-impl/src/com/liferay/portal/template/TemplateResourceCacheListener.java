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

package com.liferay.portal.template;

import com.liferay.portal.kernel.cache.CacheListener;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheException;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Tina Tian
 */
public class TemplateResourceCacheListener
	implements CacheListener<String, TemplateResource> {

	public TemplateResourceCacheListener(String templateResourceLoaderName) {
		String cacheName = TemplateResource.class.getName();

		cacheName = cacheName.concat(StringPool.POUND).concat(
			templateResourceLoaderName);

		_portalCache = SingleVMPoolUtil.getCache(cacheName);
	}

	@Override
	public void notifyEntryEvicted(
			PortalCache<String, TemplateResource> portalCache, String key,
			TemplateResource templateResource)
		throws PortalCacheException {

		if (templateResource != null) {
			_portalCache.remove(templateResource);
		}
	}

	@Override
	public void notifyEntryExpired(
			PortalCache<String, TemplateResource> portalCache, String key,
			TemplateResource templateResource)
		throws PortalCacheException {

		if (templateResource != null) {
			_portalCache.remove(templateResource);
		}
	}

	@Override
	public void notifyEntryPut(
			PortalCache<String, TemplateResource> portalCache, String key,
			TemplateResource templateResource)
		throws PortalCacheException {
	}

	@Override
	public void notifyEntryRemoved(
			PortalCache<String, TemplateResource> portalCache, String key,
			TemplateResource templateResource)
		throws PortalCacheException {

		if (templateResource != null) {
			_portalCache.remove(templateResource);
		}
	}

	@Override
	public void notifyEntryUpdated(
			PortalCache<String, TemplateResource> portalCache, String key,
			TemplateResource templateResource)
		throws PortalCacheException {

		if (templateResource != null) {
			_portalCache.remove(templateResource);
		}
	}

	@Override
	public void notifyRemoveAll(
			PortalCache<String, TemplateResource> portalCache)
		throws PortalCacheException {

		_portalCache.removeAll();
	}

	private PortalCache<TemplateResource, ?> _portalCache;

}