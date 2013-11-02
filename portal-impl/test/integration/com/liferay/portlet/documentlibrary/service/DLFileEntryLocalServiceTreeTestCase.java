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

package com.liferay.portlet.documentlibrary.service;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.util.DLAppTestUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.testng.Assert;

/**
 * @author Shinn Lok
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class DLFileEntryLocalServiceTreeTestCase {

	@After
	public void tearDown() throws Exception {
		for (int i = _fileEntries.size() - 1; i >= 0; i--) {
			FileEntry fileEntry = _fileEntries.get(i);

			DLAppLocalServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());
		}

		DLAppLocalServiceUtil.deleteFolder(_folder.getFolderId());
	}

	@Test
	public void testRebuildTree() throws Exception {
		createTree();

		for (FileEntry fileEntry : _fileEntries) {
			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(
				fileEntry.getFileEntryId());

			dlFileEntry.setTreePath(null);

			DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);
		}

		DLFileEntryLocalServiceUtil.rebuildTree(TestPropsValues.getCompanyId());

		for (FileEntry fileEntry : _fileEntries) {
			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(
				fileEntry.getFileEntryId());

			Assert.assertEquals(
				dlFileEntry.buildTreePath(), dlFileEntry.getTreePath());
		}
	}

	protected void createTree() throws Exception {
		FileEntry fileEntryA = DLAppTestUtil.addFileEntry(
			TestPropsValues.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Entry A.txt");

		_fileEntries.add(fileEntryA);

		_folder = DLAppTestUtil.addFolder(
			TestPropsValues.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Folder A");

		FileEntry fileEntryAA = DLAppTestUtil.addFileEntry(
			TestPropsValues.getGroupId(), _folder.getFolderId(), "Entry A.txt");

		_fileEntries.add(fileEntryAA);
	}

	private List<FileEntry> _fileEntries = new ArrayList<FileEntry>();
	private Folder _folder;

}