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

import com.liferay.client.soap.portal.kernel.repository.model.FileEntrySoap;
import com.liferay.client.soap.portal.kernel.repository.model.FolderSoap;
import com.liferay.client.soap.portal.service.ServiceContext;
import com.liferay.client.soap.portlet.documentlibrary.service.http.DLAppServiceSoap;
import com.liferay.client.soap.portlet.documentlibrary.service.http.DLAppServiceSoapServiceLocator;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
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
public class DLAppServiceSoapTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		String name = "Test Folder";
		String description = "This is a test folder.";

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(_group.getGroupId());

		try {
			getDLAppServiceSoap().deleteFolder(
				_group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				name);
		}
		catch (Exception e) {
		}

		_folderSoap = getDLAppServiceSoap().addFolder(
			_group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			name, description, serviceContext);
	}

	@After
	public void tearDown() throws Exception {
		try {
			if (_folderSoap != null) {
				getDLAppServiceSoap().deleteFolder(_folderSoap.getFolderId());
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
		FileEntrySoap fileEntry = addFileEntry("Test Delete.txt");

		getDLAppServiceSoap().deleteFileEntry(fileEntry.getFileEntryId());
	}

	@Test
	public void testGetFileEntry() throws Exception {
		FileEntrySoap fileEntry = addFileEntry("Test Get.txt");

		getDLAppServiceSoap().getFileEntryByUuidAndGroupId(
			fileEntry.getUuid(), fileEntry.getGroupId());
	}

	protected FileEntrySoap addFileEntry(String title) throws Exception {
		long folderId = _folderSoap.getFolderId();
		String description = StringPool.BLANK;
		String changeLog = StringPool.BLANK;
		byte[] bytes = _CONTENT.getBytes();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(_group.getGroupId());

		return getDLAppServiceSoap().addFileEntry(
			_group.getGroupId(), folderId, title, ContentTypes.TEXT_PLAIN,
			title, description, changeLog, bytes, serviceContext);
	}

	protected DLAppServiceSoap getDLAppServiceSoap() throws Exception {
		DLAppServiceSoapServiceLocator dlAppServiceSoapServiceLocator =
			new DLAppServiceSoapServiceLocator();

		return dlAppServiceSoapServiceLocator.getPortlet_DL_DLAppService(
			TestPropsValues.getSoapURL(
				dlAppServiceSoapServiceLocator.
					getPortlet_DL_DLAppServiceWSDDServiceName()));
	}

	private static final String _CONTENT =
		"Content: Enterprise. Open Source. For Life.";

	private FolderSoap _folderSoap;
	private Group _group;

}