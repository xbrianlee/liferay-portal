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

import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;

import java.io.Serializable;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface PermissionCheckerBag extends Serializable {

	public List<Group> getGroups();

	public long[] getRoleIds();

	public List<Role> getRoles();

	public List<Group> getUserGroups();

	public List<Group> getUserOrgGroups();

	public List<Organization> getUserOrgs();

	public List<Group> getUserUserGroupGroups();

	/**
	 * @deprecated As of 6.1.0, renamed to {@link
	 *             #isGroupAdmin(PermissionChecker, Group)}
	 */
	public boolean isCommunityAdmin(
			PermissionChecker permissionChecker, Group group)
		throws Exception;

	/**
	 * @deprecated As of 6.1.0, renamed to {@link
	 *             #isGroupOwner(PermissionChecker, Group)}
	 */
	public boolean isCommunityOwner(
			PermissionChecker permissionChecker, Group group)
		throws Exception;

	public boolean isGroupAdmin(
			PermissionChecker permissionChecker, Group group)
		throws Exception;

	public boolean isGroupMember(
			PermissionChecker permissionChecker, Group group)
		throws Exception;

	public boolean isGroupOwner(
			PermissionChecker permissionChecker, Group group)
		throws Exception;

	public boolean isOrganizationAdmin(
			PermissionChecker permissionChecker, Organization organization)
		throws Exception;

	public boolean isOrganizationOwner(
			PermissionChecker permissionChecker, Organization organization)
		throws Exception;

}