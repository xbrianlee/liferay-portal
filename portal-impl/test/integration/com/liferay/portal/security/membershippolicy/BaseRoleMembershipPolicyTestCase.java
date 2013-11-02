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

import com.liferay.portal.util.RoleTestUtil;

import org.junit.After;

/**
 * @author Roberto Díaz
 */
public abstract class BaseRoleMembershipPolicyTestCase
	extends BaseMembersipPolicyTestCase {

	public static long[] getForbiddenRoleIds() {
		return _forbiddenRoleIds;
	}

	public static long[] getRequiredRoleIds() {
		return _requiredRoleIds;
	}

	public static long[] getStandardRoleIds() {
		return _standardRoleIds;
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		_forbiddenRoleIds = new long[2];
		_requiredRoleIds = new long[2];
		_standardRoleIds = new long[2];
	}

	protected long[] addForbiddenRoles() throws Exception {
		_forbiddenRoleIds[0] = RoleTestUtil.addRegularRole(group.getGroupId());
		_forbiddenRoleIds[1] = RoleTestUtil.addRegularRole(group.getGroupId());

		return _forbiddenRoleIds;
	}

	protected long[] addRequiredRoles() throws Exception {
		_requiredRoleIds[0] = RoleTestUtil.addRegularRole(group.getGroupId());
		_requiredRoleIds[1] = RoleTestUtil.addRegularRole(group.getGroupId());

		return _requiredRoleIds;
	}

	protected long[] addStandardRoles() throws Exception {
		_standardRoleIds[0] = RoleTestUtil.addRegularRole(group.getGroupId());
		_standardRoleIds[1] = RoleTestUtil.addRegularRole(group.getGroupId());

		return _standardRoleIds;
	}

	private static long[] _forbiddenRoleIds = new long[2];
	private static long[] _requiredRoleIds = new long[2];
	private static long[] _standardRoleIds = new long[2];

}