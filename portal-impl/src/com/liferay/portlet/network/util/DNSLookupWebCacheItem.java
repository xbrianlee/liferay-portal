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

package com.liferay.portlet.network.util;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portlet.network.model.DNSLookup;

import java.net.InetAddress;

/**
 * @author Brian Wing Shun Chan
 */
public class DNSLookupWebCacheItem implements WebCacheItem {

	public DNSLookupWebCacheItem(String domain) {
		_domain = domain;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		DNSLookup dnsLookup = null;

		try {
			String results = null;

			char[] array = _domain.trim().toCharArray();

			for (int i = 0; i < array.length; i++) {
				if ((array[i] != '.') && !Character.isDigit(array[i])) {
					InetAddress ia = InetAddress.getByName(_domain);

					results = ia.getHostAddress();

					break;
				}
			}

			if (results == null) {
				InetAddress[] ia = InetAddress.getAllByName(_domain);

				if (ia.length == 0) {
					results = StringPool.BLANK;
				}
				else {
					StringBundler sb = new StringBundler(ia.length * 2 - 1);

					for (int i = 0; i < ia.length; i++) {
						sb.append(ia[i].getHostName());

						if ((i + 1) <= ia.length) {
							sb.append(StringPool.COMMA);
						}
					}

					results = sb.toString();
				}
			}

			dnsLookup = new DNSLookup(_domain, results);
		}
		catch (Exception e) {
			throw new WebCacheException(_domain + " " + e.toString());
		}

		return dnsLookup;
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.DAY;

	private String _domain;

}