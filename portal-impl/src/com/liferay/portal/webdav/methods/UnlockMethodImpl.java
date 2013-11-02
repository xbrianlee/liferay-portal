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

package com.liferay.portal.webdav.methods;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVStorage;
import com.liferay.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.kernel.webdav.methods.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alexander Chow
 */
public class UnlockMethodImpl implements Method {

	@Override
	public int process(WebDAVRequest webDAVRequest) throws WebDAVException {
		WebDAVStorage storage = webDAVRequest.getWebDAVStorage();

		String token = getToken(webDAVRequest.getHttpServletRequest());

		if (!storage.isSupportsClassTwo()) {
			return HttpServletResponse.SC_METHOD_NOT_ALLOWED;
		}

		if (storage.unlockResource(webDAVRequest, token)) {
			return HttpServletResponse.SC_NO_CONTENT;
		}
		else {
			return HttpServletResponse.SC_PRECONDITION_FAILED;
		}
	}

	protected String getToken(HttpServletRequest request) {
		String token = StringPool.BLANK;

		String value = GetterUtil.getString(request.getHeader("Lock-Token"));

		if (_log.isDebugEnabled()) {
			_log.debug("\"Lock-Token\" header is " + value);
		}

		if (value.startsWith("<") && value.endsWith(">")) {
			value = value.substring(1, value.length() - 1);
		}

		int index = value.indexOf(WebDAVUtil.TOKEN_PREFIX);

		if (index >= 0) {
			index += WebDAVUtil.TOKEN_PREFIX.length();

			if (index < value.length()) {
				token = GetterUtil.getString(value.substring(index));
			}
		}

		return token;
	}

	private static Log _log = LogFactoryUtil.getLog(UnlockMethodImpl.class);

}