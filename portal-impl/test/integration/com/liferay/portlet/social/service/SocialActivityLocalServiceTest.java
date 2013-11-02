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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityConstants;
import com.liferay.portlet.social.util.SocialActivityHierarchyEntryThreadLocal;
import com.liferay.portlet.social.util.SocialActivityTestUtil;

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
		SynchronousDestinationExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class SocialActivityLocalServiceTest extends BaseSocialActivityTestCase {

	@Test
	public void testActivityHierarchy() throws Exception {
		AssetEntry parentAssetEntry = SocialActivityTestUtil.addAssetEntry(
			_creatorUser, _group);

		SocialActivityHierarchyEntryThreadLocal.push(
			parentAssetEntry.getClassNameId(), parentAssetEntry.getClassPK());

		SocialActivityTestUtil.addActivity(
			_creatorUser, _group, _assetEntry, 1);

		List<SocialActivity> activities =
			SocialActivityLocalServiceUtil.getGroupActivities(
				_group.getGroupId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(1, activities.size());

		SocialActivity activity = activities.get(0);

		Assert.assertEquals(
			parentAssetEntry.getClassNameId(), activity.getParentClassNameId());
		Assert.assertEquals(
			parentAssetEntry.getClassPK(), activity.getParentClassPK());

		SocialActivityTestUtil.addActivity(
			_creatorUser, _group, _assetEntry,
			SocialActivityConstants.TYPE_DELETE);

		Assert.assertEquals(
			1,
			SocialActivityLocalServiceUtil.getGroupActivitiesCount(
				_group.getGroupId()));
	}

}