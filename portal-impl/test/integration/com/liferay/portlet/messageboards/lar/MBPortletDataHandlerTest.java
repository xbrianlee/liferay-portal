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

package com.liferay.portlet.messageboards.lar;

import com.liferay.portal.kernel.lar.PortletDataHandler;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.lar.BasePortletDataHandlerTestCase;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.util.MBTestUtil;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.testng.Assert;

/**
 * @author Zsolt Berentey
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class MBPortletDataHandlerTest extends BasePortletDataHandlerTestCase {

	@Test
	public void testDeleteAllFolders() throws Exception {
		Group group = GroupTestUtil.addGroup();

		MBCategory parentCategory = MBTestUtil.addCategory(group.getGroupId());

		MBCategory childCategory = MBTestUtil.addCategory(
			group.getGroupId(), parentCategory.getCategoryId());

		MBCategoryLocalServiceUtil.moveCategoryToTrash(
			TestPropsValues.getUserId(), childCategory.getCategoryId());

		MBCategoryLocalServiceUtil.moveCategoryToTrash(
			TestPropsValues.getUserId(), parentCategory.getCategoryId());

		MBCategoryLocalServiceUtil.deleteCategory(parentCategory, false);

		GroupLocalServiceUtil.deleteGroup(group);

		List<MBCategory> categories = MBCategoryLocalServiceUtil.getCategories(
			group.getGroupId());

		Assert.assertEquals(0, categories.size());
	}

	@Override
	protected void addParameters(Map<String, String[]> parameterMap) {
		addBooleanParameter(
			parameterMap, MBPortletDataHandler.NAMESPACE, "messages", true);
		addBooleanParameter(
			parameterMap, MBPortletDataHandler.NAMESPACE, "thread-flags", true);
		addBooleanParameter(
			parameterMap, MBPortletDataHandler.NAMESPACE, "user-bans", true);
	}

	@Override
	protected void addStagedModels() throws Exception {
		MBCategory category = MBTestUtil.addCategory(stagingGroup.getGroupId());

		MBMessage message = MBTestUtil.addMessageWithWorkflow(
			stagingGroup.getGroupId(), category.getCategoryId(), true);

		MBTestUtil.addThreadFlag(
			stagingGroup.getGroupId(), message.getThread());

		MBTestUtil.addBan(stagingGroup.getGroupId());
	}

	@Override
	protected PortletDataHandler createPortletDataHandler() {
		return new MBPortletDataHandler();
	}

	@Override
	protected String getPortletId() {
		return PortletKeys.MESSAGE_BOARDS;
	}

}