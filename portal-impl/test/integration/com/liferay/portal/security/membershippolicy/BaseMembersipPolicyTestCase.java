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

package com.liferay.portal.security.membershippolicy;

import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.UserTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * @author Roberto Díaz
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Transactional
public abstract class BaseMembersipPolicyTestCase {

	public static long[] getUserIds() {
		return _userIds;
	}

	public static boolean isPropagateMembership() {
		return _propagateMembership;
	}

	public static boolean isPropagateRoles() {
		return _propagateRoles;
	}

	public static boolean isVerify() {
		return _verify;
	}

	public static void setPropagateMembership(boolean propagateMembership) {
		_propagateMembership = propagateMembership;
	}

	public static void setPropagateRoles(boolean propagateRoles) {
		_propagateRoles = propagateRoles;
	}

	public static void setVerify(boolean verify) {
		_verify = verify;
	}

	@Before
	public void setUp() throws Exception {
		FinderCacheUtil.clearCache();

		group = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws Exception {
		group = null;

		_propagateMembership = false;
		_propagateRoles = false;
		_userIds = new long[2];
		_verify = false;
	}

	protected long[] addUsers() throws Exception {
		User user1 = UserTestUtil.addUser(
			ServiceTestUtil.randomString(), group.getGroupId());

		_userIds[0] = user1.getUserId();

		User user2 = UserTestUtil.addUser(
			ServiceTestUtil.randomString(), group.getGroupId());

		_userIds[1] = user2.getUserId();

		return _userIds;
	}

	protected Group group;

	private static boolean _propagateMembership;
	private static boolean _propagateRoles;
	private static long[] _userIds = new long[2];
	private static boolean _verify;

}