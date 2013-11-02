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

package com.liferay.portal.portletfilerepository;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Repository;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.File;
import java.io.InputStream;

import java.util.List;

/**
 * @author Eudaldo Alonso
 * @author Alexander Chow
 */
public interface PortletFileRepository {

	public void addPortletFileEntries(
			long groupId, long userId, String className, long classPK,
			String portletId, long folderId,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs)
		throws PortalException, SystemException;

	public FileEntry addPortletFileEntry(
			long groupId, long userId, String className, long classPK,
			String portletId, long folderId, File file, String fileName,
			String mimeType, boolean indexingEnabled)
		throws PortalException, SystemException;

	public FileEntry addPortletFileEntry(
			long groupId, long userId, String className, long classPK,
			String portletId, long folderId, InputStream inputStream,
			String fileName, String mimeType, boolean indexingEnabled)
		throws PortalException, SystemException;

	public Folder addPortletFolder(
			long userId, long repositoryId, long parentFolderId,
			String folderName, ServiceContext serviceContext)
		throws PortalException, SystemException;

	public Repository addPortletRepository(
			long groupId, String portletId, ServiceContext serviceContext)
		throws PortalException, SystemException;

	public void deleteFolder(long folderId)
		throws PortalException, SystemException;

	public void deletePortletFileEntries(long groupId, long folderId)
		throws PortalException, SystemException;

	public void deletePortletFileEntries(
			long groupId, long folderId, int status)
		throws PortalException, SystemException;

	public void deletePortletFileEntry(long fileEntryId)
		throws PortalException, SystemException;

	public void deletePortletFileEntry(
			long groupId, long folderId, String fileName)
		throws PortalException, SystemException;

	public void deletePortletRepository(long groupId, String portletId)
		throws PortalException, SystemException;

	public Repository fetchPortletRepository(long groupId, String portletId)
		throws SystemException;

	public List<FileEntry> getPortletFileEntries(long groupId, long folderId)
		throws SystemException;

	public List<FileEntry> getPortletFileEntries(
			long groupId, long folderId, int status)
		throws SystemException;

	public List<FileEntry> getPortletFileEntries(
			long groupId, long folderId, int status, int start, int end,
			OrderByComparator obc)
		throws SystemException;

	public int getPortletFileEntriesCount(long groupId, long folderId)
		throws SystemException;

	public int getPortletFileEntriesCount(
			long groupId, long folderId, int status)
		throws SystemException;

	public FileEntry getPortletFileEntry(long fileEntryId)
		throws PortalException, SystemException;

	public FileEntry getPortletFileEntry(
			long groupId, long folderId, String fileName)
		throws PortalException, SystemException;

	public FileEntry getPortletFileEntry(String uuid, long groupId)
		throws PortalException, SystemException;

	public String getPortletFileEntryURL(
		ThemeDisplay themeDisplay, FileEntry fileEntry, String queryString);

	public String getPortletFileEntryURL(
		ThemeDisplay themeDisplay, FileEntry fileEntry, String queryString,
		boolean absoluteURL);

	public Folder getPortletFolder(long folderId)
		throws PortalException, SystemException;

	public Folder getPortletFolder(
			long repositoryId, long parentFolderId, String folderName)
		throws PortalException, SystemException;

	public Repository getPortletRepository(long groupId, String portletId)
		throws PortalException, SystemException;

	public FileEntry movePortletFileEntryToTrash(long userId, long fileEntryId)
		throws PortalException, SystemException;

	public FileEntry movePortletFileEntryToTrash(
			long groupId, long userId, long folderId, String fileName)
		throws PortalException, SystemException;

	public void restorePortletFileEntryFromTrash(long userId, long fileEntryId)
		throws PortalException, SystemException;

	public void restorePortletFileEntryFromTrash(
			long groupId, long userId, long folderId, String fileName)
		throws PortalException, SystemException;

}