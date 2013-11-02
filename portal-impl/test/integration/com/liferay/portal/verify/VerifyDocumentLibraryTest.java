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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLAppTestUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 * @author Eudaldo Alonso
 * @author Sergio González
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class VerifyDocumentLibraryTest extends BaseVerifyTestCase {

	@Test
	@Transactional
	public void testDLFileEntryTreePathWithDLFileEntryInTrash()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Folder parentFolder = DLAppTestUtil.addFolder(
			group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			ServiceTestUtil.randomString());

		FileEntry fileEntry = DLAppTestUtil.addFileEntry(
			group.getGroupId(), parentFolder.getFolderId(), false,
			ServiceTestUtil.randomString());

		DLAppServiceUtil.moveFileEntryToTrash(fileEntry.getFileEntryId());

		DLFolderLocalServiceUtil.deleteFolder(
			parentFolder.getFolderId(), false);

		doVerify();
	}

	@Test
	@Transactional
	public void testDLFileEntryTreePathWithParentDLFolderInTrash()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Folder grandparentFolder = DLAppTestUtil.addFolder(
			group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			ServiceTestUtil.randomString());

		Folder parentFolder = DLAppTestUtil.addFolder(
			group.getGroupId(), grandparentFolder.getFolderId(),
			ServiceTestUtil.randomString());

		DLAppTestUtil.addFileEntry(
			group.getGroupId(), parentFolder.getFolderId(), false,
			ServiceTestUtil.randomString());

		DLAppServiceUtil.moveFolderToTrash(parentFolder.getFolderId());

		DLFolderLocalServiceUtil.deleteFolder(
			grandparentFolder.getFolderId(), false);

		doVerify();
	}

	@Test
	@Transactional
	public void testDLFileShortcutTreePathWithDLFileShortcutInTrash()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Folder parentFolder = DLAppTestUtil.addFolder(
			group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			ServiceTestUtil.randomString());

		FileEntry fileEntry = DLAppTestUtil.addFileEntry(
			group.getGroupId(), parentFolder.getFolderId(), false,
			ServiceTestUtil.randomString());

		DLFileShortcut dlFileShortcut = DLAppTestUtil.addDLFileShortcut(
			fileEntry, group.getGroupId(), parentFolder.getFolderId());

		DLAppServiceUtil.moveFileShortcutToTrash(
			dlFileShortcut.getFileShortcutId());

		DLFolderLocalServiceUtil.deleteFolder(
			parentFolder.getFolderId(), false);

		doVerify();
	}

	@Test
	@Transactional
	public void testDLFileShortcutTreePathWithParentDLFolderInTrash()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Folder grandparentFolder = DLAppTestUtil.addFolder(
			group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			ServiceTestUtil.randomString());

		Folder parentFolder = DLAppTestUtil.addFolder(
			group.getGroupId(), grandparentFolder.getFolderId(),
			ServiceTestUtil.randomString());

		DLAppTestUtil.addFileEntry(
			group.getGroupId(), parentFolder.getFolderId(), false,
			ServiceTestUtil.randomString());

		DLAppServiceUtil.moveFolderToTrash(parentFolder.getFolderId());

		DLFolderLocalServiceUtil.deleteFolder(
			grandparentFolder.getFolderId(), false);

		doVerify();
	}

	@Test
	@Transactional
	public void testDLFolderTreePathWithDLFolderInTrash() throws Exception {
		Group group = GroupTestUtil.addGroup();

		Folder parentFolder = DLAppTestUtil.addFolder(
			group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			ServiceTestUtil.randomString());

		Folder folder = DLAppTestUtil.addFolder(
			group.getGroupId(), parentFolder.getFolderId(),
			ServiceTestUtil.randomString());

		DLAppServiceUtil.moveFolderToTrash(folder.getFolderId());

		DLFolderLocalServiceUtil.deleteFolder(
			parentFolder.getFolderId(), false);

		doVerify();
	}

	@Test
	@Transactional
	public void testDLFolderTreePathWithParentDLFolderInTrash()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Folder grandparentFolder = DLAppTestUtil.addFolder(
			group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			ServiceTestUtil.randomString());

		Folder parentFolder = DLAppTestUtil.addFolder(
			group.getGroupId(), grandparentFolder.getFolderId(),
			ServiceTestUtil.randomString());

		DLAppTestUtil.addFolder(
			group.getGroupId(), parentFolder.getFolderId(),
			ServiceTestUtil.randomString());

		DLAppServiceUtil.moveFolderToTrash(parentFolder.getFolderId());

		DLFolderLocalServiceUtil.deleteFolder(
			grandparentFolder.getFolderId(), false);

		doVerify();
	}

	@Override
	protected VerifyProcess getVerifyProcess() {
		return new VerifyDocumentLibrary();
	}

}