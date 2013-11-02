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

package com.liferay.taglib.util;

import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;

/**
 * @author Brian Wing Shun Chan
 */
public class GetUrlWebCacheItem implements WebCacheItem {

	public GetUrlWebCacheItem(String url, long refreshTime) {
		_url = url;
		_refreshTime = refreshTime;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		String url = _url;

		String content = null;

		try {
			content = HttpUtil.URLtoString(_url);
		}
		catch (Exception e) {
			throw new WebCacheException(url + " " + e.toString());
		}

		return content;
	}

	@Override
	public long getRefreshTime() {
		return _refreshTime;
	}

	private long _refreshTime;
	private String _url;

}