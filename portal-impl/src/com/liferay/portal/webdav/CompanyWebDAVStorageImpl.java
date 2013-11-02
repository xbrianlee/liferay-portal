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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webdav.BaseResourceImpl;
import com.liferay.portal.kernel.webdav.BaseWebDAVStorageImpl;
import com.liferay.portal.kernel.webdav.Resource;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Chow
 */
public class CompanyWebDAVStorageImpl extends BaseWebDAVStorageImpl {

	@Override
	public Resource getResource(WebDAVRequest webDAVRequest) {
		String path = getRootPath() + webDAVRequest.getPath();

		return new BaseResourceImpl(path, StringPool.BLANK, StringPool.BLANK);
	}

	@Override
	public List<Resource> getResources(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		try {
			long userId = webDAVRequest.getUserId();

			return getResources(userId);
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	protected List<Resource> getResources(long userId) throws Exception {
		User user = UserLocalServiceUtil.getUserById(userId);

		List<Group> groups = WebDAVUtil.getGroups(user);

		List<Resource> resources = new ArrayList<Resource>(groups.size());

		for (Group group : groups) {
			String parentPath = getRootPath();

			String name = group.getFriendlyURL();

			name = name.substring(1);

			resources.add(new BaseResourceImpl(parentPath, name, name));
		}

		return resources;
	}

}