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

package com.liferay.portal.servlet.filters.compoundsessionid;

import com.liferay.portal.kernel.servlet.WrapHttpServletRequestFilter;
import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdServletRequest;
import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdSplitterUtil;
import com.liferay.portal.servlet.filters.BasePortalFilter;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-18587.
 * </p>
 *
 * @author Michael C. Han
 */
public class CompoundSessionIdFilter
	extends BasePortalFilter implements WrapHttpServletRequestFilter {

	@Override
	public HttpServletRequest getWrappedHttpServletRequest(
		HttpServletRequest request, HttpServletResponse response) {

		HttpServletRequest wrappedRequest = request;

		while (wrappedRequest instanceof HttpServletRequestWrapper) {
			if (wrappedRequest instanceof CompoundSessionIdServletRequest) {
				return request;
			}

			HttpServletRequestWrapper httpServletRequestWrapper =
				(HttpServletRequestWrapper)wrappedRequest;

			wrappedRequest =
				(HttpServletRequest)httpServletRequestWrapper.getRequest();
		}

		return new CompoundSessionIdServletRequest(request);
	}

	@Override
	public void init(FilterConfig filterConfig) {
		super.init(filterConfig);

		if (CompoundSessionIdSplitterUtil.hasSessionDelimiter()) {
			_filterEnabled = true;
		}
		else {
			_filterEnabled = false;
		}
	}

	@Override
	public boolean isFilterEnabled() {
		return _filterEnabled;
	}

	private static boolean _filterEnabled;

}