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

import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;
import com.liferay.portlet.network.model.DNSLookup;
import com.liferay.portlet.network.model.Whois;

/**
 * @author Brian Wing Shun Chan
 */
public class NetworkUtil {

	public static DNSLookup getDNSLookup(String domain) {
		WebCacheItem wci = new DNSLookupWebCacheItem(domain);

		return (DNSLookup)WebCachePoolUtil.get(
			NetworkUtil.class.getName() + ".dnslookup." + domain, wci);
	}

	public static Whois getWhois(String domain) {
		WebCacheItem wci = new WhoisWebCacheItem(domain);

		return (Whois)WebCachePoolUtil.get(
			NetworkUtil.class.getName() + ".whois." + domain, wci);
	}

}