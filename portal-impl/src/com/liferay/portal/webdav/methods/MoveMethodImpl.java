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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.webdav.Resource;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVStorage;
import com.liferay.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.kernel.webdav.methods.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class MoveMethodImpl implements Method {

	@Override
	public int process(WebDAVRequest webDAVRequest) throws WebDAVException {
		WebDAVStorage storage = webDAVRequest.getWebDAVStorage();
		HttpServletRequest request = webDAVRequest.getHttpServletRequest();

		long companyId = webDAVRequest.getCompanyId();
		String destination = WebDAVUtil.getDestination(
			request, storage.getRootPath());

		StringBundler sb = new StringBundler();

		if (_log.isInfoEnabled()) {
			sb.append("Destination is ");
			sb.append(destination);
		}

		if (!destination.equals(webDAVRequest.getPath()) &&
			(WebDAVUtil.getGroupId(companyId, destination) ==
				webDAVRequest.getGroupId())) {

			Resource resource = storage.getResource(webDAVRequest);

			if (resource == null) {
				return HttpServletResponse.SC_NOT_FOUND;
			}

			boolean overwrite = WebDAVUtil.isOverwrite(request);

			if (_log.isInfoEnabled()) {
				sb.append(", overwrite is ");
				sb.append(overwrite);

				_log.info(sb.toString());
			}

			if (resource.isCollection()) {
				return storage.moveCollectionResource(
					webDAVRequest, resource, destination, overwrite);
			}
			else {
				return storage.moveSimpleResource(
					webDAVRequest, resource, destination, overwrite);
			}
		}

		return HttpServletResponse.SC_FORBIDDEN;
	}

	private static Log _log = LogFactoryUtil.getLog(MoveMethodImpl.class);

}