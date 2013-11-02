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

package com.liferay.portlet.documentlibrary.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLAppTestUtil;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Transactional
public class DLFolderFinderTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			_group.getGroupId());

		_folder = DLAppLocalServiceUtil.addFolder(
			TestPropsValues.getUserId(), _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Folder A",
			StringPool.BLANK, serviceContext);

		DLAppLocalServiceUtil.addFolder(
			TestPropsValues.getUserId(), _group.getGroupId(),
			_folder.getFolderId(), "Folder B", StringPool.BLANK,
			serviceContext);

		Folder folder = DLAppLocalServiceUtil.addFolder(
			TestPropsValues.getUserId(), _group.getGroupId(),
			_folder.getFolderId(), "Folder C", StringPool.BLANK,
			serviceContext);

		DLAppServiceUtil.moveFolderToTrash(folder.getFolderId());

		FileEntry fileEntry = DLAppTestUtil.addFileEntry(
			_group.getGroupId(), _folder.getFolderId(), false, "FE1.txt",
			"FE1.txt");

		_dlFileShortcut = DLAppTestUtil.addDLFileShortcut(
			_group.getGroupId(), fileEntry);

		DLAppTestUtil.addFileEntry(
			_group.getGroupId(), _folder.getFolderId(), "FE2.pdf",
			ContentTypes.APPLICATION_PDF, "FE2.pdf");

		fileEntry = DLAppTestUtil.addFileEntry(
			_group.getGroupId(), _folder.getFolderId(), false, "FE3.txt",
			"FE3.txt");

		DLAppServiceUtil.moveFileEntryToTrash(fileEntry.getFileEntryId());
	}

	@Test
	public void testCountF_FE_FS_ByG_F_M_M() throws Exception {
		QueryDefinition queryDefinition = new QueryDefinition();

		queryDefinition.setStatus(WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(
			6,
			DLFolderFinderUtil.filterCountF_FE_FS_ByG_F_M_M(
				_group.getGroupId(), _folder.getFolderId(), null, false,
				queryDefinition));
		Assert.assertEquals(
			5,
			DLFolderFinderUtil.filterCountF_FE_FS_ByG_F_M_M(
				_group.getGroupId(), _folder.getFolderId(),
				new String[] {ContentTypes.TEXT_PLAIN}, false,
				queryDefinition));

		queryDefinition.setStatus(WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(
			4,
			DLFolderFinderUtil.filterCountF_FE_FS_ByG_F_M_M(
				_group.getGroupId(), _folder.getFolderId(), null, false,
				queryDefinition));
		Assert.assertEquals(
			2,
			DLFolderFinderUtil.filterCountF_FE_FS_ByG_F_M_M(
				_group.getGroupId(), _folder.getFolderId(),
				new String[] {ContentTypes.APPLICATION_PDF}, false,
				queryDefinition));

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH);

		Assert.assertEquals(
			2,
			DLFolderFinderUtil.filterCountF_FE_FS_ByG_F_M_M(
				_group.getGroupId(), _folder.getFolderId(), null, false,
				queryDefinition));

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH, true);

		Assert.assertEquals(
			4,
			DLFolderFinderUtil.filterCountF_FE_FS_ByG_F_M_M(
				_group.getGroupId(), _folder.getFolderId(), null, false,
				queryDefinition));
		Assert.assertEquals(
			3,
			DLFolderFinderUtil.filterCountF_FE_FS_ByG_F_M_M(
				_group.getGroupId(), _folder.getFolderId(),
				new String[] {ContentTypes.TEXT_PLAIN}, false,
				queryDefinition));
	}

	@Test
	public void testCountFE_ByG_F() throws Exception {
		QueryDefinition queryDefinition = new QueryDefinition();

		queryDefinition.setStatus(WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(
			3,
			DLFolderFinderUtil.countFE_ByG_F(
				_group.getGroupId(), _folder.getFolderId(), queryDefinition));

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH);

		Assert.assertEquals(
			1,
			DLFolderFinderUtil.countFE_ByG_F(
				_group.getGroupId(), _folder.getFolderId(), queryDefinition));

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH, true);

		Assert.assertEquals(
			2,
			DLFolderFinderUtil.countFE_ByG_F(
				_group.getGroupId(), _folder.getFolderId(), queryDefinition));
	}

	@Test
	public void testCountFE_FS_ByG_F() throws Exception {
		QueryDefinition queryDefinition = new QueryDefinition();

		queryDefinition.setStatus(WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(
			4,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F(
				_group.getGroupId(), _folder.getFolderId(), queryDefinition));

		queryDefinition.setStatus(WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(
			3,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F(
				_group.getGroupId(), _folder.getFolderId(), queryDefinition));

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH);

		Assert.assertEquals(
			1,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F(
				_group.getGroupId(), _folder.getFolderId(), queryDefinition));

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH, true);

		Assert.assertEquals(
			3,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F(
				_group.getGroupId(), _folder.getFolderId(), queryDefinition));
	}

	@Test
	public void testCountFE_FS_ByG_F_M() throws Exception {
		QueryDefinition queryDefinition = new QueryDefinition();

		queryDefinition.setStatus(WorkflowConstants.STATUS_ANY);

		Assert.assertEquals(
			4,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F_M(
				_group.getGroupId(), _folder.getFolderId(), null,
				queryDefinition));
		Assert.assertEquals(
			3,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F_M(
				_group.getGroupId(), _folder.getFolderId(),
				new String[] {ContentTypes.TEXT_PLAIN}, queryDefinition));

		queryDefinition.setStatus(WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(
			3,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F_M(
				_group.getGroupId(), _folder.getFolderId(), null,
				queryDefinition));
		Assert.assertEquals(
			1,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F_M(
				_group.getGroupId(), _folder.getFolderId(),
				new String[] {ContentTypes.APPLICATION_PDF}, queryDefinition));

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH);

		Assert.assertEquals(
			1,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F_M(
				_group.getGroupId(), _folder.getFolderId(), null,
				queryDefinition));
		Assert.assertEquals(
			1,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F_M(
				_group.getGroupId(), _folder.getFolderId(),
				new String[] {ContentTypes.TEXT_PLAIN}, queryDefinition));

		queryDefinition.setStatus(WorkflowConstants.STATUS_IN_TRASH, true);

		Assert.assertEquals(
			3,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F_M(
				_group.getGroupId(), _folder.getFolderId(), null,
				queryDefinition));
		Assert.assertEquals(
			2,
			DLFolderFinderUtil.filterCountFE_FS_ByG_F_M(
				_group.getGroupId(), _folder.getFolderId(),
				new String[] {ContentTypes.TEXT_PLAIN}, queryDefinition));
	}

	@Test
	public void testFindF_FE_FS_ByG_F_M_M() throws Exception {
		QueryDefinition queryDefinition = new QueryDefinition();

		queryDefinition.setStatus(WorkflowConstants.STATUS_APPROVED);

		List<Object> results = DLFolderFinderUtil.filterFindF_FE_FS_ByG_F_M_M(
			_group.getGroupId(), _folder.getFolderId(),
			new String[] {ContentTypes.TEXT_PLAIN}, false, queryDefinition);

		Assert.assertEquals(3, results.size());

		for (Object result : results) {
			if (result instanceof DLFileEntry) {
				DLFileEntry dlFileEntry = (DLFileEntry)result;

				Assert.assertEquals("FE1.txt", dlFileEntry.getTitle());
			}
			else if (result instanceof DLFileShortcut) {
				DLFileShortcut fileShortcut = (DLFileShortcut)result;

				Assert.assertEquals(
					_dlFileShortcut.getFileShortcutId(),
					fileShortcut.getFileShortcutId());
			}
			else if (result instanceof DLFolder) {
				DLFolder dlFolder = (DLFolder)result;

				Assert.assertEquals("Folder B", dlFolder.getName());
			}
			else {
				Assert.fail(String.valueOf(result.getClass()));
			}
		}
	}

	private DLFileShortcut _dlFileShortcut;
	private Folder _folder;
	private Group _group;

}