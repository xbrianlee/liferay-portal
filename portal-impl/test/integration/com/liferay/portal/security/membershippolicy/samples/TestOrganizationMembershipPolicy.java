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
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.security.membershippolicy.BaseOrganizationMembershipPolicy;
import com.liferay.portal.security.membershippolicy.BaseOrganizationMembershipPolicyTestCase;
import com.liferay.portal.security.membershippolicy.MembershipPolicyException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetTag;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

/**
 * @author Roberto Díaz
 */
public class TestOrganizationMembershipPolicy
	extends BaseOrganizationMembershipPolicy {

	@Override
	public void checkMembership(
			long[] userIds, long[] addOrganizationIds,
			long[] removeOrganizationIds)
		throws PortalException {

		for (long forbiddenOrganizationId :
				BaseOrganizationMembershipPolicyTestCase.
					getForbiddenOrganizationIds()) {

			if (forbiddenOrganizationId == 0) {
				continue;
			}

			if (ArrayUtil.contains(
					addOrganizationIds, forbiddenOrganizationId)) {

				throw new MembershipPolicyException(
					MembershipPolicyException.
						ORGANIZATION_MEMBERSHIP_NOT_ALLOWED);
			}
		}

		for (long requiredOrganizationId :
				BaseOrganizationMembershipPolicyTestCase.
					getRequiredOrganizationIds()) {

			if (requiredOrganizationId == 0) {
				continue;
			}

			if (ArrayUtil.contains(
					removeOrganizationIds, requiredOrganizationId)) {

				throw new MembershipPolicyException(
					MembershipPolicyException.ORGANIZATION_MEMBERSHIP_REQUIRED);
			}
		}
	}

	@Override
	public void checkRoles(
			List<UserGroupRole> addUserGroupRoles,
			List<UserGroupRole> removeUserGroupRoles)
		throws PortalException {

		long[] addUserGroupRoleIds = new long[2];
		long[] removeUserGroupRoleIds = new long[2];

		if ((addUserGroupRoles != null) && !addUserGroupRoles.isEmpty()) {
			for (UserGroupRole addUserGroupRole : addUserGroupRoles) {
				addUserGroupRoleIds[0] = addUserGroupRole.getRoleId();
			}
		}

		if ((removeUserGroupRoles != null) && !removeUserGroupRoles.isEmpty()) {
			for (UserGroupRole removeUserGroupRole : removeUserGroupRoles) {
				removeUserGroupRoleIds[0] = removeUserGroupRole.getRoleId();
			}
		}

		for (long forbiddenGroupRoleId :
				BaseOrganizationMembershipPolicyTestCase.
					getForbiddenRoleIds()) {

			if (forbiddenGroupRoleId == 0) {
				continue;
			}

			if (ArrayUtil.contains(addUserGroupRoleIds, forbiddenGroupRoleId)) {
				throw new MembershipPolicyException(
					MembershipPolicyException.ROLE_MEMBERSHIP_NOT_ALLOWED);
			}
		}

		for (long requiredGroupRoleId :
				BaseOrganizationMembershipPolicyTestCase.getRequiredRoleIds()) {

			if (requiredGroupRoleId == 0) {
				continue;
			}

			if (ArrayUtil.contains(
					removeUserGroupRoleIds, requiredGroupRoleId)) {

				throw new MembershipPolicyException(
					MembershipPolicyException.ROLE_MEMBERSHIP_REQUIRED);
			}
		}
	}

	@Override
	public void propagateMembership(
			long[] userIds, long[] addOrganizationIds,
			long[] removeOrganizationIds) {

		BaseOrganizationMembershipPolicyTestCase.setPropagateMembership(true);
	}

	@Override
	public void propagateRoles(
		List<UserGroupRole> addUserGroupRoles,
		List<UserGroupRole> removeUserGroupRoles) {

		BaseOrganizationMembershipPolicyTestCase.setPropagateRoles(true);
	}

	@Override
	public void verifyPolicy() {
		BaseOrganizationMembershipPolicyTestCase.setVerify(true);
	}

	@Override
	public void verifyPolicy(Organization organization) {
		verifyPolicy();
	}

	@Override
	public void verifyPolicy(
			Organization organization, Organization oldOrganization,
			List<AssetCategory> oldAssetCategories, List<AssetTag> oldAssetTags,
			Map<String, Serializable> oldExpandoAttributes) {

			Assert.assertNotNull(organization);
			Assert.assertNotNull(oldOrganization);
			Assert.assertNotNull(oldAssetCategories);
			Assert.assertNotNull(oldAssetTags);
			Assert.assertNotNull(oldExpandoAttributes);

			verifyPolicy(organization);
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