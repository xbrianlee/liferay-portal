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

/**
 * @author Shuyang Zhou
 */
public class SimpleCacheKeyGenerator extends BaseCacheKeyGenerator {

	@Override
	public CacheKeyGenerator clone() {
		return new SimpleCacheKeyGenerator();
	}

	@Override
	public String getCacheKey(String key) {
		return key;
	}

	@Override
	public String getCacheKey(String[] keys) {
		StringBundler sb = new StringBundler(keys);

		return sb.toString();
	}

	@Override
	public String getCacheKey(StringBundler sb) {
		return sb.toString();
	}

}