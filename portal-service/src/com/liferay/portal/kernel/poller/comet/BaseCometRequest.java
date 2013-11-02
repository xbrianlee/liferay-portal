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

package com.liferay.portal.kernel.poller.comet;

import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public abstract class BaseCometRequest implements CometRequest {

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public String getPathInfo() {
		return _pathInfo;
	}

	@Override
	public HttpServletRequest getRequest() {
		return _request;
	}

	@Override
	public long getTimestamp() {
		return _timestamp;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public void setPathInfo(String pathInfo) {
		_pathInfo = pathInfo;
	}

	public void setRequest(HttpServletRequest request) {
		setCompanyId(PortalUtil.getCompanyId(request));
		setPathInfo(request.getPathInfo());
		setUserId(PortalUtil.getUserId(request));
	}

	@Override
	public void setTimestamp(long timestamp) {
		_timestamp = timestamp;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	private long _companyId;
	private String _pathInfo;
	private HttpServletRequest _request;
	private long _timestamp = System.currentTimeMillis();
	private long _userId;

}