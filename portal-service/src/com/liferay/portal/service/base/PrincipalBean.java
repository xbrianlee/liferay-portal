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

package com.liferay.portal.service.base;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of 6.2.0, replaced by {@link
 *             com.liferay.portal.service.BaseServiceImpl}
 */
public class PrincipalBean {

	public static final String[] ANONYMOUS_NAMES = {
		PrincipalBean.JRUN_ANONYMOUS, PrincipalBean.ORACLE_ANONYMOUS,
		PrincipalBean.SUN_ANONYMOUS, PrincipalBean.WEBLOGIC_ANONYMOUS
	};

	public static final String JRUN_ANONYMOUS = "anonymous-guest";

	public static final String ORACLE_ANONYMOUS = "guest";

	public static final String SUN_ANONYMOUS = "ANONYMOUS";

	public static final String WEBLOGIC_ANONYMOUS = "<anonymous>";

	public User getGuestOrUser() throws PortalException, SystemException {
		try {
			return getUser();
		}
		catch (PrincipalException pe) {
			try {
				return UserLocalServiceUtil.getDefaultUser(
					CompanyThreadLocal.getCompanyId());
			}
			catch (Exception e) {
				throw pe;
			}
		}
	}

	public long getGuestOrUserId() throws PrincipalException {
		try {
			return getUserId();
		}
		catch (PrincipalException pe) {
			try {
				return UserLocalServiceUtil.getDefaultUserId(
					CompanyThreadLocal.getCompanyId());
			}
			catch (Exception e) {
				throw pe;
			}
		}
	}

	public PermissionChecker getPermissionChecker() throws PrincipalException {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker == null) {
			throw new PrincipalException("PermissionChecker not initialized");
		}

		return permissionChecker;
	}

	public User getUser() throws PortalException, SystemException {
		return UserLocalServiceUtil.getUserById(getUserId());
	}

	public long getUserId() throws PrincipalException {
		String name = PrincipalThreadLocal.getName();

		if (name == null) {
			throw new PrincipalException();
		}

		if (Validator.isNull(name)) {
			throw new PrincipalException("Principal is null");
		}
		else {
			for (int i = 0; i < ANONYMOUS_NAMES.length; i++) {
				if (StringUtil.equalsIgnoreCase(name, ANONYMOUS_NAMES[i])) {
					throw new PrincipalException(
						"Principal cannot be " + ANONYMOUS_NAMES[i]);
				}
			}
		}

		return GetterUtil.getLong(name);
	}

}