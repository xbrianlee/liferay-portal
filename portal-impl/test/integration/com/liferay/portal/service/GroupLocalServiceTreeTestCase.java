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

package com.liferay.portal.service;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.TreeModel;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.TestPropsValues;

import org.junit.runner.RunWith;

/**
 * @author Shinn Lok
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)

public class GroupLocalServiceTreeTestCase
	extends BaseLocalServiceTreeTestCase {

	@Override
	protected TreeModel addTreeModel(TreeModel parentTreeModel)
		throws Exception {

		long parentGroupId = GroupConstants.DEFAULT_PARENT_GROUP_ID;

		if (parentTreeModel != null) {
			Group group = (Group)parentTreeModel;

			parentGroupId = group.getGroupId();
		}

		Group group = GroupTestUtil.addGroup(
			parentGroupId, ServiceTestUtil.randomString());

		group.setTreePath(null);

		return GroupLocalServiceUtil.updateGroup(group);
	}

	@Override
	protected void deleteTreeModel(TreeModel treeModel) throws Exception {
		Group group = (Group)treeModel;

		GroupLocalServiceUtil.deleteGroup(group);
	}

	@Override
	protected TreeModel getTreeModel(long primaryKey) throws Exception {
		return GroupLocalServiceUtil.getGroup(primaryKey);
	}

	@Override
	protected void rebuildTree() throws Exception {
		GroupLocalServiceUtil.rebuildTree(TestPropsValues.getCompanyId());
	}

}