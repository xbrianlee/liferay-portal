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

package com.liferay.portal.security.membershippolicy.samples;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.security.membershippolicy.BaseUserGroupMembershipPolicy;
import com.liferay.portal.security.membershippolicy.BaseUserGroupMembershipPolicyTestCase;
import com.liferay.portal.security.membershippolicy.MembershipPolicyException;

import java.io.Serializable;

import java.util.Map;

import org.junit.Assert;

/**
 * @author Roberto Díaz
 */
public class TestUserGroupMembershipPolicy
	extends BaseUserGroupMembershipPolicy {

	@Override
	public void checkMembership(
			long[] userIds, long[] addUserGroupIds, long[] removeUserGroupIds)
		throws PortalException {

		for (long forbiddenUserGroupId :
				BaseUserGroupMembershipPolicyTestCase.
					getForbiddenUserGroupIds()) {

			if (forbiddenUserGroupId == 0) {
				continue;
			}

			if (ArrayUtil.contains(addUserGroupIds, forbiddenUserGroupId)) {
				throw new MembershipPolicyException(
					MembershipPolicyException.
						USER_GROUP_MEMBERSHIP_NOT_ALLOWED);
			}
		}

		for (long requiredUserGroupId :
				BaseUserGroupMembershipPolicyTestCase.
					getRequiredUserGroupIds()) {

			if (requiredUserGroupId == 0) {
				continue;
			}

			if (ArrayUtil.contains(removeUserGroupIds, requiredUserGroupId)) {
				throw new MembershipPolicyException(
					MembershipPolicyException.USER_GROUP_MEMBERSHIP_REQUIRED);
			}
		}
	}

	@Override
	public void propagateMembership(
		long[] userIds, long[] addUserGroupIds, long[] removeUserGroupIds) {

		BaseUserGroupMembershipPolicyTestCase.setPropagateMembership(true);
	}

	@Override
	public void verifyPolicy() {
		BaseUserGroupMembershipPolicyTestCase.setVerify(true);
	}

	@Override
	public void verifyPolicy(UserGroup userGroup) {
		verifyPolicy();
	}

	@Override
	public void verifyPolicy(
		UserGroup userGroup, UserGroup oldUserGroup,
		Map<String, Serializable> oldExpandoAttributes) {

		Assert.assertNotNull(userGroup);
		Assert.assertNotNull(oldUserGroup);
		Assert.assertNotNull(oldExpandoAttributes);

		verifyPolicy(userGroup);
	}

}