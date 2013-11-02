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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.servlet.SharedSessionServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class SessionLayoutClone implements LayoutClone {

	@Override
	public String get(HttpServletRequest request, long plid) {
		HttpSession session = getPortalSession(request);

		return (String)session.getAttribute(encodeKey(plid));
	}

	@Override
	public void update(
		HttpServletRequest request, long plid, String typeSettings) {

		HttpSession session = getPortalSession(request);

		session.setAttribute(encodeKey(plid), typeSettings);
	}

	protected String encodeKey(long plid) {
		return SessionLayoutClone.class.getName().concat(
			StringPool.POUND).concat(StringUtil.toHexString(plid));
	}

	protected HttpSession getPortalSession(HttpServletRequest request) {
		HttpServletRequest originalRequest = request;

		while (originalRequest instanceof HttpServletRequestWrapper) {
			if (originalRequest instanceof SharedSessionServletRequest) {
				SharedSessionServletRequest sharedSessionServletRequest =
					(SharedSessionServletRequest)originalRequest;

				return sharedSessionServletRequest.getSharedSession();
			}

			originalRequest = (HttpServletRequest)
				((HttpServletRequestWrapper)originalRequest).getRequest();
		}

		return request.getSession();
	}

}