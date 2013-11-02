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

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.security.membershippolicy.util.MembershipPolicyTestUtil;
import com.liferay.portal.service.OrganizationServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Roberto Díaz
 */
public class OrganizationMembershipPolicyMembershipsTest
	extends BaseOrganizationMembershipPolicyTestCase {

	@Test(expected = MembershipPolicyException.class)
	public void testAddUserToForbiddenOrganization() throws Exception {
		MembershipPolicyTestUtil.addUser(
			addForbiddenOrganizations(), null, null, null);
	}

	@Test
	public void testAddUserToRequiredOrganizations() throws Exception {
		long[] requiredOrganizationIds = addRequiredOrganizations();

		int initialOrganizationUsersCount =
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]);

		MembershipPolicyTestUtil.addUser(
			new long[] {requiredOrganizationIds[0]}, null, null, null);

		Assert.assertEquals(
			initialOrganizationUsersCount + 1,
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]));
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUsersToForbiddenOrganization() throws Exception {
		long[] forbiddenOrganizationIds = addForbiddenOrganizations();

		UserServiceUtil.addOrganizationUsers(
			forbiddenOrganizationIds[0], addUsers());
	}

	@Test
	public void testAssignUsersToRequiredOrganization() throws Exception {
		long[] requiredOrganizationIds = addRequiredOrganizations();

		int initialOrganizationUsersCount =
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]);

		UserServiceUtil.addOrganizationUsers(
			requiredOrganizationIds[0], addUsers());

		Assert.assertEquals(
			initialOrganizationUsersCount + 2,
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]));
		Assert.assertTrue(isPropagateMembership());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUserToForbiddenOrganizations() throws Exception {
		long[] userIds = addUsers();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, addForbiddenOrganizations(), null, null, null,
			Collections.<UserGroupRole>emptyList());
	}

	@Test
	public void testAssignUserToRequiredOrganizations() throws Exception {
		long[] userIds = addUsers();
		long[] requiredOrganizationIds = addRequiredOrganizations();

		int initialOrganizationUsersCount =
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]);

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, new long[] {requiredOrganizationIds[0]}, null, null, null,
			Collections.<UserGroupRole>emptyList());

		Assert.assertEquals(
			initialOrganizationUsersCount + 1,
			UserLocalServiceUtil.getOrganizationUsersCount(
				requiredOrganizationIds[0]));
		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testPropagateWhenAddingUserToRequiredOrganizations()
		throws Exception {

		MembershipPolicyTestUtil.addUser(
			addRequiredOrganizations(), null, null, null);

		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testPropagateWhenAssigningUsersToRequiredOrganization()
		throws Exception {

		long[] requiredOrganizationIds = addRequiredOrganizations();

		UserServiceUtil.addOrganizationUsers(
			requiredOrganizationIds[0], addUsers());

		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testPropagateWhenAssigningUserToRequiredOrganizations()
		throws Exception {

		long[] userIds = addUsers();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, addRequiredOrganizations(), null, null, null,
			Collections.<UserGroupRole>emptyList());

		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testUnassignUserFromOrganizations() throws Exception {
		long[] userIds = addUsers();
		long[] standardOrganizationIds = addStandardOrganizations();
		long[] requiredOrganizationIds = addRequiredOrganizations();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		List<Organization> organizations = user.getOrganizations();

		Assert.assertEquals(0, organizations.size());

		long[] userOrganizationIds = ArrayUtil.append(
			standardOrganizationIds, requiredOrganizationIds);

		MembershipPolicyTestUtil.updateUser(
			user, userOrganizationIds, null, null, null,
			Collections.<UserGroupRole>emptyList());

		organizations = user.getOrganizations();

		Assert.assertEquals(userOrganizationIds.length, organizations.size());

		MembershipPolicyTestUtil.updateUser(
			user, standardOrganizationIds, null, null, null,
			Collections.<UserGroupRole>emptyList());

		organizations = user.getOrganizations();

		Assert.assertEquals(userOrganizationIds.length, organizations.size());
	}

	@Test
	public void testUnassignUserFromRequiredOrganizations() throws Exception {
		long[] userIds = addUsers();
		long[] standardOrganizationIds = addStandardOrganizations();
		long[] requiredOrganizationIds = addRequiredOrganizations();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		List<Organization> organizations = user.getOrganizations();

		Assert.assertEquals(0, organizations.size());

		long[] userOrganizationIds = ArrayUtil.append(
			standardOrganizationIds, requiredOrganizationIds);

		MembershipPolicyTestUtil.updateUser(
			user, userOrganizationIds, null, null, null,
			Collections.<UserGroupRole>emptyList());

		organizations = user.getOrganizations();

		Assert.assertEquals(userOrganizationIds.length, organizations.size());

		MembershipPolicyTestUtil.updateUser(
			user, requiredOrganizationIds, null, null, null,
			Collections.<UserGroupRole>emptyList());

		organizations = user.getOrganizations();

		Assert.assertEquals(
			requiredOrganizationIds.length, organizations.size());
	}

	@Test
	public void testUnassignUsersFromOrganization() throws Exception {
		long[] standardOrganizationIds = addStandardOrganizations();

		User user = MembershipPolicyTestUtil.addUser(
			standardOrganizationIds, null, null, null);

		int initialUserOrganizationCount =
			UserLocalServiceUtil.getOrganizationUsersCount(
				standardOrganizationIds[0]);

		UserServiceUtil.unsetOrganizationUsers(
			standardOrganizationIds[0], new long[] {user.getUserId()});

		Assert.assertEquals(
			initialUserOrganizationCount - 1,
			UserLocalServiceUtil.getOrganizationUsersCount(
				standardOrganizationIds[0]));
		Assert.assertTrue(isPropagateMembership());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testUnassignUsersFromRequiredOrganization() throws Exception {
		long[] requiredOrganizationIds = addRequiredOrganizations();

		User user = MembershipPolicyTestUtil.addUser(
			requiredOrganizationIds, null, null, null);

		UserServiceUtil.unsetOrganizationUsers(
			requiredOrganizationIds[0], new long[] {user.getUserId()});
	}

	@Test
	public void testVerifyWhenAddingOrganization() throws Exception {
		MembershipPolicyTestUtil.addOrganization();

		Assert.assertTrue(isVerify());
	}

	@Test
	public void testVerifyWhenUpdatingOrganization() throws Exception {
		Organization organization = MembershipPolicyTestUtil.addOrganization();

		OrganizationServiceUtil.updateOrganization(
			organization.getOrganizationId(),
			organization.getParentOrganizationId(), organization.getName(),
			organization.getType(), 0, 0, organization.getStatusId(),
			organization.getComments(), false,
			ServiceTestUtil.getServiceContext());

		Assert.assertTrue(isVerify());
	}

}