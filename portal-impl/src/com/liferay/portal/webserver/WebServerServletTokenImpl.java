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

package com.liferay.portal.webserver;

import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.servlet.filters.cache.CacheUtil;
import com.liferay.portlet.journalcontent.util.JournalContentUtil;

/**
 * @author Brian Wing Shun Chan
 * @since  6.1, replaced com.liferay.portal.servlet.ImageServletTokenImpl
 */
@DoPrivileged
public class WebServerServletTokenImpl implements WebServerServletToken {

	public void afterPropertiesSet() {
		_portalCache = (PortalCache<Long, String>)_multiVMPool.getCache(
			_CACHE_NAME);
	}

	@Override
	public String getToken(long imageId) {
		Long key = imageId;

		String token = _portalCache.get(key);

		if (token == null) {
			token = _createToken(imageId);

			_portalCache.put(key, token);
		}

		return token;
	}

	@Override
	public void resetToken(long imageId) {
		_portalCache.remove(imageId);

		// Journal content

		JournalContentUtil.clearCache();

		// Layout cache

		CacheUtil.clearCache();
	}

	public void setMultiVMPool(MultiVMPool multiVMPool) {
		_multiVMPool = multiVMPool;
	}

	private String _createToken(long imageId) {
		return String.valueOf(System.currentTimeMillis());
	}

	private static final String _CACHE_NAME =
		WebServerServletToken.class.getName();

	private MultiVMPool _multiVMPool;
	private PortalCache<Long, String> _portalCache;

}