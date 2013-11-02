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

package com.liferay.portal.webcache;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePool;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class WebCachePoolImpl implements WebCachePool {

	public void afterPropertiesSet() {
		_portalCache = (PortalCache<String, Object>)_singleVMPool.getCache(
			_CACHE_NAME);
	}

	@Override
	public void clear() {
		_portalCache.removeAll();
	}

	@Override
	public Object get(String key, WebCacheItem wci) {
		Object obj = _portalCache.get(key);

		if (obj != null) {
			return obj;
		}

		try {
			obj = wci.convert(key);

			int timeToLive = (int)(wci.getRefreshTime() / Time.SECOND);

			_portalCache.put(key, obj, timeToLive);
		}
		catch (WebCacheException wce) {
			if (_log.isWarnEnabled()) {
				Throwable cause = wce.getCause();

				if (cause != null) {
					_log.warn(cause, cause);
				}
				else {
					_log.warn(wce, wce);
				}
			}
		}

		return obj;
	}

	@Override
	public void remove(String key) {
		_portalCache.remove(key);
	}

	public void setSingleVMPool(SingleVMPool singleVMPool) {
		_singleVMPool = singleVMPool;
	}

	private static final String _CACHE_NAME = WebCachePool.class.getName();

	private static Log _log = LogFactoryUtil.getLog(WebCachePoolImpl.class);

	private PortalCache<String, Object> _portalCache;
	private SingleVMPool _singleVMPool;

}