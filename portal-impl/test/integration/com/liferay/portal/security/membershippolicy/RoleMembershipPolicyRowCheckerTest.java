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
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.util.UserTestUtil;
import com.liferay.portlet.rolesadmin.search.UserRoleChecker;

import javax.portlet.RenderResponse;

import org.junit.Assert;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Roberto Díaz
 */
public class RoleMembershipPolicyRowCheckerTest
	extends BaseRoleMembershipPolicyTestCase {

	@Test
	public void testIsCheckerDisabledWhenSettingForbiddenRoleToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenRoleId = addForbiddenRoles()[0];

		Role forbiddenRole = RoleLocalServiceUtil.getRole(forbiddenRoleId);

		UserRoleChecker userRoleChecker = new UserRoleChecker(
			renderResponse, forbiddenRole);

		User user = UserTestUtil.addUser();

		Assert.assertTrue(userRoleChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenSettingRequiredRoleToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredRoleId = addRequiredRoles()[0];

		Role requiredRole = RoleLocalServiceUtil.getRole(requiredRoleId);

		UserRoleChecker userRoleChecker = new UserRoleChecker(
			renderResponse, requiredRole);

		User user = UserTestUtil.addUser();

		Assert.assertFalse(userRoleChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingForbiddenRoleToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenRoleId = addForbiddenRoles()[0];

		Role forbiddenRole = RoleLocalServiceUtil.getRole(forbiddenRoleId);

		UserRoleChecker userRoleChecker = new UserRoleChecker(
			renderResponse, forbiddenRole);

		User user = UserTestUtil.addUser();

		RoleLocalServiceUtil.addUserRole(user.getUserId(), forbiddenRoleId);

		Assert.assertFalse(userRoleChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingRequiredRoleToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredRoleId = addRequiredRoles()[0];

		Role requiredRole = RoleLocalServiceUtil.getRole(requiredRoleId);

		UserRoleChecker userRoleChecker = new UserRoleChecker(
			renderResponse, requiredRole);

		User user = UserTestUtil.addUser();

		RoleLocalServiceUtil.addUserRole(user.getUserId(), requiredRoleId);

		Assert.assertTrue(userRoleChecker.isDisabled(user));
	}

}