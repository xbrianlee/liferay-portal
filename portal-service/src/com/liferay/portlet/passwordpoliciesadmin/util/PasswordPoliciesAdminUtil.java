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

package com.liferay.portlet.passwordpoliciesadmin.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class PasswordPoliciesAdminUtil {

	public static PasswordPoliciesAdmin getPasswordPoliciesAdmin() {
		PortalRuntimePermission.checkGetBeanProperty(
			PasswordPoliciesAdminUtil.class);

		return _passwordPoliciesAdmin;
	}

	public static OrderByComparator getPasswordPolicyOrderByComparator(
		String orderByCol, String orderByType) {

		return getPasswordPoliciesAdmin().getPasswordPolicyOrderByComparator(
			orderByCol, orderByType);
	}

	public void setPasswordPoliciesAdmin(
		PasswordPoliciesAdmin passwordPoliciesAdmin) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_passwordPoliciesAdmin = passwordPoliciesAdmin;
	}

	private static PasswordPoliciesAdmin _passwordPoliciesAdmin;

}