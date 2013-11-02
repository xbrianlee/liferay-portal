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

import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.model.TreeModel;
import com.liferay.portal.service.BaseLocalServiceTreeTestCase;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.util.DLAppTestUtil;

import org.junit.runner.RunWith;

/**
 * @author Shinn Lok
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class DLFolderLocalServiceTreeTestCase
	extends BaseLocalServiceTreeTestCase {

	@Override
	protected TreeModel addTreeModel(TreeModel parentTreeModel)
		throws Exception {

		long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (parentTreeModel != null) {
			DLFolder folder = (DLFolder)parentTreeModel;

			parentFolderId = folder.getFolderId();
		}

		Folder folder = DLAppTestUtil.addFolder(
			TestPropsValues.getGroupId(), parentFolderId,
			ServiceTestUtil.randomString());

		DLFolder dlFolder = DLFolderLocalServiceUtil.getFolder(
			folder.getFolderId());

		dlFolder.setTreePath(null);

		return DLFolderLocalServiceUtil.updateDLFolder(dlFolder);
	}

	@Override
	protected void deleteTreeModel(TreeModel treeModel) throws Exception {
		DLFolder folder = (DLFolder)treeModel;

		DLFolderLocalServiceUtil.deleteFolder(folder.getFolderId());
	}

	@Override
	protected TreeModel getTreeModel(long primaryKey) throws Exception {
		return DLFolderLocalServiceUtil.getFolder(primaryKey);
	}

	@Override
	protected void rebuildTree() throws Exception {
		DLFolderLocalServiceUtil.rebuildTree(TestPropsValues.getCompanyId());
	}

}