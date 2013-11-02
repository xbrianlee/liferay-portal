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
public class OrganizationMembershipPolicyBasicTest
	extends BaseOrganizationMembershipPolicyTestCase {

	@Test
	public void testIsMembershipAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] standardOrganizationIds = addStandardOrganizations();

		Assert.assertTrue(
			OrganizationMembershipPolicyUtil.isMembershipAllowed(
				userIds[0], standardOrganizationIds[0]));
	}

	@Test
	public void testIsMembershipNotAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] forbiddenOrganizationIds = addForbiddenOrganizations();

		Assert.assertFalse(
			OrganizationMembershipPolicyUtil.isMembershipAllowed(
				userIds[0], forbiddenOrganizationIds[0]));
	}

	@Test
	public void testIsMembershipNotRequired() throws Exception {
		long[] userIds = addUsers();
		long[] standardOrganizationIds = addStandardOrganizations();

		Assert.assertFalse(
			OrganizationMembershipPolicyUtil.isMembershipRequired(
				userIds[0], standardOrganizationIds[0]));
	}

	@Test
	public void testIsMembershipRequired() throws Exception {
		long[] userIds = addUsers();
		long[] requiredOrganizationIds = addRequiredOrganizations();

		Assert.assertTrue(
			OrganizationMembershipPolicyUtil.isMembershipRequired(
				userIds[0], requiredOrganizationIds[0]));
	}

	@Test
	public void testIsRoleAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] standardOrganizationIds = addStandardOrganizations();
		long[] standardRoleIds = addStandardRoles();

		Assert.assertTrue(
			OrganizationMembershipPolicyUtil.isRoleAllowed(
				userIds[0], standardOrganizationIds[0], standardRoleIds[0]));
	}

	@Test
	public void testIsRoleNotAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] standardOrganizationIds = addStandardOrganizations();
		long[] forbiddenRoleIds = addForbiddenRoles();

		Assert.assertFalse(
			OrganizationMembershipPolicyUtil.isRoleAllowed(
				userIds[0], standardOrganizationIds[0], forbiddenRoleIds[0]));
	}

	@Test
	public void testIsRoleNotRequired() throws Exception {
		long[] userIds = addUsers();
		long[] standardOrganizationIds = addStandardOrganizations();
		long[] standardRoleIds = addStandardRoles();

		Assert.assertFalse(
			OrganizationMembershipPolicyUtil.isRoleRequired(
				userIds[0], standardOrganizationIds[0], standardRoleIds[0]));
	}

	@Test
	public void testIsRoleRequired() throws Exception {
		long[] userIds = addUsers();
		long[] standardOrganizationIds = addStandardOrganizations();
		long[] requiredRoleIds = addRequiredRoles();

		Assert.assertTrue(
			OrganizationMembershipPolicyUtil.isRoleRequired(
				userIds[0], standardOrganizationIds[0], requiredRoleIds[0]));
	}

}