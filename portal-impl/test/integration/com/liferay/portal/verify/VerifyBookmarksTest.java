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

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalServiceUtil;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.portlet.bookmarks.util.BookmarksTestUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 * @author Eudaldo Alonso
 * @author Sergio González
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class VerifyBookmarksTest extends BaseVerifyTestCase {

	@Test
	@Transactional
	public void testBookmarksEntryTreePathWithBookmarksEntryInTrash()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		BookmarksFolder parentFolder = BookmarksTestUtil.addFolder(
			group.getGroupId(), ServiceTestUtil.randomString());

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			group.getGroupId());

		BookmarksEntry entry = BookmarksTestUtil.addEntry(
			parentFolder.getFolderId(), true, serviceContext);

		BookmarksEntryLocalServiceUtil.moveEntryToTrash(
			TestPropsValues.getUserId(), entry.getEntryId());

		BookmarksFolderLocalServiceUtil.deleteFolder(
			parentFolder.getFolderId(), false);

		doVerify();
	}

	@Test
	@Transactional
	public void testBookmarksEntryTreePathWithBookmarksParentFolderInTrash()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		BookmarksFolder grandparentFolder = BookmarksTestUtil.addFolder(
			group.getGroupId(), ServiceTestUtil.randomString());

		BookmarksFolder parentFolder = BookmarksTestUtil.addFolder(
			group.getGroupId(), grandparentFolder.getFolderId(),
			ServiceTestUtil.randomString());

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			group.getGroupId());

		BookmarksTestUtil.addEntry(
			parentFolder.getFolderId(), true, serviceContext);

		BookmarksFolderLocalServiceUtil.moveFolderToTrash(
			TestPropsValues.getUserId(), parentFolder.getFolderId());

		BookmarksFolderLocalServiceUtil.deleteFolder(
			grandparentFolder.getFolderId(), false);

		doVerify();
	}

	@Test
	@Transactional
	public void testBookmarksFolderTreePathWithBookmarksFolderInTrash()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		BookmarksFolder parentFolder = BookmarksTestUtil.addFolder(
			group.getGroupId(), ServiceTestUtil.randomString());

		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			group.getGroupId(), parentFolder.getFolderId(),
			ServiceTestUtil.randomString());

		BookmarksFolderLocalServiceUtil.moveFolderToTrash(
			TestPropsValues.getUserId(), folder.getFolderId());

		BookmarksFolderLocalServiceUtil.deleteFolder(
			parentFolder.getFolderId(), false);

		doVerify();
	}

	@Test
	@Transactional
	public void testBookmarksFolderTreePathWithBookmarksParentFolderInTrash()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		BookmarksFolder grandparentFolder = BookmarksTestUtil.addFolder(
			group.getGroupId(), ServiceTestUtil.randomString());

		BookmarksFolder parentFolder = BookmarksTestUtil.addFolder(
			group.getGroupId(), grandparentFolder.getFolderId(),
			ServiceTestUtil.randomString());

		BookmarksTestUtil.addFolder(
			group.getGroupId(), parentFolder.getFolderId(),
			ServiceTestUtil.randomString());

		BookmarksFolderLocalServiceUtil.moveFolderToTrash(
			TestPropsValues.getUserId(), parentFolder.getFolderId());

		BookmarksFolderLocalServiceUtil.deleteFolder(
			grandparentFolder.getFolderId(), false);

		doVerify();
	}

	@Override
	protected VerifyProcess getVerifyProcess() {
		return new VerifyBookmarks();
	}

}