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
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.TreeModel;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.OrganizationTestUtil;
import com.liferay.portal.util.TestPropsValues;

import org.junit.runner.RunWith;

/**
 * @author Shinn Lok
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class OrganizationLocalServiceTreeTestCase
	extends BaseLocalServiceTreeTestCase {

	@Override
	protected TreeModel addTreeModel(TreeModel parentTreeModel)
		throws Exception {

		long parentOrganizationId =
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID;

		if (parentTreeModel != null) {
			Organization organization = (Organization)parentTreeModel;

			parentOrganizationId = organization.getOrganizationId();
		}

		Organization organization = OrganizationTestUtil.addOrganization(
			parentOrganizationId, ServiceTestUtil.randomString(), false);

		organization.setTreePath(null);

		return OrganizationLocalServiceUtil.updateOrganization(organization);
	}

	@Override
	protected void deleteTreeModel(TreeModel treeModel) throws Exception {
		Organization organization = (Organization)treeModel;

		OrganizationLocalServiceUtil.deleteOrganization(organization);
	}

	@Override
	protected TreeModel getTreeModel(long primaryKey) throws Exception {
		return OrganizationLocalServiceUtil.getOrganization(primaryKey);
	}

	@Override
	protected void rebuildTree() throws Exception {
		OrganizationLocalServiceUtil.rebuildTree(
			TestPropsValues.getCompanyId());
	}

}