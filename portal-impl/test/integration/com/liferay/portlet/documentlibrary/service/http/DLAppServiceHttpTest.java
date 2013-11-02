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

package com.liferay.portlet.documentlibrary.service.http;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alexander Chow
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class DLAppServiceHttpTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		String name = "Test Folder";
		String description = "This is a test folder.";

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			_group.getGroupId());

		try {
			DLAppServiceHttp.deleteFolder(
				TestPropsValues.getHttpPrincipal(), _group.getGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, name);
		}
		catch (Exception e) {
		}

		_folder = DLAppServiceHttp.addFolder(
			TestPropsValues.getHttpPrincipal(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, name, description,
			serviceContext);
	}

	@After
	public void tearDown() throws Exception {
		try {
			if (_folder != null) {
				DLAppServiceHttp.deleteFolder(
					TestPropsValues.getHttpPrincipal(), _folder.getFolderId());
			}
		}
		catch (Exception e) {
		}
	}

	@Test
	public void testAddFileEntry() throws Exception {
		addFileEntry("Test Add.txt");
	}

	@Test
	public void testDeleteFileEntry() throws Exception {
		FileEntry fileEntry = addFileEntry("Test Delete.txt");

		DLAppServiceHttp.deleteFileEntry(
			TestPropsValues.getHttpPrincipal(), fileEntry.getFileEntryId());
	}

	@Test
	public void testGetFileEntry() throws Exception {
		FileEntry fileEntry = addFileEntry("Test Get.txt");

		DLAppServiceHttp.getFileEntryByUuidAndGroupId(
			TestPropsValues.getHttpPrincipal(), fileEntry.getUuid(),
			fileEntry.getGroupId());
	}

	protected FileEntry addFileEntry(String title) throws Exception {
		long folderId = _folder.getFolderId();
		String description = StringPool.BLANK;
		String changeLog = StringPool.BLANK;
		byte[] bytes = _CONTENT.getBytes();

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			_group.getGroupId());

		return DLAppServiceHttp.addFileEntry(
			TestPropsValues.getHttpPrincipal(), _group.getGroupId(), folderId,
			title, ContentTypes.TEXT_PLAIN, title, description, changeLog,
			bytes, serviceContext);
	}

	private static final String _CONTENT =
		"Content: Enterprise. Open Source. For Life.";

	private Folder _folder;
	private Group _group;

}