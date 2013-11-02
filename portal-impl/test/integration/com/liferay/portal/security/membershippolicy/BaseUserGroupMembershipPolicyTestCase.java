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

import com.liferay.portal.model.UserGroup;
import com.liferay.portal.util.UserGroupTestUtil;

import org.junit.After;
import org.junit.Before;

/**
 * @author Roberto Díaz
 */
public abstract class BaseUserGroupMembershipPolicyTestCase
	extends BaseMembersipPolicyTestCase {

	public static long[] getForbiddenUserGroupIds() {
		return _forbiddenUserGroupIds;
	}

	public static long[] getRequiredUserGroupIds() {
		return _requiredUserGroupIds;
	}

	public static long[] getStandardUserGroupIds() {
		return _standardUserGroupIds;
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		userGroup = UserGroupTestUtil.addUserGroup();
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		userGroup = null;

		_forbiddenUserGroupIds = new long[2];
		_requiredUserGroupIds = new long[2];
		_standardUserGroupIds = new long[2];
	}

	protected long[] addForbiddenUserGroups() throws Exception {
		UserGroup forbiddenUserGroup1 = UserGroupTestUtil.addUserGroup();

		_forbiddenUserGroupIds[0] = forbiddenUserGroup1.getUserGroupId();

		UserGroup forbiddenUserGroup2 = UserGroupTestUtil.addUserGroup();

		_forbiddenUserGroupIds[1] = forbiddenUserGroup2.getUserGroupId();

		return _forbiddenUserGroupIds;
	}

	protected long[] addRequiredUserGroups() throws Exception {
		UserGroup requiredUserGroup1 = UserGroupTestUtil.addUserGroup();

		_requiredUserGroupIds[0] = requiredUserGroup1.getUserGroupId();

		UserGroup requiredUserGroup2 = UserGroupTestUtil.addUserGroup();

		_requiredUserGroupIds[1] = requiredUserGroup2.getUserGroupId();

		return _requiredUserGroupIds;
	}

	protected long[] addStandardUserGroups() throws Exception {
		UserGroup standardUserGroup1 = UserGroupTestUtil.addUserGroup();

		_standardUserGroupIds[0] = standardUserGroup1.getUserGroupId();

		UserGroup standardUserGroup2 = UserGroupTestUtil.addUserGroup();

		_standardUserGroupIds[1] = standardUserGroup2.getUserGroupId();

		return _standardUserGroupIds;
	}

	protected UserGroup userGroup;

	private static long[] _forbiddenUserGroupIds = new long[2];
	private static long[] _requiredUserGroupIds = new long[2];
	private static long[] _standardUserGroupIds = new long[2];

}