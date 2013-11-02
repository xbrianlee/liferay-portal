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
import com.liferay.portal.model.TreeModel;
import com.liferay.portal.service.BaseLocalServiceTreeTestCase;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.model.BookmarksFolderConstants;
import com.liferay.portlet.bookmarks.util.BookmarksTestUtil;

import org.junit.runner.RunWith;

/**
 * @author Shinn Lok
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class BookmarksFolderLocalServiceTreeTestCase
	extends BaseLocalServiceTreeTestCase {

	@Override
	protected TreeModel addTreeModel(TreeModel parentTreeModel)
		throws Exception {

		long parentFolderId = BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (parentTreeModel != null) {
			BookmarksFolder folder = (BookmarksFolder)parentTreeModel;

			parentFolderId = folder.getFolderId();
		}

		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			TestPropsValues.getGroupId(), parentFolderId,
			ServiceTestUtil.randomString());

		folder.setTreePath(null);

		return BookmarksFolderLocalServiceUtil.updateBookmarksFolder(folder);
	}

	@Override
	protected void deleteTreeModel(TreeModel treeModel) throws Exception {
		BookmarksFolder folder = (BookmarksFolder)treeModel;

		BookmarksFolderLocalServiceUtil.deleteFolder(folder);
	}

	@Override
	protected TreeModel getTreeModel(long primaryKey) throws Exception {
		return BookmarksFolderLocalServiceUtil.getFolder(primaryKey);
	}

	@Override
	protected void rebuildTree() throws Exception {
		BookmarksFolderLocalServiceUtil.rebuildTree(
			TestPropsValues.getCompanyId());
	}

}