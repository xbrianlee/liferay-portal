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
import com.liferay.portal.model.Role;
import com.liferay.portal.security.membershippolicy.BaseRoleMembershipPolicy;
import com.liferay.portal.security.membershippolicy.BaseRoleMembershipPolicyTestCase;
import com.liferay.portal.security.membershippolicy.MembershipPolicyException;

import java.io.Serializable;

import java.util.Map;

import org.junit.Assert;

/**
 * @author Roberto Díaz
 */
public class TestRoleMembershipPolicy extends BaseRoleMembershipPolicy {

	@Override
	public void checkRoles(
			long[] userIds, long[] addRoleIds, long[] removeRoleIds)
		throws PortalException {

		for (long forbiddenRoleId :
				BaseRoleMembershipPolicyTestCase.getForbiddenRoleIds()) {

			if (forbiddenRoleId == 0) {
				continue;
			}

			if (ArrayUtil.contains(addRoleIds, forbiddenRoleId)) {
				throw new MembershipPolicyException(
					MembershipPolicyException.ROLE_MEMBERSHIP_NOT_ALLOWED);
			}
		}

		for (long requiredRoleId :
				BaseRoleMembershipPolicyTestCase.getRequiredRoleIds()) {

			if (requiredRoleId == 0) {
				continue;
			}

			if (ArrayUtil.contains(removeRoleIds, requiredRoleId)) {
				throw new MembershipPolicyException(
					MembershipPolicyException.ROLE_MEMBERSHIP_REQUIRED);
			}
		}
	}

	@Override
	public void propagateRoles(
		long[] userIds, long[] addRoleIds, long[] removeRoleIds) {

		BaseRoleMembershipPolicyTestCase.setPropagateRoles(true);
	}

	@Override
	public void verifyPolicy() {
		BaseRoleMembershipPolicyTestCase.setVerify(true);
	}

	@Override
	public void verifyPolicy(Role role) {
		verifyPolicy();
	}

	@Override
	public void verifyPolicy(
		Role role, Role oldRole,
		Map<String, Serializable> oldExpandoAttributes) {

		Assert.assertNotNull(role);
		Assert.assertNotNull(oldRole);
		Assert.assertNotNull(oldExpandoAttributes);

		verifyPolicy(role);
	}

}