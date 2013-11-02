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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Team;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.base.TeamServiceBaseImpl;
import com.liferay.portal.service.permission.GroupPermissionUtil;
import com.liferay.portal.service.permission.TeamPermissionUtil;
import com.liferay.portal.service.permission.UserPermissionUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class TeamServiceImpl extends TeamServiceBaseImpl {

	@Override
	public Team addTeam(long groupId, String name, String description)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.MANAGE_TEAMS);

		return teamLocalService.addTeam(
			getUserId(), groupId, name, description);
	}

	@Override
	public void deleteTeam(long teamId)
		throws PortalException, SystemException {

		TeamPermissionUtil.check(
			getPermissionChecker(), teamId, ActionKeys.DELETE);

		teamLocalService.deleteTeam(teamId);
	}

	@Override
	public List<Team> getGroupTeams(long groupId)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.MANAGE_TEAMS);

		return teamLocalService.getGroupTeams(groupId);
	}

	@Override
	public Team getTeam(long teamId) throws PortalException, SystemException {
		TeamPermissionUtil.check(
			getPermissionChecker(), teamId, ActionKeys.VIEW);

		return teamLocalService.getTeam(teamId);
	}

	@Override
	public Team getTeam(long groupId, String name)
		throws PortalException, SystemException {

		Team team = teamLocalService.getTeam(groupId, name);

		TeamPermissionUtil.check(getPermissionChecker(), team, ActionKeys.VIEW);

		return team;
	}

	@Override
	public List<Team> getUserTeams(long userId)
		throws PortalException, SystemException {

		UserPermissionUtil.check(
			getPermissionChecker(), userId, ActionKeys.UPDATE);

		return teamLocalService.getUserTeams(userId);
	}

	@Override
	public List<Team> getUserTeams(long userId, long groupId)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.MANAGE_TEAMS);

		return teamLocalService.getUserTeams(userId, groupId);
	}

	@Override
	public boolean hasUserTeam(long userId, long teamId)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		Team team = teamPersistence.findByPrimaryKey(teamId);

		if (!GroupPermissionUtil.contains(
				permissionChecker, team.getGroupId(),
				ActionKeys.MANAGE_TEAMS) &&
			!UserPermissionUtil.contains(
				permissionChecker, userId, ActionKeys.UPDATE)) {

			throw new PrincipalException();
		}

		return userPersistence.containsTeam(userId, teamId);
	}

	@Override
	public Team updateTeam(long teamId, String name, String description)
		throws PortalException, SystemException {

		TeamPermissionUtil.check(
			getPermissionChecker(), teamId, ActionKeys.UPDATE);

		return teamLocalService.updateTeam(teamId, name, description);
	}

}