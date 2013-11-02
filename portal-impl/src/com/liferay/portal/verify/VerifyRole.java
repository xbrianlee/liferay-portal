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

package com.liferay.portal.verify;

import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.util.PortalInstances;

/**
 * @author Brian Wing Shun Chan
 */
public class VerifyRole extends VerifyProcess {

	protected void addViewSiteAdministrationPermission(Role role)
		throws Exception {

		String name = Group.class.getName();

		Group group = GroupLocalServiceUtil.getGroup(
			role.getCompanyId(), GroupConstants.USER_PERSONAL_SITE);

		String primKey = String.valueOf(group.getGroupId());

		if (!ResourcePermissionLocalServiceUtil.hasResourcePermission(
				role.getCompanyId(), name, ResourceConstants.SCOPE_GROUP,
				primKey, role.getRoleId(), ActionKeys.MANAGE_LAYOUTS) ||
			ResourcePermissionLocalServiceUtil.hasResourcePermission(
				role.getCompanyId(), name, ResourceConstants.SCOPE_GROUP,
				primKey, role.getRoleId(),
				ActionKeys.VIEW_SITE_ADMINISTRATION)) {

			return;
		}

		ResourcePermissionLocalServiceUtil.addResourcePermission(
			role.getCompanyId(), name, ResourceConstants.SCOPE_GROUP, primKey,
			role.getRoleId(), ActionKeys.VIEW_SITE_ADMINISTRATION);
	}

	protected void deleteImplicitAssociations(Role role) throws Exception {
		runSQL(
			"delete from UserGroupGroupRole where roleId = " +
				role.getRoleId());
		runSQL("delete from UserGroupRole where roleId = " + role.getRoleId());
	}

	@Override
	protected void doVerify() throws Exception {
		long[] companyIds = PortalInstances.getCompanyIdsBySQL();

		for (long companyId : companyIds) {
			RoleLocalServiceUtil.checkSystemRoles(companyId);

			try {
				Role organizationUserRole = RoleLocalServiceUtil.getRole(
					companyId, RoleConstants.ORGANIZATION_USER);

				deleteImplicitAssociations(organizationUserRole);
			}
			catch (NoSuchRoleException nsre) {
			}

			try {
				Role powerUserRole = RoleLocalServiceUtil.getRole(
					companyId, RoleConstants.POWER_USER);

				addViewSiteAdministrationPermission(powerUserRole);
			}
			catch (NoSuchRoleException nsre) {
			}

			try {
				Role siteMemberRole = RoleLocalServiceUtil.getRole(
					companyId, RoleConstants.SITE_MEMBER);

				deleteImplicitAssociations(siteMemberRole);
			}
			catch (NoSuchRoleException nsre) {
			}
		}
	}

}