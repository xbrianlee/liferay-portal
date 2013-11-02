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
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
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
public class DLFileShortcutLocalServiceTreeTestCase {

	@After
	public void tearDown() throws Exception {
		for (int i = _dlFileShortcuts.size() - 1; i >= 0; i--) {
			DLFileShortcutLocalServiceUtil.deleteDLFileShortcut(
				_dlFileShortcuts.get(i));
		}

		DLAppLocalServiceUtil.deleteFileEntry(_fileEntry.getFileEntryId());
		DLAppLocalServiceUtil.deleteFolder(_folder.getFolderId());
	}

	@Test
	public void testRebuildTree() throws Exception {
		createTree();

		for (DLFileShortcut dlFileShortcut : _dlFileShortcuts) {
			dlFileShortcut.setTreePath(null);

			DLFileShortcutLocalServiceUtil.updateDLFileShortcut(dlFileShortcut);
		}

		DLFileShortcutLocalServiceUtil.rebuildTree(
			TestPropsValues.getCompanyId());

		for (DLFileShortcut dlFileShortcut : _dlFileShortcuts) {
			dlFileShortcut = DLFileShortcutLocalServiceUtil.getDLFileShortcut(
				dlFileShortcut.getFileShortcutId());

			Assert.assertEquals(
				dlFileShortcut.buildTreePath(), dlFileShortcut.getTreePath());
		}
	}

	protected void createTree() throws Exception {
		_fileEntry = DLAppTestUtil.addFileEntry(
			TestPropsValues.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Entry A.txt");

		DLFileShortcut dlFileShortcutA = DLAppTestUtil.addDLFileShortcut(
			_fileEntry, TestPropsValues.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		_dlFileShortcuts.add(dlFileShortcutA);

		_folder = DLAppTestUtil.addFolder(
			TestPropsValues.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Folder A");

		DLFileShortcut dlFileShortcutAA = DLAppTestUtil.addDLFileShortcut(
			_fileEntry, TestPropsValues.getGroupId(), _folder.getFolderId());

		_dlFileShortcuts.add(dlFileShortcutAA);
	}

	private List<DLFileShortcut> _dlFileShortcuts =
		new ArrayList<DLFileShortcut>();
	private FileEntry _fileEntry;
	private Folder _folder;

}