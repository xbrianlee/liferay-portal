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

package com.liferay.portlet.documentlibrary.lar;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.repository.liferayrepository.model.LiferayFolder;
import com.liferay.portal.repository.liferayrepository.util.LiferayBase;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.persistence.DLFolderUtil;

import java.util.List;

/**
 * @author Alexander Chow
 */
public class FolderUtil extends LiferayBase {

	public static Folder fetchByR_P_N(
			long groupId, long parentFolderId, String name)
		throws SystemException {

		DLFolder dlFolder = DLFolderUtil.fetchByG_P_N(
			groupId, parentFolderId, name);

		if (dlFolder == null) {
			return null;
		}

		return new LiferayFolder(dlFolder);
	}

	public static Folder fetchByUUID_R(String uuid, long repositoryId)
		throws SystemException {

		DLFolder dlFolder = DLFolderUtil.fetchByUUID_G(uuid, repositoryId);

		if (dlFolder == null) {
			return null;
		}

		return new LiferayFolder(dlFolder);
	}

	public static Folder findByPrimaryKey(long folderId)
		throws NoSuchFolderException, SystemException {

		DLFolder dlFolder = DLFolderUtil.findByPrimaryKey(folderId);

		return new LiferayFolder(dlFolder);
	}

	public static List<Folder> findByR_P(long repositoryId, long parentFolderId)
		throws SystemException {

		List<DLFolder> dlFolders = DLFolderUtil.findByG_P(
			repositoryId, parentFolderId);

		return _instance.toFolders(dlFolders);
	}

	public static List<Folder> findByRepositoryId(long repositoryId)
		throws SystemException {

		List<DLFolder> dlFolders = DLFolderUtil.findByGroupId(repositoryId);

		return _instance.toFolders(dlFolders);
	}

	private static FolderUtil _instance = new FolderUtil();

}