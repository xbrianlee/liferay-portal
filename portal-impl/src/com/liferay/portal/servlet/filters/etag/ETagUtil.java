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

package com.liferay.portal.servlet.filters.etag;

import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.nio.ByteBuffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ETagUtil {

	public static boolean processETag(
		HttpServletRequest request, HttpServletResponse response,
		ByteBuffer byteBuffer) {

		if (response.isCommitted()) {
			return false;
		}

		int hashCode = _hashCode(
			byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());

		String eTag = StringPool.QUOTE.concat(
			StringUtil.toHexString(hashCode)).concat(StringPool.QUOTE);

		response.setHeader(HttpHeaders.ETAG, eTag);

		String ifNoneMatch = request.getHeader(HttpHeaders.IF_NONE_MATCH);

		if (eTag.equals(ifNoneMatch)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			response.setContentLength(0);

			return true;
		}
		else {
			return false;
		}
	}

	private static int _hashCode(byte[] data, int offset, int length) {
		int hashCode = 0;

		for (int i = 0; i < length; i++) {
			hashCode = 31 * hashCode + data[offset++];
		}

		return hashCode;
	}

}