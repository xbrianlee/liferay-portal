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

package com.liferay.portal.cache;

import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheManager;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
@DoPrivileged
public class MultiVMPoolImpl implements MultiVMPool {

	@Override
	public void clear() {
		_portalCacheManager.clearAll();
	}

	@Override
	public PortalCache<? extends Serializable, ? extends Serializable> getCache(
		String name) {

		return _portalCacheManager.getCache(name);
	}

	@Override
	public PortalCache<? extends Serializable, ? extends Serializable> getCache(
		String name, boolean blocking) {

		return _portalCacheManager.getCache(name, blocking);
	}

	@Override
	public void removeCache(String name) {
		_portalCacheManager.removeCache(name);
	}

	public void setPortalCacheManager(
		PortalCacheManager<? extends Serializable, ? extends Serializable>
			portalCacheManager) {

		_portalCacheManager = portalCacheManager;
	}

	private PortalCacheManager<? extends Serializable, ? extends Serializable>
		_portalCacheManager;

}