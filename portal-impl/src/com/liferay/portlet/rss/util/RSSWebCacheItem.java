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

import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.security.lang.DoPrivilegedBean;
import com.liferay.portal.util.HttpImpl;
import com.liferay.portal.util.PropsValues;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

/**
 * @author Brian Wing Shun Chan
 */
public class RSSWebCacheItem implements WebCacheItem {

	public RSSWebCacheItem(String url) {
		_url = url;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		SyndFeed feed = null;

		try {

			// com.liferay.portal.kernel.util.HttpUtil will break the connection
			// if it spends more than 5 seconds looking up a location. However,
			// German umlauts do not get encoded correctly. This may be a bug
			// with commons-httpclient or with how FeedParser uses
			// java.io.Reader.

			// Use http://xml.newsisfree.com/feeds/29/629.xml and
			// http://test.domosoft.com/up/RSS to test if German umlauts show up
			// correctly.

			/*Reader reader = new StringReader(
				new String(HttpUtil.URLtoByteArray(_url)));

			channel = FeedParser.parse(builder, reader);*/

			HttpImpl httpImpl = null;

			Object httpObject = HttpUtil.getHttp();

			if (httpObject instanceof DoPrivilegedBean) {
				DoPrivilegedBean doPrivilegedBean =
					(DoPrivilegedBean)httpObject;

				httpImpl = (HttpImpl)doPrivilegedBean.getActualBean();
			}
			else {
				httpImpl = (HttpImpl)httpObject;
			}

			HostConfiguration hostConfiguration = httpImpl.getHostConfiguration(
				_url);

			HttpClient httpClient = httpImpl.getClient(hostConfiguration);

			httpImpl.proxifyState(httpClient.getState(), hostConfiguration);

			HttpClientParams httpClientParams = httpClient.getParams();

			httpClientParams.setConnectionManagerTimeout(
				PropsValues.RSS_CONNECTION_TIMEOUT);
			httpClientParams.setSoTimeout(PropsValues.RSS_CONNECTION_TIMEOUT);

			GetMethod getMethod = new GetMethod(
				httpImpl.encodeParameters(_url));

			httpClient.executeMethod(hostConfiguration, getMethod);

			SyndFeedInput input = new SyndFeedInput();

			feed = input.build(
				new XmlReader(getMethod.getResponseBodyAsStream()));
		}
		catch (Exception e) {
			throw new WebCacheException(_url + " " + e.toString());
		}

		return feed;
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 20;

	private String _url;

}