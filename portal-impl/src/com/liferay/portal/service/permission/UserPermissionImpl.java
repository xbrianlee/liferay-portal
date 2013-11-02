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

package com.liferay.portal.service.permission;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Charles May
 * @author Jorge Ferrer
 */
public class UserPermissionImpl implements UserPermission {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #check(PermissionChecker,
	 *             long, long[], String)}
	 */
	@Override
	public void check(
			PermissionChecker permissionChecker, long userId,
			long organizationId, long locationId, String actionId)
		throws PrincipalException {

		check(
			permissionChecker, userId, new long[] {organizationId, locationId},
			actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long userId,
			long[] organizationIds, String actionId)
		throws PrincipalException {

		if (!contains(permissionChecker, userId, organizationIds, actionId)) {
			throw new PrincipalException();
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long userId, String actionId)
		throws PrincipalException {

		if (!contains(permissionChecker, userId, actionId)) {
			throw new PrincipalException();
		}
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #contains(PermissionChecker,
	 *             long, long[], String)}
	 */
	@Override
	public boolean contains(
		PermissionChecker permissionChecker, long userId, long organizationId,
		long locationId, String actionId) {

		return contains(
			permissionChecker, userId, new long[] {organizationId, locationId},
			actionId);
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, long userId,
		long[] organizationIds, String actionId) {

		if ((actionId.equals(ActionKeys.DELETE) ||
			 actionId.equals(ActionKeys.IMPERSONATE) ||
			 actionId.equals(ActionKeys.PERMISSIONS) ||
			 actionId.equals(ActionKeys.UPDATE)) &&
			PortalUtil.isOmniadmin(userId) &&
			!permissionChecker.isOmniadmin()) {

			return false;
		}

		try {
			User user = null;

			if (userId != ResourceConstants.PRIMKEY_DNE) {
				user = UserLocalServiceUtil.getUserById(userId);

				Contact contact = user.getContact();

				if (permissionChecker.hasOwnerPermission(
						permissionChecker.getCompanyId(), User.class.getName(),
						userId, contact.getUserId(), actionId) ||
					(permissionChecker.getUserId() == userId)) {

					return true;
				}
			}

			if (permissionChecker.hasPermission(
					0, User.class.getName(), userId, actionId)) {

				return true;
			}

			if (user == null) {
				return false;
			}

			if (organizationIds == null) {
				organizationIds = user.getOrganizationIds();
			}

			for (long organizationId : organizationIds) {
				if (OrganizationPermissionUtil.contains(
						permissionChecker, organizationId,
						ActionKeys.MANAGE_USERS)) {

					if (permissionChecker.getUserId() == user.getUserId()) {
						return true;
					}

					Organization organization =
						OrganizationLocalServiceUtil.getOrganization(
							organizationId);

					Group organizationGroup = organization.getGroup();

					// Organization administrators can only manage normal users.
					// Owners can only manage normal users and administrators.

					if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
							user.getUserId(), organizationGroup.getGroupId(),
							RoleConstants.ORGANIZATION_OWNER, true)) {

						continue;
					}
					else if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
								user.getUserId(),
								organizationGroup.getGroupId(),
								RoleConstants.ORGANIZATION_ADMINISTRATOR,
								true) &&
							 !UserGroupRoleLocalServiceUtil.hasUserGroupRole(
								permissionChecker.getUserId(),
								organizationGroup.getGroupId(),
								RoleConstants.ORGANIZATION_OWNER, true)) {

						continue;
					}

					return true;
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, long userId, String actionId) {

		return contains(permissionChecker, userId, null, actionId);
	}

	private static Log _log = LogFactoryUtil.getLog(UserPermissionImpl.class);

}