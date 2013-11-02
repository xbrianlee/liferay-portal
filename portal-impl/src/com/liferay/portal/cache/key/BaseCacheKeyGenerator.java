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

package com.liferay.portal.cache.key;

import com.liferay.portal.kernel.cache.key.CacheKeyGenerator;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public abstract class BaseCacheKeyGenerator implements CacheKeyGenerator {

	@Override
	public CacheKeyGenerator append(String key) {
		keyBundler.append(key);

		return this;
	}

	@Override
	public CacheKeyGenerator append(String[] keys) {
		keyBundler.append(keys);

		return this;
	}

	@Override
	public CacheKeyGenerator append(StringBundler sb) {
		keyBundler.append(sb);

		return this;
	}

	@Override
	public abstract CacheKeyGenerator clone();

	@Override
	public Serializable finish() {
		Serializable cacheKey = getCacheKey(keyBundler);

		keyBundler.setIndex(0);

		return cacheKey;
	}

	@Override
	public boolean isCallingGetCacheKeyThreadSafe() {
		return _CALLING_GET_CACHE_KEY_THREAD_SAFE;
	}

	protected StringBundler keyBundler = new StringBundler();

	private static final boolean _CALLING_GET_CACHE_KEY_THREAD_SAFE = true;

}