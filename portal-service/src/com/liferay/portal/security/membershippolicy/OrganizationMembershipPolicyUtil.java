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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetTag;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Roberto Díaz
 */
public class OrganizationMembershipPolicyUtil {

	public static void checkMembership(
			long[] userIds, long[] addOrganizationIds,
			long[] removeOrganizationIds)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		organizationMembershipPolicy.checkMembership(
			userIds, addOrganizationIds, removeOrganizationIds);
	}

	public static void checkRoles(
			List<UserGroupRole> addUserGroupRoles,
			List<UserGroupRole> removeUserGroupRoles)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		organizationMembershipPolicy.checkRoles(
			addUserGroupRoles, removeUserGroupRoles);
	}

	public static boolean isMembershipAllowed(long userId, long organizationId)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		return organizationMembershipPolicy.isMembershipAllowed(
			userId, organizationId);
	}

	public static boolean isMembershipProtected(
			PermissionChecker permissionChecker, long userId,
			long organizationId)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		return organizationMembershipPolicy.isMembershipProtected(
			permissionChecker, userId, organizationId);
	}

	public static boolean isMembershipRequired(long userId, long organizationId)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		return organizationMembershipPolicy.isMembershipRequired(
			userId, organizationId);
	}

	public static boolean isRoleAllowed(
			long userId, long organizationId, long roleId)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		return organizationMembershipPolicy.isRoleAllowed(
			userId, organizationId, roleId);
	}

	public static boolean isRoleProtected(
			PermissionChecker permissionChecker, long userId,
			long organizationId, long roleId)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		return organizationMembershipPolicy.isRoleProtected(
			permissionChecker, userId, organizationId, roleId);
	}

	public static boolean isRoleRequired(
			long userId, long organizationId, long roleId)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		return organizationMembershipPolicy.isRoleRequired(
			userId, organizationId, roleId);
	}

	public static void propagateMembership(
			long[] userIds, long[] addOrganizationIds,
			long[] removeOrganizationIds)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		organizationMembershipPolicy.propagateMembership(
			userIds, addOrganizationIds, removeOrganizationIds);
	}

	public static void propagateRoles(
			List<UserGroupRole> addUserGroupRoles,
			List<UserGroupRole> removeUserGroupRoles)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		organizationMembershipPolicy.propagateRoles(
			addUserGroupRoles, removeUserGroupRoles);
	}

	public static void verifyPolicy() throws PortalException, SystemException {
		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		organizationMembershipPolicy.verifyPolicy();
	}

	public static void verifyPolicy(Organization organization)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		organizationMembershipPolicy.verifyPolicy(organization);
	}

	public static void verifyPolicy(
			Organization organization, Organization oldOrganization,
			List<AssetCategory> oldAssetCategories, List<AssetTag> oldAssetTags,
			Map<String, Serializable> oldExpandoAttributes)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		organizationMembershipPolicy.verifyPolicy(
			organization, oldOrganization, oldAssetCategories, oldAssetTags,
			oldExpandoAttributes);
	}

	public static void verifyPolicy(Role role)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		organizationMembershipPolicy.verifyPolicy(role);
	}

	public static void verifyPolicy(
			Role role, Role oldRole,
			Map<String, Serializable> oldExpandoAttributes)
		throws PortalException, SystemException {

		OrganizationMembershipPolicy organizationMembershipPolicy =
			OrganizationMembershipPolicyFactoryUtil.
				getOrganizationMembershipPolicy();

		organizationMembershipPolicy.verifyPolicy(
			role, oldRole, oldExpandoAttributes);
	}

}