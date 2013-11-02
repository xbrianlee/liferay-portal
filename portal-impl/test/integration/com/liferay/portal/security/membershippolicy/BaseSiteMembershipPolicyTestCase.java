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

import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.RoleTestUtil;

import org.junit.After;

/**
 * @author Roberto Díaz
 */
public abstract class BaseSiteMembershipPolicyTestCase
	extends BaseMembersipPolicyTestCase {

	public static long[] getForbiddenGroupIds() {
		return _forbiddenGroupIds;
	}

	public static long[] getForbiddenRoleIds() {
		return _forbiddenRoleIds;
	}

	public static long[] getRequiredGroupIds() {
		return _requiredGroupIds;
	}

	public static long[] getRequiredRoleIds() {
		return _requiredRoleIds;
	}

	public static long[] getStandardGroupIds() {
		return _standardGroupIds;
	}

	public static long[] getStandardRoleIds() {
		return _standardRoleIds;
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		_forbiddenGroupIds = new long[2];
		_forbiddenRoleIds = new long[2];
		_requiredGroupIds = new long[2];
		_requiredRoleIds = new long[2];
		_standardGroupIds = new long[2];
		_standardRoleIds = new long[2];
	}

	protected long[] addForbiddenGroups() throws Exception {
		Group forbiddenGroup1 = GroupTestUtil.addGroup(
			ServiceTestUtil.randomString());

		_forbiddenGroupIds[0] = forbiddenGroup1.getGroupId();

		Group forbiddenGroup2 = GroupTestUtil.addGroup(
			ServiceTestUtil.randomString());

		_forbiddenGroupIds[1] = forbiddenGroup2.getGroupId();

		return _forbiddenGroupIds;
	}

	protected long[] addForbiddenRoles() throws Exception {
		_forbiddenRoleIds[0] = RoleTestUtil.addGroupRole(group.getGroupId());
		_forbiddenRoleIds[1] = RoleTestUtil.addGroupRole(group.getGroupId());

		return _forbiddenRoleIds;
	}

	protected long[] addRequiredGroups() throws Exception {
		Group requiredGroup1 = GroupTestUtil.addGroup(
			ServiceTestUtil.randomString());

		_requiredGroupIds[0] = requiredGroup1.getGroupId();

		Group requiredGroup2 = GroupTestUtil.addGroup(
			ServiceTestUtil.randomString());

		_requiredGroupIds[1] = requiredGroup2.getGroupId();

		return _requiredGroupIds;
	}

	protected long[] addRequiredRoles() throws Exception {
		_requiredRoleIds[0] = RoleTestUtil.addGroupRole(group.getGroupId());
		_requiredRoleIds[1] = RoleTestUtil.addGroupRole(group.getGroupId());

		return _requiredRoleIds;
	}

	protected long[] addStandardGroups() throws Exception {
		Group standardGroup1 = GroupTestUtil.addGroup(
			ServiceTestUtil.randomString());

		_standardGroupIds[0] = standardGroup1.getGroupId();

		Group standardGroup2 = GroupTestUtil.addGroup(
			ServiceTestUtil.randomString());

		_standardGroupIds[1] = standardGroup2.getGroupId();

		return _standardGroupIds;
	}

	protected long[] addStandardRoles() throws Exception {
		_standardRoleIds[0] = RoleTestUtil.addGroupRole(group.getGroupId());
		_standardRoleIds[1] = RoleTestUtil.addGroupRole(group.getGroupId());

		return _standardRoleIds;
	}

	private static long[] _forbiddenGroupIds = new long[2];
	private static long[] _forbiddenRoleIds = new long[2];
	private static long[] _requiredGroupIds = new long[2];
	private static long[] _requiredRoleIds = new long[2];
	private static long[] _standardGroupIds = new long[2];
	private static long[] _standardRoleIds = new long[2];

}