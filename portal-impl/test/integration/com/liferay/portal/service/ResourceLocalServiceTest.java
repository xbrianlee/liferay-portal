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

import com.liferay.portal.NoSuchResourceException;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.DoAsUserThread;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portal.util.UserTestUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Brian Wing Shun Chan
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class ResourceLocalServiceTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_userIds = new long[ServiceTestUtil.THREAD_COUNT];

		for (int i = 0; i < ServiceTestUtil.THREAD_COUNT; i++) {
			User user = UserTestUtil.addUser(
				"ResourceLocalServiceTest" + (i + 1), _group.getGroupId());

			_userIds[i] = user.getUserId();
		}
	}

	@After
	public void tearDown() throws Exception {
		GroupLocalServiceUtil.deleteGroup(_group);

		for (int i = 0; i < ServiceTestUtil.THREAD_COUNT; i++) {
			UserLocalServiceUtil.deleteUser(_userIds[i]);
		}
	}

	@Test
	public void testAddResourcesConcurrently() throws Exception {
		DoAsUserThread[] doAsUserThreads =
			new DoAsUserThread[ServiceTestUtil.THREAD_COUNT];

		for (int i = 0; i < doAsUserThreads.length; i++) {
			doAsUserThreads[i] = new AddResources(_userIds[i]);
		}

		for (DoAsUserThread doAsUserThread : doAsUserThreads) {
			doAsUserThread.start();
		}

		for (DoAsUserThread doAsUserThread : doAsUserThreads) {
			doAsUserThread.join();
		}

		int successCount = 0;

		for (DoAsUserThread doAsUserThread : doAsUserThreads) {
			if (doAsUserThread.isSuccess()) {
				successCount++;
			}
		}

		Assert.assertTrue(
			"Only " + successCount + " out of " + ServiceTestUtil.THREAD_COUNT +
				" threads added resources successfully",
			successCount == ServiceTestUtil.THREAD_COUNT);
	}

	private Group _group;
	private long[] _userIds;

	private class AddResources extends DoAsUserThread {

		public AddResources(long userId) {
			super(userId);
		}

		@Override
		public boolean isSuccess() {
			return true;
		}

		@Override
		protected void doRun() throws Exception {
			try {
				ResourceLocalServiceUtil.getResource(
					TestPropsValues.getCompanyId(), Layout.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(
						TestPropsValues.getPlid(_group.getGroupId())));
			}
			catch (NoSuchResourceException nsre) {
				boolean addGroupPermission = true;
				boolean addGuestPermission = true;

				ResourceLocalServiceUtil.addResources(
					TestPropsValues.getCompanyId(), _group.getGroupId(), 0,
					Layout.class.getName(),
					TestPropsValues.getPlid(_group.getGroupId()), false,
					addGroupPermission, addGuestPermission);
			}
		}

	}

}