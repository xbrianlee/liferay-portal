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

import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.util.UserTestUtil;
import com.liferay.portlet.usergroupsadmin.search.UserUserGroupChecker;

import javax.portlet.RenderResponse;

import org.junit.Assert;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Roberto Díaz
 */
public class UserGroupMembershipPolicyRowCheckerTest
	extends BaseUserGroupMembershipPolicyTestCase {

	@Test
	public void testIsCheckerDisabledWhenSettingForbiddenUserGroupToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenUserGroupId = addForbiddenUserGroups()[0];

		UserGroup forbiddenUserGroup = UserGroupLocalServiceUtil.getUserGroup(
			forbiddenUserGroupId);

		UserUserGroupChecker userUserGroupChecker = new UserUserGroupChecker(
			renderResponse, forbiddenUserGroup);

		User user = UserTestUtil.addUser();

		Assert.assertTrue(userUserGroupChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenSettingRequiredUserGroupToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredUserGroupId = addRequiredUserGroups()[0];

		UserGroup requiredUserGroup = UserGroupLocalServiceUtil.getUserGroup(
			requiredUserGroupId);

		UserUserGroupChecker userUserGroupChecker = new UserUserGroupChecker(
			renderResponse, requiredUserGroup);

		User user = UserTestUtil.addUser();

		Assert.assertFalse(userUserGroupChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingForbiddenUserGroupToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenUserGroupId = addForbiddenUserGroups()[0];

		UserGroup forbiddenUserGroup = UserGroupLocalServiceUtil.getUserGroup(
			forbiddenUserGroupId);

		UserUserGroupChecker userUserGroupChecker = new UserUserGroupChecker(
			renderResponse, forbiddenUserGroup);

		User user = UserTestUtil.addUser();

		UserGroupLocalServiceUtil.addUserUserGroup(
			user.getUserId(), forbiddenUserGroupId);

		Assert.assertFalse(userUserGroupChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingRequiredUserGroupToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredUserGroupId = addRequiredUserGroups()[0];

		UserGroup requiredUserGroup = UserGroupLocalServiceUtil.getUserGroup(
			requiredUserGroupId);

		UserUserGroupChecker userUserGroupChecker = new UserUserGroupChecker(
			renderResponse, requiredUserGroup);

		User user = UserTestUtil.addUser();

		UserGroupLocalServiceUtil.addUserUserGroup(
			user.getUserId(), requiredUserGroupId);

		Assert.assertTrue(userUserGroupChecker.isDisabled(user));
	}

}