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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Account;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Charles May
 */
public class CommonPermissionImpl implements CommonPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, long classNameId, long classPK,
			String actionId)
		throws PortalException, SystemException {

		String className = PortalUtil.getClassName(classNameId);

		check(permissionChecker, className, classPK, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, String className, long classPK,
			String actionId)
		throws PortalException, SystemException {

		if (className.equals(Account.class.getName())) {
		}
		else if (className.equals(Company.class.getName())) {
		}
		else if (className.equals(Contact.class.getName())) {
			User user = UserLocalServiceUtil.getUserByContactId(classPK);

			UserPermissionUtil.check(
				permissionChecker, user.getUserId(), actionId);
		}
		else if (className.equals(Organization.class.getName())) {
			OrganizationPermissionUtil.check(
				permissionChecker, classPK, actionId);
		}
		else if (className.equals(User.class.getName())) {
			UserPermissionUtil.check(permissionChecker, classPK, actionId);
		}
		else {
			if (_log.isWarnEnabled()) {
				_log.warn("Invalid class name " + className);
			}

			throw new PrincipalException();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommonPermissionImpl.class);

}