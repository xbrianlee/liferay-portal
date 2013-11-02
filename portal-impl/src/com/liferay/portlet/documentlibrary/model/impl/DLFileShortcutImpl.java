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

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.model.Repository;
import com.liferay.portal.repository.liferayrepository.model.LiferayFolder;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileShortcutImpl extends DLFileShortcutBaseImpl {

	public DLFileShortcutImpl() {
	}

	@Override
	public String buildTreePath() throws PortalException, SystemException {
		DLFolder dlFolder = getDLFolder();

		return dlFolder.buildTreePath();
	}

	@Override
	public DLFolder getDLFolder() throws PortalException, SystemException {
		Folder folder = getFolder();

		return (DLFolder)folder.getModel();
	}

	@Override
	public Folder getFolder() throws PortalException, SystemException {
		if (getFolderId() <= 0) {
			return new LiferayFolder(new DLFolderImpl());
		}

		return DLAppLocalServiceUtil.getFolder(getFolderId());
	}

	@Override
	public String getToTitle() {
		String toTitle = null;

		try {
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
				getToFileEntryId());

			toTitle = fileEntry.getTitle();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return toTitle;
	}

	@Override
	public boolean isInHiddenFolder() {
		try {
			long repositoryId = getRepositoryId();

			Repository repository = RepositoryLocalServiceUtil.getRepository(
				repositoryId);

			long dlFolderId = repository.getDlFolderId();

			DLFolder dlFolder = DLFolderLocalServiceUtil.getFolder(dlFolderId);

			return dlFolder.isHidden();
		}
		catch (Exception e) {
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(DLFileShortcutImpl.class);

}