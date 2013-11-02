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
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.util.UserTestUtil;
import com.liferay.portlet.sites.search.UserGroupRoleRoleChecker;
import com.liferay.portlet.sites.search.UserGroupRoleUserChecker;
import com.liferay.portlet.usergroupsadmin.search.UserGroupChecker;

import javax.portlet.RenderResponse;

import org.junit.Assert;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Roberto Díaz
 */
public class SiteMembershipPolicyRowCheckerTest
	extends BaseSiteMembershipPolicyTestCase {

	@Test
	public void testIsCheckerDisabledWhenSettingForbiddenGroupToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenGroupId = addForbiddenGroups()[0];

		Group forbiddenGroup = GroupLocalServiceUtil.getGroup(forbiddenGroupId);

		UserGroupChecker userGroupChecker = new UserGroupChecker(
			renderResponse, forbiddenGroup);

		User user = UserTestUtil.addUser();

		Assert.assertTrue(userGroupChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenSettingForbiddenRoleToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenRoleId = addForbiddenRoles()[0];

		Role role = RoleLocalServiceUtil.getRole(forbiddenRoleId);

		UserGroupRoleUserChecker userGroupRoleUserChecker =
			new UserGroupRoleUserChecker(renderResponse, group, role);

		User user = UserTestUtil.addUser();

		Assert.assertTrue(userGroupRoleUserChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenSettingRequiredGroupToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredGroupId = addRequiredGroups()[0];

		Group requiredGroup = GroupLocalServiceUtil.getGroup(requiredGroupId);

		UserGroupChecker userGroupChecker = new UserGroupChecker(
			renderResponse, requiredGroup);

		User user = UserTestUtil.addUser();

		Assert.assertFalse(userGroupChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenSettingRequiredRoleToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredRoleId = addRequiredRoles()[0];

		Role role = RoleLocalServiceUtil.getRole(requiredRoleId);

		UserGroupRoleUserChecker userGroupRoleUserChecker =
			new UserGroupRoleUserChecker(renderResponse, group, role);

		User user = UserTestUtil.addUser();

		Assert.assertFalse(userGroupRoleUserChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenSettingUserToForbiddenRole()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		User user = UserTestUtil.addUser();

		UserGroupRoleRoleChecker userGroupRoleRoleChecker =
			new UserGroupRoleRoleChecker(renderResponse, user, group);

		Role role = RoleLocalServiceUtil.getRole(addForbiddenRoles()[0]);

		Assert.assertTrue(userGroupRoleRoleChecker.isDisabled(role));
	}

	@Test
	public void testIsCheckerDisabledWhenSettingUserToRequiredRole()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		User user = UserTestUtil.addUser();

		UserGroupRoleRoleChecker userGroupRoleRoleChecker =
			new UserGroupRoleRoleChecker(renderResponse, user, group);

		Role role = RoleLocalServiceUtil.getRole(addRequiredRoles()[0]);

		Assert.assertFalse(userGroupRoleRoleChecker.isDisabled(role));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingForbiddenGroupFromUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenGroupId = addForbiddenGroups()[0];

		Group forbiddenGroup = GroupLocalServiceUtil.getGroup(forbiddenGroupId);

		UserGroupChecker userGroupChecker = new UserGroupChecker(
			renderResponse, forbiddenGroup);

		User user = UserTestUtil.addUser(
			ServiceTestUtil.randomString(), forbiddenGroupId);

		Assert.assertFalse(userGroupChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingForbiddenRoleFromUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenRoleId = addForbiddenRoles()[0];

		Role role = RoleLocalServiceUtil.getRole(forbiddenRoleId);

		UserGroupRoleUserChecker userGroupRoleUserChecker =
			new UserGroupRoleUserChecker(renderResponse, group, role);

		User user = UserTestUtil.addUser();

		UserGroupRoleLocalServiceUtil.addUserGroupRoles(
			user.getUserId(), group.getGroupId(), new long[] {forbiddenRoleId});

		Assert.assertFalse(userGroupRoleUserChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingRequiredGroupFromUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredGroupId = addRequiredGroups()[0];

		Group requiredGroup = GroupLocalServiceUtil.getGroup(requiredGroupId);

		UserGroupChecker userGroupChecker = new UserGroupChecker(
			renderResponse, requiredGroup);

		User user = UserTestUtil.addUser(
			ServiceTestUtil.randomString(), requiredGroupId);

		Assert.assertTrue(userGroupChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingRequiredRoleFromUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredRoleId = addRequiredRoles()[0];

		Role role = RoleLocalServiceUtil.getRole(requiredRoleId);

		UserGroupRoleUserChecker userGroupRoleUserChecker =
			new UserGroupRoleUserChecker(renderResponse, group, role);

		User user = UserTestUtil.addUser();

		UserGroupRoleLocalServiceUtil.addUserGroupRoles(
			user.getUserId(), group.getGroupId(), new long[] {requiredRoleId});

		Assert.assertTrue(userGroupRoleUserChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingUserFromForbiddenRole()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		User user = UserTestUtil.addUser();

		UserGroupRoleRoleChecker userGroupRoleRoleChecker =
			new UserGroupRoleRoleChecker(renderResponse, user, group);

		long forbiddenRoleId = addForbiddenRoles()[0];

		Role role = RoleLocalServiceUtil.getRole(forbiddenRoleId);

		UserGroupRoleLocalServiceUtil.addUserGroupRoles(
			user.getUserId(), group.getGroupId(), new long[] {forbiddenRoleId});

		Assert.assertFalse(userGroupRoleRoleChecker.isDisabled(role));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingUserFromRequiredRole()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		User user = UserTestUtil.addUser();

		UserGroupRoleRoleChecker userGroupRoleRoleChecker =
			new UserGroupRoleRoleChecker(renderResponse, user, group);

		long requiredRoleId = addRequiredRoles()[0];

		Role role = RoleLocalServiceUtil.getRole(requiredRoleId);

		UserGroupRoleLocalServiceUtil.addUserGroupRoles(
			user.getUserId(), group.getGroupId(), new long[] {requiredRoleId});

		Assert.assertTrue(userGroupRoleRoleChecker.isDisabled(role));
	}

}