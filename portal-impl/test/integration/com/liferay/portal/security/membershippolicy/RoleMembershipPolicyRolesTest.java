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

import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.security.membershippolicy.util.MembershipPolicyTestUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.RoleServiceUtil;
import com.liferay.portal.service.ServiceContext;
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
public class RoleMembershipPolicyRolesTest
	extends BaseRoleMembershipPolicyTestCase {

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUsersToForbiddenRole() throws Exception {
		long[] forbiddenRoleIds = addForbiddenRoles();

		UserServiceUtil.addRoleUsers(forbiddenRoleIds[0], addUsers());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUserToForbiddenRole() throws Exception {
		long[] userIds = addUsers();
		long[] forbiddenRoleIds = addForbiddenRoles();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, null, new long[] {forbiddenRoleIds[0]}, null, null,
			Collections.<UserGroupRole>emptyList());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUserToForbiddenRoles() throws Exception {
		long[] userIds = addUsers();

		RoleServiceUtil.addUserRoles(userIds[0], addForbiddenRoles());
	}

	@Test
	public void testPropagateWhenAssigningRolesToUser() throws Exception {
		long[] userIds = addUsers();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, null, addStandardRoles(), null, null,
			Collections.<UserGroupRole>emptyList());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenAssigningRoleToUsers() throws Exception {
		long[] standardRoleId = addStandardRoles();

		UserServiceUtil.setRoleUsers(standardRoleId[0], addUsers());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenAssigningUsersToRole() throws Exception {
		long[] standardRoleId = addStandardRoles();

		UserServiceUtil.addRoleUsers(standardRoleId[0], addUsers());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenAssigningUserToRoles() throws Exception {
		long[] userIds = addUsers();

		RoleServiceUtil.addUserRoles(userIds[0], addStandardRoles());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenUnassigningRolesFromUser() throws Exception {
		long[] userIds = addUsers();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		RoleServiceUtil.addUserRoles(user.getUserId(), addStandardRoles());

		MembershipPolicyTestUtil.updateUser(
			user, null, null, null, null,
			Collections.<UserGroupRole>emptyList());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenUnassigningUserFromRoles() throws Exception {
		long[] userIds = addUsers();

		RoleServiceUtil.unsetUserRoles(userIds[0], addStandardRoles());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testPropagateWhenUnassigningUsersFromRole() throws Exception {
		long[] standardRoles = addStandardRoles();

		UserServiceUtil.unsetRoleUsers(standardRoles[0], addUsers());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testSetForbbidenRoleToUsers() throws Exception {
		long[] forbiddenRoleIds = addForbiddenRoles();

		UserServiceUtil.setRoleUsers(forbiddenRoleIds[0], addUsers());
	}

	@Test
	public void testUnassignRequiredRolesFromUser() throws Exception {
		long[] userIds = addUsers();

		RoleServiceUtil.addUserRoles(userIds[0], addRequiredRoles());

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		List<Role> initialUserRoles = RoleLocalServiceUtil.getUserRoles(
			user.getUserId());

		MembershipPolicyTestUtil.updateUser(
			user, null, null, null, null,
			Collections.<UserGroupRole>emptyList());

		List<Role> currentUserRoles = RoleLocalServiceUtil.getUserRoles(
			user.getUserId());

		Assert.assertEquals(initialUserRoles.size(), currentUserRoles.size());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testUnassignUserFromRequiredRoles() throws Exception {
		long[] userIds = addUsers();

		RoleServiceUtil.unsetUserRoles(userIds[0], addRequiredRoles());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testUnassignUsersFromRequiredRole() throws Exception {
		long[] requiredRoleIds = addRequiredRoles();

		UserServiceUtil.unsetRoleUsers(requiredRoleIds[0], addUsers());
	}

	@Test
	public void testUnassignUsersFromRole() throws Exception {
		long[] standardRoleIds = addStandardRoles();

		UserServiceUtil.unsetRoleUsers(standardRoleIds[0], addUsers());

		Assert.assertTrue(isPropagateRoles());
	}

	@Test
	public void testVerifyWhenAddingRole() throws Exception {
		MembershipPolicyTestUtil.addRole(RoleConstants.TYPE_REGULAR);

		Assert.assertTrue(isVerify());
	}

	@Test
	public void testVerifyWhenUpdatingRole() throws Exception {
		Role role = MembershipPolicyTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		RoleServiceUtil.updateRole(
			role.getRoleId(), ServiceTestUtil.randomString(),
			role.getTitleMap(), role.getDescriptionMap(), role.getSubtype(),
			new ServiceContext());

		Assert.assertTrue(isVerify());
	}

}