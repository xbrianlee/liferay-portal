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

package com.liferay.util.servlet.filters;

import com.liferay.portal.kernel.servlet.Header;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;

import java.io.IOException;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Alexander Chow
 */
public class CacheResponseUtil {

	public static void setHeaders(
		HttpServletResponse response, Map<String, Set<Header>> headers) {

		if (response.isCommitted()) {
			return;
		}

		for (Map.Entry<String, Set<Header>> entry : headers.entrySet()) {
			String key = entry.getKey();

			boolean first = true;

			for (Header header : entry.getValue()) {
				if (first) {
					header.setToResponse(key, response);

					first = false;
				}
				else {
					header.addToResponse(key, response);
				}
			}
		}
	}

	public static void write(
			HttpServletResponse response, CacheResponseData cacheResponseData)
		throws IOException {

		setHeaders(response, cacheResponseData.getHeaders());

		response.setContentType(cacheResponseData.getContentType());

		ServletResponseUtil.write(response, cacheResponseData.getByteBuffer());
	}

}