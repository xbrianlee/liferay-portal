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

import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public interface CacheKeyGenerator extends Cloneable {

	public CacheKeyGenerator append(String key);

	public CacheKeyGenerator append(String[] keys);

	public CacheKeyGenerator append(StringBundler sb);

	public CacheKeyGenerator clone();

	public Serializable finish();

	public Serializable getCacheKey(String key);

	public Serializable getCacheKey(String[] keys);

	public Serializable getCacheKey(StringBundler sb);

	public boolean isCallingGetCacheKeyThreadSafe();

}