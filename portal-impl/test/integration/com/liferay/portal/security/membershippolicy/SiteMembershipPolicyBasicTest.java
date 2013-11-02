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
public class SiteMembershipPolicyBasicTest
	extends BaseSiteMembershipPolicyTestCase {

	@Test
	public void testIsMembershipAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();

		Assert.assertTrue(
			SiteMembershipPolicyUtil.isMembershipAllowed(
				userIds[0], standardGroupIds[0]));
	}

	@Test
	public void testIsMembershipNotAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] forbiddenGroupIds = addForbiddenGroups();

		Assert.assertFalse(
			SiteMembershipPolicyUtil.isMembershipAllowed(
				userIds[0], forbiddenGroupIds[0]));
	}

	@Test
	public void testIsMembershipNotRequired() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();

		Assert.assertFalse(
			SiteMembershipPolicyUtil.isMembershipRequired(
				userIds[0], standardGroupIds[0]));
	}

	@Test
	public void testIsMembershipRequired() throws Exception {
		long[] userIds = addUsers();
		long[] requiredGroupIds = addRequiredGroups();

		Assert.assertTrue(
			SiteMembershipPolicyUtil.isMembershipRequired(
				userIds[0], requiredGroupIds[0]));
	}

	@Test
	public void testIsRoleAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();
		long[] standardRoleIds = addStandardRoles();

		Assert.assertTrue(
			SiteMembershipPolicyUtil.isRoleAllowed(
				userIds[0], standardGroupIds[0], standardRoleIds[0]));
	}

	@Test
	public void testIsRoleNotAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();
		long[] forbiddenRoleIds = addForbiddenRoles();

		Assert.assertFalse(
			SiteMembershipPolicyUtil.isRoleAllowed(
				userIds[0], standardGroupIds[0], forbiddenRoleIds[0]));
	}

	@Test
	public void testIsRoleNotRequired() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();
		long[] standardRoleIds = addStandardRoles();

		Assert.assertFalse(
			SiteMembershipPolicyUtil.isRoleRequired(
				userIds[0], standardGroupIds[0], standardRoleIds[0]));
	}

	@Test
	public void testIsRoleRequired() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();
		long[] requiredRoleIds = addRequiredRoles();

		Assert.assertTrue(
			SiteMembershipPolicyUtil.isRoleRequired(
				userIds[0], standardGroupIds[0], requiredRoleIds[0]));
	}

}