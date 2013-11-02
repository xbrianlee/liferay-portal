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

package com.liferay.portlet;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Shuyang Zhou
 */
public class PortalPreferencesWrapperCacheUtil {

	public static final String CACHE_NAME =
		PortalPreferencesWrapperCacheUtil.class.getName();

	public static PortalPreferencesWrapper get(long ownerId, int ownerType) {
		String cacheKey = StringUtil.toHexString(ownerId).concat(
			StringUtil.toHexString(ownerType));

		return _portalPreferencesWrapperPortalCache.get(cacheKey);
	}

	public static void put(
		long ownerId, int ownerType,
		PortalPreferencesWrapper portalPreferencesWrapper) {

		String cacheKey = StringUtil.toHexString(ownerId).concat(
			StringUtil.toHexString(ownerType));

		_portalPreferencesWrapperPortalCache.put(
			cacheKey, portalPreferencesWrapper);
	}

	public static void remove(long ownerId, int ownerType) {
		String cacheKey = StringUtil.toHexString(ownerId).concat(
			StringUtil.toHexString(ownerType));

		_portalPreferencesWrapperPortalCache.remove(cacheKey);
	}

	private static PortalCache<String, PortalPreferencesWrapper>
		_portalPreferencesWrapperPortalCache = MultiVMPoolUtil.getCache(
			CACHE_NAME);

}