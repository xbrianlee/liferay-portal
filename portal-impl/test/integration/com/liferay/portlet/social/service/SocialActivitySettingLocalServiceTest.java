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

package com.liferay.portlet.social.service;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.SocialActivityDefinition;
import com.liferay.portlet.social.util.SocialConfigurationUtil;

import java.util.List;

import org.junit.Assert;
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
public class SocialActivitySettingLocalServiceTest
	extends BaseSocialActivityTestCase {

	@Test
	public void testGetActivityDefinition() throws Exception {
		SocialActivitySettingLocalServiceUtil.updateActivitySetting(
			_group.getGroupId(), TEST_MODEL, true);

		SocialActivityDefinition defaultActivityDefinition =
			SocialConfigurationUtil.getActivityDefinition(TEST_MODEL, 1);

		SocialActivityDefinition activityDefinition =
			SocialActivitySettingLocalServiceUtil.getActivityDefinition(
				_group.getGroupId(), TEST_MODEL, 1);

		Assert.assertEquals(defaultActivityDefinition, activityDefinition);

		List<SocialActivityDefinition> defaultActivityDefinitions =
			SocialConfigurationUtil.getActivityDefinitions(TEST_MODEL);

		Assert.assertNotNull(defaultActivityDefinitions);
		Assert.assertFalse(defaultActivityDefinitions.isEmpty());

		List<SocialActivityDefinition> activityDefinitions =
			SocialActivitySettingLocalServiceUtil.getActivityDefinitions(
				_group.getGroupId(), TEST_MODEL);

		Assert.assertNotNull(activityDefinitions);
		Assert.assertFalse(activityDefinitions.isEmpty());
		Assert.assertEquals(
			defaultActivityDefinitions.size(), activityDefinitions.size());
		Assert.assertTrue(
			activityDefinitions.contains(defaultActivityDefinition));
	}

	@Test
	@Transactional
	public void testUpdateActivitySettings() throws Exception {
		SocialActivitySettingLocalServiceUtil.updateActivitySetting(
			_group.getGroupId(), TEST_MODEL, true);

		long classNameId = PortalUtil.getClassNameId(TEST_MODEL);

		Assert.assertTrue(
			SocialActivitySettingLocalServiceUtil.isEnabled(
				_group.getGroupId(), classNameId));

		SocialActivitySettingLocalServiceUtil.updateActivitySetting(
			_group.getGroupId(), TEST_MODEL, false);

		Assert.assertFalse(
			SocialActivitySettingLocalServiceUtil.isEnabled(
				_group.getGroupId(), classNameId));
		Assert.assertTrue(
			SocialActivitySettingLocalServiceUtil.isEnabled(
				_group.getGroupId(), classNameId, 1));

		SocialActivitySettingLocalServiceUtil.updateActivitySetting(
			_group.getGroupId(), TEST_MODEL, 1, false);

		Assert.assertFalse(
			SocialActivitySettingLocalServiceUtil.isEnabled(
				_group.getGroupId(), classNameId, 1));
	}

}