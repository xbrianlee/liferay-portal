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

package com.liferay.portlet.sites.search;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.UserGroupGroupRoleLocalServiceUtil;

import javax.portlet.RenderResponse;

/**
 * @author Brett Swaim
 */
public class UserGroupGroupRoleRoleChecker extends RowChecker {

	public UserGroupGroupRoleRoleChecker(
		RenderResponse renderResponse, UserGroup userGroup, Group group) {

		super(renderResponse);

		_userGroup = userGroup;
		_group = group;
	}

	@Override
	public boolean isChecked(Object obj) {
		Role role = (Role)obj;

		try {
			return UserGroupGroupRoleLocalServiceUtil.hasUserGroupGroupRole(
				_userGroup.getUserGroupId(), _group.getGroupId(),
				role.getRoleId());
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		UserGroupGroupRoleRoleChecker.class);

	private Group _group;
	private UserGroup _userGroup;

}