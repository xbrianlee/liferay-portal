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
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.security.membershippolicy.util.MembershipPolicyTestUtil;
import com.liferay.portal.service.GroupServiceUtil;
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
public class SiteMembershipPolicyMembershipsTest
	extends BaseSiteMembershipPolicyTestCase {

	@Test(expected = MembershipPolicyException.class)
	public void testAddUserToForbiddenGroups() throws Exception {
		MembershipPolicyTestUtil.addUser(
			null, null, addForbiddenGroups(), null);
	}

	@Test
	public void testAddUserToRequiredGroups() throws Exception {
		long[] requiredGroupIds = addRequiredGroups();

		int initialGroupUsersCount = UserLocalServiceUtil.getGroupUsersCount(
			requiredGroupIds[0]);

		MembershipPolicyTestUtil.addUser(
			null, null, new long[] {requiredGroupIds[0]}, null);

		Assert.assertEquals(
			initialGroupUsersCount + 1,
			UserLocalServiceUtil.getGroupUsersCount(requiredGroupIds[0]));
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUsersToForbiddenGroup() throws Exception {
		long[] forbiddenGroupIds = addForbiddenGroups();

		UserServiceUtil.addGroupUsers(
			forbiddenGroupIds[0], addUsers(),
			ServiceTestUtil.getServiceContext());
	}

	@Test
	public void testAssignUsersToRequiredGroup() throws Exception {
		long[] requiredGroupIds = addRequiredGroups();

		int initialGroupUsersCount = UserLocalServiceUtil.getGroupUsersCount(
			requiredGroupIds[0]);

		UserServiceUtil.addGroupUsers(
			requiredGroupIds[0], addUsers(),
			ServiceTestUtil.getServiceContext());

		Assert.assertEquals(
			initialGroupUsersCount + 2,
			UserLocalServiceUtil.getGroupUsersCount(requiredGroupIds[0]));
		Assert.assertTrue(isPropagateMembership());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testAssignUserToForbiddenGroups() throws Exception {
		long[] userIds = addUsers();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, null, null, addForbiddenGroups(), null,
			Collections.<UserGroupRole>emptyList());
	}

	@Test
	public void testAssignUserToRequiredGroups() throws Exception {
		long[] userIds = addUsers();
		long[] requiredGroupIds = addRequiredGroups();

		int initialGroupUsersCount = UserLocalServiceUtil.getGroupUsersCount(
			requiredGroupIds[0]);

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, null, null, new long[] {requiredGroupIds[0]}, null,
			Collections.<UserGroupRole>emptyList());

		Assert.assertEquals(
			initialGroupUsersCount + 1,
			UserLocalServiceUtil.getGroupUsersCount(requiredGroupIds[0]));
		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testPropagateWhenAddingUserToRequiredGroups() throws Exception {
		MembershipPolicyTestUtil.addUser(null, null, addRequiredGroups(), null);

		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testPropagateWhenAssigningUsersToRequiredGroup()
		throws Exception {

		long[] requiredGroupIds = addRequiredGroups();

		UserServiceUtil.addGroupUsers(
			requiredGroupIds[0], addUsers(),
			ServiceTestUtil.getServiceContext());

		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testPropagateWhenAssigningUserToRequiredGroups()
		throws Exception {

		long[] userIds = addUsers();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		MembershipPolicyTestUtil.updateUser(
			user, null, null, addRequiredGroups(), null,
			Collections.<UserGroupRole>emptyList());

		Assert.assertTrue(isPropagateMembership());
	}

	@Test
	public void testUnassignUserFromGroups() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();
		long[] requiredGroupIds = addRequiredGroups();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		List<Group> groups = user.getGroups();

		Assert.assertEquals(1, groups.size());

		long[] userGroupIds = ArrayUtil.append(
			standardGroupIds, requiredGroupIds, new long[] {user.getGroupId()});

		MembershipPolicyTestUtil.updateUser(
			user, null, null, userGroupIds, null,
			Collections.<UserGroupRole>emptyList());

		groups = user.getGroups();

		Assert.assertEquals(userGroupIds.length, groups.size());

		MembershipPolicyTestUtil.updateUser(
			user, null, null, standardGroupIds, null,
			Collections.<UserGroupRole>emptyList());

		groups = user.getGroups();

		Assert.assertEquals(userGroupIds.length - 1, groups.size());
	}

	@Test
	public void testUnassignUserFromRequiredGroups() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();
		long[] requiredGroupIds = addRequiredGroups();

		User user = UserLocalServiceUtil.getUser(userIds[0]);

		List<Group> groups = user.getGroups();

		Assert.assertEquals(1, groups.size());

		long[] userGroupIds = ArrayUtil.append(
			standardGroupIds, requiredGroupIds, new long[] {user.getGroupId()});

		MembershipPolicyTestUtil.updateUser(
			user, null, null, userGroupIds, null,
			Collections.<UserGroupRole>emptyList());

		groups = user.getGroups();

		Assert.assertEquals(userGroupIds.length, groups.size());

		MembershipPolicyTestUtil.updateUser(
			user, null, null, requiredGroupIds, null,
			Collections.<UserGroupRole>emptyList());

		groups = user.getGroups();

		Assert.assertEquals(requiredGroupIds.length, groups.size());
	}

	@Test
	public void testUnassignUsersFromGroup() throws Exception {
		long[] standardGroupIds = addStandardGroups();

		User user = MembershipPolicyTestUtil.addUser(
			null, null, standardGroupIds, null);

		int initialUserGroupCount = UserLocalServiceUtil.getGroupUsersCount(
			standardGroupIds[0]);

		UserServiceUtil.unsetGroupUsers(
			standardGroupIds[0], new long[] {user.getUserId()},
			ServiceTestUtil.getServiceContext());

		Assert.assertEquals(
			initialUserGroupCount - 1,
			UserLocalServiceUtil.getGroupUsersCount(standardGroupIds[0]));
		Assert.assertTrue(isPropagateMembership());
	}

	@Test(expected = MembershipPolicyException.class)
	public void testUnassignUsersFromRequiredGroup() throws Exception {
		long[] requiredGroupIds = addRequiredGroups();

		User user = MembershipPolicyTestUtil.addUser(
			null, null, requiredGroupIds, null);

		UserServiceUtil.unsetGroupUsers(
			requiredGroupIds[0], new long[] {user.getUserId()},
			ServiceTestUtil.getServiceContext());
	}

	@Test
	public void testVerifyWhenAddingGroup() throws Exception {
		MembershipPolicyTestUtil.addGroup();

		Assert.assertTrue(isVerify());
	}

	@Test
	public void testVerifyWhenUpdatingGroup() throws Exception {
		Group group = MembershipPolicyTestUtil.addGroup();

		GroupServiceUtil.updateGroup(
			group.getGroupId(), group.getParentGroupId(),
			ServiceTestUtil.randomString(), group.getDescription(),
			group.getType(), group.isManualMembership(),
			group.getMembershipRestriction(), group.getFriendlyURL(),
			group.isActive(), ServiceTestUtil.getServiceContext());

		Assert.assertTrue(isVerify());
	}

	@Test
	public void testVerifyWhenUpdatingGroupTypeSettings() throws Exception {
		Group group = MembershipPolicyTestUtil.addGroup();

		String typeSettings = ServiceTestUtil.randomString(50);

		GroupServiceUtil.updateGroup(group.getGroupId(), typeSettings);

		Assert.assertTrue(isVerify());
	}

}