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

package com.liferay.portal.kernel.webdav;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.webdav.methods.MethodFactory;
import com.liferay.portal.kernel.webdav.methods.MethodFactoryRegistryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Lock;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Fabio Pezzutto
 */
public abstract class BaseWebDAVStorageImpl implements WebDAVStorage {

	@Override
	@SuppressWarnings("unused")
	public int copyCollectionResource(
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite, long depth)
		throws WebDAVException {

		return HttpServletResponse.SC_FORBIDDEN;
	}

	@Override
	@SuppressWarnings("unused")
	public int copySimpleResource(
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return HttpServletResponse.SC_FORBIDDEN;
	}

	@Override
	@SuppressWarnings("unused")
	public int deleteResource(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		return HttpServletResponse.SC_FORBIDDEN;
	}

	@Override
	public MethodFactory getMethodFactory() {
		return MethodFactoryRegistryUtil.getDefaultMethodFactory();
	}

	@Override
	public String getRootPath() {
		return _rootPath;
	}

	@Override
	public String getToken() {
		return _token;
	}

	@Override
	public boolean isAvailable(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		if (getResource(webDAVRequest) == null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean isSupportsClassTwo() {
		return false;
	}

	@Override
	@SuppressWarnings("unused")
	public Status lockResource(
			WebDAVRequest webDAVRequest, String owner, long timeout)
		throws WebDAVException {

		return null;
	}

	@Override
	@SuppressWarnings("unused")
	public Status makeCollection(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		return new Status(HttpServletResponse.SC_FORBIDDEN);
	}

	@Override
	@SuppressWarnings("unused")
	public int moveCollectionResource(
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return HttpServletResponse.SC_FORBIDDEN;
	}

	@Override
	@SuppressWarnings("unused")
	public int moveSimpleResource(
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException {

		return HttpServletResponse.SC_FORBIDDEN;
	}

	@Override
	@SuppressWarnings("unused")
	public int putResource(WebDAVRequest webDAVRequest) throws WebDAVException {
		return HttpServletResponse.SC_FORBIDDEN;
	}

	@Override
	@SuppressWarnings("unused")
	public Lock refreshResourceLock(
			WebDAVRequest webDAVRequest, String uuid, long timeout)
		throws WebDAVException {

		return null;
	}

	@Override
	public void setRootPath(String rootPath) {
		_rootPath = rootPath;
	}

	@Override
	public void setToken(String token) {
		_token = token;
	}

	@Override
	@SuppressWarnings("unused")
	public boolean unlockResource(WebDAVRequest webDAVRequest, String token)
		throws WebDAVException {

		return false;
	}

	protected long getPlid(long groupId) throws SystemException {
		return LayoutLocalServiceUtil.getDefaultPlid(groupId);
	}

	protected boolean isAddGroupPermissions(long groupId) throws Exception {
		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (!group.isUser()) {
			return true;
		}
		else {
			return false;
		}
	}

	private String _rootPath;
	private String _token;

}