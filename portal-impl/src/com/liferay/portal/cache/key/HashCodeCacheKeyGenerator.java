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
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class HashCodeCacheKeyGenerator extends BaseCacheKeyGenerator {

	@Override
	public CacheKeyGenerator clone() {
		return new HashCodeCacheKeyGenerator();
	}

	@Override
	public Long getCacheKey(String key) {
		long hashCode = 0;

		for (int i = 0; i < key.length(); i++) {
			hashCode = 31 * hashCode + key.charAt(i);
		}

		return hashCode;
	}

	@Override
	public Long getCacheKey(String[] keys) {
		long hashCode = 0;

		for (String key : keys) {
			if (key == null) {
				continue;
			}

			for (int i = 0; i < key.length(); i++) {
				hashCode = 31 * hashCode + key.charAt(i);
			}
		}

		return hashCode;
	}

	@Override
	public Long getCacheKey(StringBundler sb) {
		long hashCode = 0;

		for (int i = 0; i < sb.index(); i++) {
			String key = sb.stringAt(i);

			for (int j = 0; j < key.length(); j++) {
				hashCode = 31 * hashCode + key.charAt(j);
			}
		}

		return hashCode;
	}

}