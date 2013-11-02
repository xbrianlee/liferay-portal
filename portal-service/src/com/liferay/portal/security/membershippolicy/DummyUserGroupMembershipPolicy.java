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

import com.liferay.portal.model.UserGroup;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Roberto Díaz
 * @author Sergio González
 */
public class DummyUserGroupMembershipPolicy
	extends BaseUserGroupMembershipPolicy {

	@Override
	public void checkMembership(
		long[] userIds, long[] addUserGroupIds, long[] removeUserGroupIds) {
	}

	@Override
	public boolean isMembershipAllowed(long userId, long userGroupId) {
		return true;
	}

	@Override
	public boolean isMembershipRequired(long userId, long userGroupId) {
		return false;
	}

	@Override
	public void propagateMembership(
		long[] userIds, long[] addUserGroupIds, long[] removeUserGroupIds) {
	}

	@Override
	public void verifyPolicy(UserGroup userGroup) {
	}

	@Override
	public void verifyPolicy(
		UserGroup userGroup, UserGroup oldUserGroup,
		Map<String, Serializable> oldExpandoAttributes) {
	}

}