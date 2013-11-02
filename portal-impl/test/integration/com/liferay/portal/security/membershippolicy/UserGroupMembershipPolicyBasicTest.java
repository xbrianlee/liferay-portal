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
public class UserGroupMembershipPolicyBasicTest
	extends BaseUserGroupMembershipPolicyTestCase {

	@Test
	public void testIsMembershipAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] standardUserGroupIds = addStandardUserGroups();

		Assert.assertTrue(
			UserGroupMembershipPolicyUtil.isMembershipAllowed(
				userIds[0], standardUserGroupIds[0]));
	}

	@Test
	public void testIsMembershipNotAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] forbiddenUserGroupIds = addForbiddenUserGroups();

		Assert.assertFalse(
			UserGroupMembershipPolicyUtil.isMembershipAllowed(
				userIds[0], forbiddenUserGroupIds[0]));
	}

	@Test
	public void testIsMembershipNotRequired() throws Exception {
		long[] userIds = addUsers();
		long[] standardUserGroupIds = addStandardUserGroups();

		Assert.assertFalse(
			UserGroupMembershipPolicyUtil.isMembershipRequired(
				userIds[0], standardUserGroupIds[0]));
	}

	@Test
	public void testIsMembershipRequired() throws Exception {
		long[] userIds = addUsers();
		long[] requiredUserGroupIds = addRequiredUserGroups();

		Assert.assertTrue(
			UserGroupMembershipPolicyUtil.isMembershipRequired(
				userIds[0], requiredUserGroupIds[0]));
	}

}