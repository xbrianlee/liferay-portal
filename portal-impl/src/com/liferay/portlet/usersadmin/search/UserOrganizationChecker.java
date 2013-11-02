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

package com.liferay.portlet.usersadmin.search;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.security.membershippolicy.OrganizationMembershipPolicyUtil;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.UserPermissionUtil;
import com.liferay.portal.util.PropsValues;

import javax.portlet.RenderResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class UserOrganizationChecker extends RowChecker {

	public UserOrganizationChecker(
		RenderResponse renderResponse, Organization organization) {

		super(renderResponse);

		_organization = organization;
	}

	@Override
	public boolean isChecked(Object obj) {
		User user = (User)obj;

		try {
			return UserLocalServiceUtil.hasOrganizationUser(
				_organization.getOrganizationId(), user.getUserId());
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	@Override
	public boolean isDisabled(Object obj) {
		if (!PropsValues.ORGANIZATIONS_ASSIGNMENT_STRICT) {
			return false;
		}

		User user = (User)obj;

		try {
			PermissionChecker permissionChecker =
				PermissionThreadLocal.getPermissionChecker();

			if (isChecked(user)) {
				if (OrganizationMembershipPolicyUtil.isMembershipProtected(
						permissionChecker, user.getUserId(),
						_organization.getOrganizationId()) ||
					OrganizationMembershipPolicyUtil.isMembershipRequired(
						user.getUserId(), _organization.getOrganizationId())) {

					return true;
				}
			}
			else {
				if (!OrganizationMembershipPolicyUtil.isMembershipAllowed(
						user.getUserId(), _organization.getOrganizationId())) {

					return true;
				}
			}

			return !UserPermissionUtil.contains(
				permissionChecker, user.getUserId(), ActionKeys.UPDATE);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return super.isDisabled(obj);
	}

	private static Log _log = LogFactoryUtil.getLog(
		UserOrganizationChecker.class);

	private Organization _organization;

}