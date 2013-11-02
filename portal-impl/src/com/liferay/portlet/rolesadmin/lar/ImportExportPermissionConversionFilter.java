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

package com.liferay.portlet.rolesadmin.lar;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.model.ResourceTypePermission;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.PermissionConversionFilter;
import com.liferay.portal.service.GroupLocalServiceUtil;

/**
 * @author Michael C. Han
 */
public class ImportExportPermissionConversionFilter
	implements PermissionConversionFilter {

	@Override
	public boolean accept(Role role, ResourcePermission resourcePermission)
		throws SystemException {

		int scope = resourcePermission.getScope();

		if ((scope == ResourceConstants.SCOPE_COMPANY) ||
			(scope == ResourceConstants.SCOPE_GROUP_TEMPLATE)) {

			return true;
		}
		else if (resourcePermission.getScope() ==
					ResourceConstants.SCOPE_GROUP) {

			Group group = GroupLocalServiceUtil.fetchGroup(
				Long.valueOf(resourcePermission.getPrimKey()));

			if (group.isCompany() || group.isUserPersonalSite()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean accept(
			Role role, ResourceTypePermission resourceTypePermission)
		throws SystemException {

		if (role.getType() != RoleConstants.TYPE_REGULAR) {
			return true;
		}
		else if (resourceTypePermission.isCompanyScope()) {
			return true;
		}

		Group group = GroupLocalServiceUtil.fetchGroup(
			resourceTypePermission.getGroupId());

		if ((group != null) &&
			(group.isCompany() || group.isUserPersonalSite())) {

			return true;
		}

		return false;
	}

}