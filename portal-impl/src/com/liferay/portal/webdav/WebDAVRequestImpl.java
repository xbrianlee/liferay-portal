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

package com.liferay.portal.webdav;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVStorage;
import com.liferay.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class WebDAVRequestImpl implements WebDAVRequest {

	public WebDAVRequestImpl(
			WebDAVStorage storage, HttpServletRequest request,
			HttpServletResponse response, String userAgent,
			PermissionChecker permissionChecker)
		throws WebDAVException {

		_storage = storage;
		_request = request;
		_response = response;
		_userAgent = userAgent;
		_lockUuid = WebDAVUtil.getLockUuid(request);

		String pathInfo = HttpUtil.fixPath(_request.getPathInfo(), false, true);

		String strippedPathInfo = WebDAVUtil.stripManualCheckInRequiredPath(
			pathInfo);

		if (strippedPathInfo.length() != pathInfo.length()) {
			pathInfo = strippedPathInfo;

			_manualCheckInRequired = true;
		}

		_path = WebDAVUtil.stripOfficeExtension(pathInfo);

		_companyId = PortalUtil.getCompanyId(request);
		_groupId = WebDAVUtil.getGroupId(_companyId, _path);
		_userId = GetterUtil.getLong(_request.getRemoteUser());
		_permissionChecker = permissionChecker;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public HttpServletRequest getHttpServletRequest() {
		return _request;
	}

	@Override
	public HttpServletResponse getHttpServletResponse() {
		return _response;
	}

	@Override
	public String getLockUuid() {
		return _lockUuid;
	}

	@Override
	public String getPath() {
		return _path;
	}

	@Override
	public String[] getPathArray() {
		return WebDAVUtil.getPathArray(_path);
	}

	@Override
	public PermissionChecker getPermissionChecker() {
		return _permissionChecker;
	}

	@Override
	public String getRootPath() {
		return _storage.getRootPath();
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public WebDAVStorage getWebDAVStorage() {
		return _storage;
	}

	@Override
	public boolean isAppleDoubleRequest() {
		String[] pathArray = getPathArray();

		String name = WebDAVUtil.getResourceName(pathArray);

		if (isMac() && name.startsWith(_APPLE_DOUBLE_PREFIX)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isLitmus() {
		return _userAgent.contains("litmus");
	}

	@Override
	public boolean isMac() {
		return _userAgent.contains("WebDAVFS");
	}

	@Override
	public boolean isManualCheckInRequired() {
		return _manualCheckInRequired;
	}

	@Override
	public boolean isWindows() {
		return _userAgent.contains(
			"Microsoft Data Access Internet Publishing Provider");
	}

	private static final String _APPLE_DOUBLE_PREFIX = "._";

	private long _companyId;
	private long _groupId;
	private String _lockUuid;
	private boolean _manualCheckInRequired;
	private String _path = StringPool.BLANK;
	private PermissionChecker _permissionChecker;
	private HttpServletRequest _request;
	private HttpServletResponse _response;
	private WebDAVStorage _storage;
	private String _userAgent;
	private long _userId;

}