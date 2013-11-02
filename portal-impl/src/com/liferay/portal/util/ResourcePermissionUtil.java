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

package com.liferay.portal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceBlock;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;

import java.util.List;

/**
 * @author Juan Fernández
 * @author Sergio González
 */
public class ResourcePermissionUtil {

	public static void populateResourcePermissionActionIds(
			long groupId, Role role, Resource resource, List<String> actions,
			List<String> individualActions, List<String> groupActions,
			List<String> groupTemplateActions, List<String> companyActions)
		throws PortalException, SystemException {

		if (ResourceBlockLocalServiceUtil.isSupported(resource.getName())) {
			ResourceBlock resourceBlock =
				ResourceBlockLocalServiceUtil.getResourceBlock(
					resource.getName(), Long.valueOf(resource.getPrimKey()));

			// Individual actions are not stored separately, so
			// individualActions will include group and company actions as well

			individualActions.addAll(
				ResourceBlockLocalServiceUtil.getPermissions(
					resourceBlock, role.getRoleId()));
			groupActions.addAll(
				ResourceBlockLocalServiceUtil.getGroupScopePermissions(
					resourceBlock, role.getRoleId()));

			// Resource blocks do not distinguish between company scope and
			// group template scope permissions, so the distinction must be
			// simulated here

			if (role.getType() == RoleConstants.TYPE_REGULAR) {
				companyActions.addAll(
					ResourceBlockLocalServiceUtil.getCompanyScopePermissions(
						resourceBlock, role.getRoleId()));
			}
			else {
				groupTemplateActions.addAll(
					ResourceBlockLocalServiceUtil.getCompanyScopePermissions(
						resourceBlock, role.getRoleId()));
			}
		}
		else {
			individualActions.addAll(
				ResourcePermissionLocalServiceUtil.
					getAvailableResourcePermissionActionIds(
						resource.getCompanyId(), resource.getName(),
						resource.getScope(), resource.getPrimKey(),
						role.getRoleId(), actions));

			groupActions.addAll(
				ResourcePermissionLocalServiceUtil.
					getAvailableResourcePermissionActionIds(
						resource.getCompanyId(), resource.getName(),
						ResourceConstants.SCOPE_GROUP, String.valueOf(groupId),
						role.getRoleId(), actions));

			groupTemplateActions.addAll(
				ResourcePermissionLocalServiceUtil.
					getAvailableResourcePermissionActionIds(
						resource.getCompanyId(), resource.getName(),
						ResourceConstants.SCOPE_GROUP_TEMPLATE, "0",
						role.getRoleId(), actions));

			companyActions.addAll(
				ResourcePermissionLocalServiceUtil.
					getAvailableResourcePermissionActionIds(
						resource.getCompanyId(), resource.getName(),
						ResourceConstants.SCOPE_COMPANY,
						String.valueOf(resource.getCompanyId()),
						role.getRoleId(), actions));
		}
	}

}