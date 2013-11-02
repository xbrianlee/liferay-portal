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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Team;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.TeamLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class TeamPermissionImpl implements TeamPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, long teamId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, teamId, actionId)) {
			throw new PrincipalException();
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, Team team, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, team, actionId)) {
			throw new PrincipalException();
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long teamId, String actionId)
		throws PortalException, SystemException {

		Team team = TeamLocalServiceUtil.getTeam(teamId);

		return contains(permissionChecker, team, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, Team team, String actionId)
		throws PortalException, SystemException {

		if (GroupPermissionUtil.contains(
				permissionChecker, team.getGroupId(),
				ActionKeys.MANAGE_TEAMS)) {

			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				team.getCompanyId(), Team.class.getName(), team.getTeamId(),
				team.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			team.getGroupId(), Team.class.getName(), team.getTeamId(),
			actionId);
	}

}