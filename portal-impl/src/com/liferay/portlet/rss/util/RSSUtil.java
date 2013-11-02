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

package com.liferay.portlet.rss.util;

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import com.sun.syndication.feed.synd.SyndFeed;

/**
 * @author Brian Wing Shun Chan
 */
public class RSSUtil {

	public static ObjectValuePair<String, SyndFeed> getFeed(String url) {
		WebCacheItem wci = new RSSWebCacheItem(url);

		return new ObjectValuePair<String, SyndFeed>(
			url,
			(SyndFeed)WebCachePoolUtil.get(
				RSSUtil.class.getName() + StringPool.PERIOD + url, wci));
	}

}