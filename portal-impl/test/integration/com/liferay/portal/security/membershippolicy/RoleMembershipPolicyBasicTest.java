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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Roberto Díaz
 */
public class RoleMembershipPolicyBasicTest
	extends BaseRoleMembershipPolicyTestCase {

	@Test
	public void testIsRoleAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] standardRoleIds = addStandardRoles();

		Assert.assertTrue(
			RoleMembershipPolicyUtil.isRoleAllowed(
				userIds[0], standardRoleIds[0]));
	}

	@Test
	public void testIsRoleNotAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] forbiddenRoleIds = addForbiddenRoles();

		Assert.assertFalse(
			RoleMembershipPolicyUtil.isRoleAllowed(
				userIds[0], forbiddenRoleIds[0]));
	}

	@Test
	public void testIsRoleNotRequired() throws Exception {
		long[] userIds = addUsers();
		long[] standardRoleIds = addStandardRoles();

		Assert.assertFalse(
			RoleMembershipPolicyUtil.isRoleRequired(
				userIds[0], standardRoleIds[0]));
	}

	@Test
	public void testIsRoleRequired() throws Exception {
		long[] userIds = addUsers();
		long[] requiredRoleIds = addRequiredRoles();

		Assert.assertTrue(
			RoleMembershipPolicyUtil.isRoleRequired(
				userIds[0], requiredRoleIds[0]));
	}

}