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

package com.liferay.portlet.documentlibrary.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.base.DLFileVersionServiceBaseImpl;
import com.liferay.portlet.documentlibrary.service.permission.DLFileEntryPermission;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileVersionServiceImpl extends DLFileVersionServiceBaseImpl {

	@Override
	public DLFileVersion getFileVersion(long fileVersionId)
		throws PortalException, SystemException {

		DLFileVersion fileVersion = dlFileVersionLocalService.getFileVersion(
			fileVersionId);

		DLFileEntryPermission.check(
			getPermissionChecker(), fileVersion.getFileEntryId(),
			ActionKeys.VIEW);

		return fileVersion;
	}

	@Override
	public List<DLFileVersion> getFileVersions(long fileEntryId, int status)
		throws PortalException, SystemException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.VIEW);

		return dlFileVersionLocalService.getFileVersions(fileEntryId, status);
	}

	@Override
	public int getFileVersionsCount(long fileEntryId, int status)
		throws PortalException, SystemException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.VIEW);

		return dlFileVersionPersistence.countByF_S(fileEntryId, status);
	}

	@Override
	public DLFileVersion getLatestFileVersion(long fileEntryId)
		throws PortalException, SystemException {

		DLFileEntryPermission.check(
			getPermissionChecker(), fileEntryId, ActionKeys.VIEW);

		return dlFileVersionLocalService.getLatestFileVersion(
			getGuestOrUserId(), fileEntryId);
	}

}