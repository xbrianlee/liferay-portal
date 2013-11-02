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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.model.Repository;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;

import java.io.File;
import java.io.InputStream;

import java.util.List;

/**
 * @author Sergio González
 * @author Matthew Kong
 * @author Alexander Chow
 */
public class TempFileUtil {

	public static FileEntry addTempFile(
			long groupId, long userId, String fileName, String tempFolderName,
			File file, String mimeType)
		throws PortalException, SystemException {

		Folder folder = addTempFolder(groupId, userId, tempFolderName);

		return PortletFileRepositoryUtil.addPortletFileEntry(
			groupId, userId, StringPool.BLANK, 0, PortletKeys.DOCUMENT_LIBRARY,
			folder.getFolderId(), file, fileName, mimeType, false);
	}

	public static FileEntry addTempFile(
			long groupId, long userId, String fileName, String tempFolderName,
			InputStream inputStream, String mimeType)
		throws PortalException, SystemException {

		Folder folder = addTempFolder(groupId, userId, tempFolderName);

		return PortletFileRepositoryUtil.addPortletFileEntry(
			groupId, userId, StringPool.BLANK, 0, PortletKeys.DOCUMENT_LIBRARY,
			folder.getFolderId(), inputStream, fileName, mimeType, false);
	}

	public static void deleteTempFile(long fileEntryId)
		throws PortalException, SystemException {

		PortletFileRepositoryUtil.deletePortletFileEntry(fileEntryId);
	}

	public static void deleteTempFile(
			long groupId, long userId, String fileName, String tempFolderName)
		throws PortalException, SystemException {

		Folder folder = getTempFolder(groupId, userId, tempFolderName);

		PortletFileRepositoryUtil.deletePortletFileEntry(
			groupId, folder.getFolderId(), fileName);
	}

	public static FileEntry getTempFile(
			long groupId, long userId, String fileName, String tempFolderName)
		throws PortalException, SystemException {

		Folder folder = getTempFolder(groupId, userId, tempFolderName);

		return PortletFileRepositoryUtil.getPortletFileEntry(
			groupId, folder.getFolderId(), fileName);
	}

	public static String[] getTempFileEntryNames(
			long groupId, long userId, String tempFolderName)
		throws PortalException, SystemException {

		Folder folder = addTempFolder(groupId, userId, tempFolderName);

		List<FileEntry> fileEntries =
			PortletFileRepositoryUtil.getPortletFileEntries(
				groupId, folder.getFolderId());

		String[] fileEntryNames = new String[fileEntries.size()];

		for (int i = 0; i < fileEntries.size(); i++) {
			FileEntry fileEntry = fileEntries.get(i);

			fileEntryNames[i] = fileEntry.getTitle();
		}

		return fileEntryNames;
	}

	protected static Folder addTempFolder(
			long groupId, long userId, String tempFolderName)
		throws PortalException, SystemException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Repository repository = PortletFileRepositoryUtil.addPortletRepository(
			groupId, PortletKeys.DOCUMENT_LIBRARY, serviceContext);

		Folder userFolder = PortletFileRepositoryUtil.addPortletFolder(
			userId, repository.getRepositoryId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, String.valueOf(userId),
			serviceContext);

		Folder tempFolder = PortletFileRepositoryUtil.addPortletFolder(
			userId, repository.getRepositoryId(), userFolder.getFolderId(),
			tempFolderName, serviceContext);

		return tempFolder;
	}

	protected static Folder getTempFolder(
			long groupId, long userId, String tempFolderName)
		throws PortalException, SystemException {

		Repository repository = PortletFileRepositoryUtil.getPortletRepository(
			groupId, PortletKeys.DOCUMENT_LIBRARY);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Folder userFolder = PortletFileRepositoryUtil.getPortletFolder(
			userId, repository.getRepositoryId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, String.valueOf(userId),
			serviceContext);

		Folder tempFolder = PortletFileRepositoryUtil.getPortletFolder(
			userId, repository.getRepositoryId(), userFolder.getFolderId(),
			tempFolderName, serviceContext);

		return tempFolder;
	}

}