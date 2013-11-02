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

package com.liferay.portal.kernel.cache.key;

import com.liferay.portal.kernel.cache.Lifecycle;
import com.liferay.portal.kernel.cache.ThreadLocalCache;
import com.liferay.portal.kernel.cache.ThreadLocalCacheManager;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class CacheKeyGeneratorUtil {

	public static CacheKeyGenerator getCacheKeyGenerator() {
		return getCacheKeyGenerator(null);
	}

	public static CacheKeyGenerator getCacheKeyGenerator(String cacheName) {
		PortalRuntimePermission.checkGetBeanProperty(
			CacheKeyGeneratorUtil.class);

		ThreadLocalCache<CacheKeyGenerator> threadLocalCacheKeyGenerators =
			ThreadLocalCacheManager.getThreadLocalCache(
				Lifecycle.ETERNAL, CacheKeyGeneratorUtil.class.getName());

		CacheKeyGenerator cacheKeyGenerator = threadLocalCacheKeyGenerators.get(
			cacheName);

		if (cacheKeyGenerator != null) {
			return cacheKeyGenerator;
		}

		cacheKeyGenerator = _cacheKeyGenerators.get(cacheName);

		if (cacheKeyGenerator == null) {
			cacheKeyGenerator = _defaultCacheKeyGenerator;
		}

		cacheKeyGenerator = cacheKeyGenerator.clone();

		threadLocalCacheKeyGenerators.put(cacheName, cacheKeyGenerator);

		return cacheKeyGenerator;
	}

	public void setCacheKeyGenerators(
		Map<String, CacheKeyGenerator> cacheKeyGenerators) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_cacheKeyGenerators = cacheKeyGenerators;
	}

	public void setDefaultCacheKeyGenerator(
		CacheKeyGenerator defaultCacheKeyGenerator) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_defaultCacheKeyGenerator = defaultCacheKeyGenerator;
	}

	private static Map<String, CacheKeyGenerator> _cacheKeyGenerators =
		new HashMap<String, CacheKeyGenerator>();
	private static CacheKeyGenerator _defaultCacheKeyGenerator;

}