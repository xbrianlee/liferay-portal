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

import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.TreePathUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.documentlibrary.NoSuchFileVersionException;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.impl.DLFileVersionModelImpl;
import com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl;
import com.liferay.portlet.documentlibrary.service.base.DLFileVersionLocalServiceBaseImpl;
import com.liferay.portlet.documentlibrary.util.comparator.FileVersionVersionComparator;

import java.util.Collections;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileVersionLocalServiceImpl
	extends DLFileVersionLocalServiceBaseImpl {

	@Override
	public DLFileVersion getFileVersion(long fileVersionId)
		throws PortalException, SystemException {

		return dlFileVersionPersistence.findByPrimaryKey(fileVersionId);
	}

	@Override
	public DLFileVersion getFileVersion(long fileEntryId, String version)
		throws PortalException, SystemException {

		return dlFileVersionPersistence.findByF_V(fileEntryId, version);
	}

	@Override
	public DLFileVersion getFileVersionByUuidAndGroupId(
			String uuid, long groupId)
		throws SystemException {

		return dlFileVersionPersistence.fetchByUUID_G(uuid, groupId);
	}

	@Override
	public List<DLFileVersion> getFileVersions(long fileEntryId, int status)
		throws SystemException {

		List<DLFileVersion> dlFileVersions = null;

		if (status == WorkflowConstants.STATUS_ANY) {
			dlFileVersions = dlFileVersionPersistence.findByFileEntryId(
				fileEntryId);
		}
		else {
			dlFileVersions = dlFileVersionPersistence.findByF_S(
				fileEntryId, status);
		}

		dlFileVersions = ListUtil.copy(dlFileVersions);

		Collections.sort(dlFileVersions, new FileVersionVersionComparator());

		return dlFileVersions;
	}

	@Override
	public int getFileVersionsCount(long fileEntryId, int status)
		throws SystemException {

		return dlFileVersionPersistence.countByF_S(fileEntryId, status);
	}

	@Override
	public DLFileVersion getLatestFileVersion(
			long fileEntryId, boolean excludeWorkingCopy)
		throws PortalException, SystemException {

		List<DLFileVersion> dlFileVersions =
			dlFileVersionPersistence.findByFileEntryId(fileEntryId);

		if (dlFileVersions.isEmpty()) {
			throw new NoSuchFileVersionException(
				"No file versions found for fileEntryId " + fileEntryId);
		}

		dlFileVersions = ListUtil.copy(dlFileVersions);

		Collections.sort(dlFileVersions, new FileVersionVersionComparator());

		DLFileVersion dlFileVersion = dlFileVersions.get(0);

		String version = dlFileVersion.getVersion();

		if (excludeWorkingCopy &&
			version.equals(DLFileEntryConstants.PRIVATE_WORKING_COPY_VERSION)) {

			return dlFileVersions.get(1);
		}

		return dlFileVersion;
	}

	@Override
	public DLFileVersion getLatestFileVersion(long userId, long fileEntryId)
		throws PortalException, SystemException {

		boolean excludeWorkingCopy = true;

		if (dlFileEntryLocalService.isFileEntryCheckedOut(fileEntryId)) {
			excludeWorkingCopy = !dlFileEntryLocalService.hasFileEntryLock(
				userId, fileEntryId);
		}

		return getLatestFileVersion(fileEntryId, excludeWorkingCopy);
	}

	@Override
	public void rebuildTree(long companyId) throws SystemException {
		dlFolderLocalService.rebuildTree(companyId);

		Session session = dlFileVersionPersistence.openSession();

		try {
			TreePathUtil.rebuildTree(
				session, companyId, DLFileVersionModelImpl.TABLE_NAME,
				DLFolderModelImpl.TABLE_NAME, "folderId", true);
		}
		finally {
			dlFileVersionPersistence.closeSession(session);

			dlFileVersionPersistence.clearCache();
		}
	}

}