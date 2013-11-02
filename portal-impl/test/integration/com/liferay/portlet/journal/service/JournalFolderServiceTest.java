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

package com.liferay.portlet.journal.service;

import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalFolder;
import com.liferay.portlet.journal.util.JournalTestUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Juan Fernández
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
@Transactional
public class JournalFolderServiceTest {

	@Before
	public void setUp() {
		FinderCacheUtil.clearCache();
	}

	@Test
	public void testContent() throws Exception {
		Group group = GroupTestUtil.addGroup();

		JournalFolder folder = JournalTestUtil.addFolder(
			group.getGroupId(), 0, "Test Folder");

		JournalArticle article = JournalTestUtil.addArticle(
			group.getGroupId(), folder.getFolderId(), "Test Article",
			"This is a test article.");

		Assert.assertEquals(article.getFolderId(), folder.getFolderId());

		JournalFolderLocalServiceUtil.deleteFolder(folder);
		GroupLocalServiceUtil.deleteGroup(group);
	}

	@Test
	@Transactional
	public void testSubfolders() throws Exception {
		Group group = GroupTestUtil.addGroup();

		JournalFolder folder1 = JournalTestUtil.addFolder(
			group.getGroupId(), 0, "Test 1");

		JournalFolder folder11 = JournalTestUtil.addFolder(
			group.getGroupId(), folder1.getFolderId(), "Test 1.1");

		JournalFolder folder111 = JournalTestUtil.addFolder(
			group.getGroupId(), folder11.getFolderId(), "Test 1.1.1");

		Assert.assertTrue(folder1.isRoot());
		Assert.assertFalse(folder11.isRoot());
		Assert.assertFalse(folder111.isRoot());

		Assert.assertEquals(
			folder1.getFolderId(), folder11.getParentFolderId());

		Assert.assertEquals(
			folder11.getFolderId(), folder111.getParentFolderId());
	}

}