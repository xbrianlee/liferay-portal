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

package com.liferay.portlet.bookmarks.service;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.util.BookmarksTestUtil;

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
public class BookmarksEntryLocalServiceTreeTestCase {

	@After
	public void tearDown() throws Exception {
		for (int i = _entries.size() - 1; i >= 0; i--) {
			BookmarksEntryLocalServiceUtil.deleteBookmarksEntry(
				_entries.get(i));
		}

		BookmarksFolderLocalServiceUtil.deleteBookmarksFolder(_folder);
	}

	@Test
	public void testRebuildTree() throws Exception {
		createTree();

		for (BookmarksEntry entry : _entries) {
			entry.setTreePath(null);

			BookmarksEntryLocalServiceUtil.updateBookmarksEntry(entry);
		}

		BookmarksEntryLocalServiceUtil.rebuildTree(
			TestPropsValues.getCompanyId());

		for (BookmarksEntry entry : _entries) {
			entry = BookmarksEntryLocalServiceUtil.getEntry(entry.getEntryId());

			Assert.assertEquals(entry.buildTreePath(), entry.getTreePath());
		}
	}

	protected void createTree() throws Exception {
		BookmarksEntry entryA = BookmarksTestUtil.addEntry(true);

		_entries.add(entryA);

		_folder = BookmarksTestUtil.addFolder("Folder A");

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			TestPropsValues.getGroupId());

		BookmarksEntry entryAA = BookmarksTestUtil.addEntry(
			_folder.getFolderId(), true, serviceContext);

		_entries.add(entryAA);
	}

	private List<BookmarksEntry> _entries = new ArrayList<BookmarksEntry>();
	private BookmarksFolder _folder;

}