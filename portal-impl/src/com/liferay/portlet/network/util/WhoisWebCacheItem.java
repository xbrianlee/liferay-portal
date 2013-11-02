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

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portlet.network.model.Whois;

import java.io.InputStreamReader;
import java.io.PrintStream;

import java.net.Socket;

/**
 * @author Brian Wing Shun Chan
 */
public class WhoisWebCacheItem implements WebCacheItem {

	public static final String WHOIS_SERVER = "whois.geektools.com";

	public static final int WHOIS_SERVER_PORT = 43;

	public WhoisWebCacheItem(String domain) {
		_domain = domain;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		Whois whois = null;

		try {
			Socket socket = new Socket(WHOIS_SERVER, WHOIS_SERVER_PORT);

			UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(
					new InputStreamReader(socket.getInputStream()));

			PrintStream out = new PrintStream(socket.getOutputStream());

			out.println(_domain);

			StringBundler sb = new StringBundler();
			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				if (line.startsWith("Results ")) {
					break;
				}

				sb.append(line).append("\n");
			}

			unsyncBufferedReader.close();
			socket.close();

			whois = new Whois(
				_domain,
				StringUtil.replace(sb.toString().trim(), "\n\n", "\n"));
		}
		catch (Exception e) {
			throw new WebCacheException(_domain + " " + e.toString());
		}

		return whois;
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.DAY;

	private String _domain;

}