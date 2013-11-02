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

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.methods.Method;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class OptionsMethodImpl implements Method {

	@Override
	public int process(WebDAVRequest webDAVRequest) {
		HttpServletResponse response = webDAVRequest.getHttpServletResponse();

		if (webDAVRequest.getWebDAVStorage().isSupportsClassTwo()) {
			response.addHeader("DAV", "1,2");
		}
		else {
			response.addHeader("DAV", "1");
		}

		response.addHeader(
			"Allow", StringUtil.merge(Method.SUPPORTED_METHOD_NAMES));
		response.addHeader("MS-Author-Via", "DAV");

		return HttpServletResponse.SC_OK;
	}

}