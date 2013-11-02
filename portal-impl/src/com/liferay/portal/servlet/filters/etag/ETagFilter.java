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

import com.liferay.portal.kernel.servlet.RestrictedByteBufferCacheServletResponse;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.PropsValues;

import java.nio.ByteBuffer;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class ETagFilter extends BasePortalFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		if (ParamUtil.getBoolean(request, _ETAG, true)) {
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isEligibleForEtag(int status) {
		if ((status >= HttpServletResponse.SC_OK) &&
			(status < HttpServletResponse.SC_MULTIPLE_CHOICES)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		RestrictedByteBufferCacheServletResponse
			restrictedByteBufferCacheServletResponse =
				new RestrictedByteBufferCacheServletResponse(
					response, PropsValues.ETAG_RESPONSE_SIZE_MAX);

		processFilter(
			ETagFilter.class, request, restrictedByteBufferCacheServletResponse,
			filterChain);

		if (!restrictedByteBufferCacheServletResponse.isOverflowed()) {
			ByteBuffer byteBuffer =
				restrictedByteBufferCacheServletResponse.getByteBuffer();

			if (!isEligibleForEtag(
					restrictedByteBufferCacheServletResponse.getStatus()) ||
				!ETagUtil.processETag(request, response, byteBuffer)) {

				restrictedByteBufferCacheServletResponse.finishResponse();

				restrictedByteBufferCacheServletResponse.flushCache();
			}
		}
	}

	private static final String _ETAG = "etag";

}