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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.StringUtil;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Igor Spasic
 */
public class URLUtil {

	/**
	 * @see {@link
	 *      com.liferay.portal.kernel.process.ClassPathUtil#_buildClassPath(
	 *      ClassLoader, String)}
	 */
	public static URL normalizeURL(URL url) throws MalformedURLException {
		String urlString = url.toString();

		if (urlString.startsWith("vfsfile:")) {
			urlString = StringUtil.replaceFirst(urlString, "vfsfile:", "file:");
		}
		else if (urlString.startsWith("vfsjar:")) {
			urlString = StringUtil.replaceFirst(urlString, "vfsjar:", "file:");
		}
		else if (urlString.startsWith("vfszip:")) {
			urlString = StringUtil.replaceFirst(urlString, "vfszip:", "file:");
		}

		if (urlString.contains(".jar/")) {
			urlString = StringUtil.replaceFirst(urlString, ".jar/", ".jar!/");

			if (urlString.startsWith("file:")) {
				urlString = "jar:" + urlString;
			}
		}

		urlString = urlString.replace('\\', '/');

		int index = urlString.indexOf("file:");

		if (index != -1) {
			index += 5;

			if (urlString.charAt(index) != '/') {
				urlString =
					urlString.substring(0, index) + '/' +
						urlString.substring(index);
			}
		}

		return new URL(urlString);
	}

}