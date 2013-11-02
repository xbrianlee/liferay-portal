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

package com.liferay.portal.kernel.cache;

/**
 * @author Brian Wing Shun Chan
 */
public interface CacheRegistry {

	public void clear();

	public void clear(String name);

	public boolean isActive();

	public void register(CacheRegistryItem cacheRegistryItem);

	public void setActive(boolean active);

	public void unregister(String name);

}