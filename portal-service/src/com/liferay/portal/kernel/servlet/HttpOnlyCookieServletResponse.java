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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author Samuel Kong
 */
public class HttpOnlyCookieServletResponse extends HttpServletResponseWrapper {

	public static HttpServletResponse getHttpOnlyCookieServletResponse(
		HttpServletResponse response) {

		HttpServletResponse wrappedResponse = response;

		while (wrappedResponse instanceof HttpServletResponseWrapper) {
			if (wrappedResponse instanceof HttpOnlyCookieServletResponse) {
				return response;
			}

			HttpServletResponseWrapper httpServletResponseWrapper =
				(HttpServletResponseWrapper)wrappedResponse;

			wrappedResponse =
				(HttpServletResponse)httpServletResponseWrapper.getResponse();
		}

		return new HttpOnlyCookieServletResponse(response);
	}

	public HttpOnlyCookieServletResponse(HttpServletResponse response) {
		super(response);
	}

	@Override
	public void addCookie(Cookie cookie) {
		if (!_cookieHttpOnlyCookieNamesExcludes.contains(cookie.getName())) {
			cookie.setHttpOnly(true);
		}

		super.addCookie(cookie);
	}

	private static Set<String> _cookieHttpOnlyCookieNamesExcludes =
		SetUtil.fromArray(
			PropsUtil.getArray(PropsKeys.COOKIE_HTTP_ONLY_NAMES_EXCLUDES));

}