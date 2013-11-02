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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.ResourceAction;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.model.ResourceTypePermission;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.impl.PermissionImpl;
import com.liferay.portal.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceTypePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class PermissionConverterImpl implements PermissionConverter {

	@Override
	public List<Permission> convertPermissions(long roleId)
		throws PortalException, SystemException {

		return convertPermissions(roleId, null);
	}

	@Override
	public List<Permission> convertPermissions(
			long roleId, PermissionConversionFilter permissionConversionFilter)
		throws PortalException, SystemException {

		Role role = RoleLocalServiceUtil.getRole(roleId);

		return convertPermissions(role, permissionConversionFilter);
	}

	@Override
	public List<Permission> convertPermissions(Role role)
		throws SystemException {

		return convertPermissions(role, null);
	}

	@Override
	public List<Permission> convertPermissions(
			Role role, PermissionConversionFilter permissionConversionFilter)
		throws SystemException {

		int[] scopes = new int[0];

		if (role.getType() == RoleConstants.TYPE_REGULAR) {
			scopes = new int[] {
				ResourceConstants.SCOPE_COMPANY, ResourceConstants.SCOPE_GROUP};
		}
		else if ((role.getType() == RoleConstants.TYPE_ORGANIZATION) ||
				 (role.getType() == RoleConstants.TYPE_PROVIDER) ||
				 (role.getType() == RoleConstants.TYPE_SITE)) {

			scopes = new int[] {ResourceConstants.SCOPE_GROUP_TEMPLATE};
		}

		List<Permission> permissions = new ArrayList<Permission>();

		List<ResourcePermission> resourcePermissions =
			ResourcePermissionLocalServiceUtil.getRoleResourcePermissions(
				role.getRoleId(), scopes, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ResourcePermission resourcePermission : resourcePermissions) {
			if ((permissionConversionFilter != null) &&
				!permissionConversionFilter.accept(role, resourcePermission)) {

				continue;
			}

			List<ResourceAction> resourceActions =
				ResourceActionLocalServiceUtil.getResourceActions(
					resourcePermission.getName());

			for (ResourceAction resourceAction : resourceActions) {
				if (ResourcePermissionLocalServiceUtil.hasActionId(
						resourcePermission, resourceAction)) {

					Permission permission = new PermissionImpl();

					permission.setName(resourcePermission.getName());
					permission.setScope(resourcePermission.getScope());
					permission.setPrimKey(resourcePermission.getPrimKey());
					permission.setActionId(resourceAction.getActionId());

					permissions.add(permission);
				}
			}
		}

		List<ResourceTypePermission> resourceTypePermissions =
			ResourceTypePermissionLocalServiceUtil.
				getRoleResourceTypePermissions(role.getRoleId());

		for (ResourceTypePermission resourceTypePermission :
				resourceTypePermissions) {

			if ((permissionConversionFilter != null) &&
				!permissionConversionFilter.accept(
					role, resourceTypePermission)) {

				continue;
			}

			List<String> actionIds = ResourceBlockLocalServiceUtil.getActionIds(
				resourceTypePermission.getName(),
				resourceTypePermission.getActionIds());

			for (String actionId : actionIds) {
				Permission permission = new PermissionImpl();

				permission.setName(resourceTypePermission.getName());

				if (role.getType() == RoleConstants.TYPE_REGULAR) {
					if (resourceTypePermission.isCompanyScope()) {
						permission.setScope(ResourceConstants.SCOPE_COMPANY);
					}
					else {
						permission.setScope(ResourceConstants.SCOPE_GROUP);
					}
				}
				else {
					permission.setScope(ResourceConstants.SCOPE_GROUP_TEMPLATE);
				}

				permission.setPrimKey(
					String.valueOf(resourceTypePermission.getGroupId()));

				permission.setActionId(actionId);

				permissions.add(permission);
			}
		}

		return permissions;
	}

}