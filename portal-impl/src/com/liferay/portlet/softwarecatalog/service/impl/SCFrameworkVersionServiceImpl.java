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

package com.liferay.portlet.softwarecatalog.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion;
import com.liferay.portlet.softwarecatalog.service.base.SCFrameworkVersionServiceBaseImpl;
import com.liferay.portlet.softwarecatalog.service.permission.SCFrameworkVersionPermission;
import com.liferay.portlet.softwarecatalog.service.permission.SCPermission;

import java.util.List;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SCFrameworkVersionServiceImpl
	extends SCFrameworkVersionServiceBaseImpl {

	@Override
	public SCFrameworkVersion addFrameworkVersion(
			String name, String url, boolean active, int priority,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		SCPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_FRAMEWORK_VERSION);

		return scFrameworkVersionLocalService.addFrameworkVersion(
			getUserId(), name, url, active, priority, serviceContext);
	}

	@Override
	public void deleteFrameworkVersion(long frameworkVersionId)
		throws PortalException, SystemException {

		SCFrameworkVersionPermission.check(
			getPermissionChecker(), frameworkVersionId, ActionKeys.DELETE);

		scFrameworkVersionLocalService.deleteFrameworkVersion(
			frameworkVersionId);
	}

	@Override
	public SCFrameworkVersion getFrameworkVersion(long frameworkVersionId)
		throws PortalException, SystemException {

		return scFrameworkVersionLocalService.getFrameworkVersion(
			frameworkVersionId);
	}

	@Override
	public List<SCFrameworkVersion> getFrameworkVersions(
			long groupId, boolean active)
		throws SystemException {

		return scFrameworkVersionLocalService.getFrameworkVersions(
			groupId, active);
	}

	@Override
	public List<SCFrameworkVersion> getFrameworkVersions(
			long groupId, boolean active, int start, int end)
		throws SystemException {

		return scFrameworkVersionLocalService.getFrameworkVersions(
			groupId, active, start, end);
	}

	@Override
	public SCFrameworkVersion updateFrameworkVersion(
			long frameworkVersionId, String name, String url, boolean active,
			int priority)
		throws PortalException, SystemException {

		SCFrameworkVersionPermission.check(
			getPermissionChecker(), frameworkVersionId, ActionKeys.UPDATE);

		return scFrameworkVersionLocalService.updateFrameworkVersion(
			frameworkVersionId, name, url, active, priority);
	}

}