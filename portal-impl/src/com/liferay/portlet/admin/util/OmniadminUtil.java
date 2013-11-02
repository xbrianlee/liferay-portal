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

package com.liferay.portlet.admin.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PropsValues;

/**
 * Provides utility methods for determining if a user is a universal
 * administrator. Universal administrators have administrator permissions in
 * every company.
 *
 * <p>
 * A user can be made a universal administrator by adding their primary key to
 * the list in <code>portal.properties</code> under the key
 * <code>omniadmin.users</key>. If this property is left blank, administrators
 * of the default company will automatically be universal administrators.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class OmniadminUtil {

	public static boolean isOmniadmin(long userId) {
		try {
			User user = UserLocalServiceUtil.fetchUser(userId);

			if (user == null) {
				return false;
			}

			return isOmniadmin(user);
		}
		catch (SystemException se) {
			return false;
		}
	}

	public static boolean isOmniadmin(User user) {
		if (CompanyThreadLocal.getCompanyId() !=
				PortalInstances.getDefaultCompanyId()) {

			return false;
		}

		long userId = user.getUserId();

		if (userId <= 0) {
			return false;
		}

		try {
			if (PropsValues.OMNIADMIN_USERS.length > 0) {
				for (int i = 0; i < PropsValues.OMNIADMIN_USERS.length; i++) {
					if (PropsValues.OMNIADMIN_USERS[i] == userId) {
						if (user.getCompanyId() !=
								PortalInstances.getDefaultCompanyId()) {

							return false;
						}

						return true;
					}
				}

				return false;
			}

			if (user.getCompanyId() !=
					PortalInstances.getDefaultCompanyId()) {

				return false;
			}

			return RoleLocalServiceUtil.hasUserRole(
				userId, user.getCompanyId(), RoleConstants.ADMINISTRATOR, true);
		}
		catch (Exception e) {
			_log.error(e);

			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(OmniadminUtil.class);

}