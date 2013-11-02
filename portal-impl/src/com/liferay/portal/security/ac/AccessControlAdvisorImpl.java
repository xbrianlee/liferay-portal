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

package com.liferay.portal.security.ac;

import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.auth.AccessControlContext;
import com.liferay.portal.security.auth.AuthSettingsUtil;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;

import java.lang.reflect.Method;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tomas Polesovsky
 * @author Igor Spasic
 * @author Michael C. Han
 * @author Raymond Augé
 */
public class AccessControlAdvisorImpl implements AccessControlAdvisor {

	@Override
	public void accept(Method method, AccessControlled accessControlled)
		throws SecurityException {

		if (accessControlled.hostAllowedValidationEnabled()) {
			checkAllowedHosts();
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!accessControlled.guestAccessEnabled() &&
			((permissionChecker == null) || !permissionChecker.isSignedIn())) {

			throw new SecurityException("Authenticated access required");
		}
	}

	protected void checkAllowedHosts() {
		AccessControlContext accessControlContext =
			AccessControlUtil.getAccessControlContext();

		if (accessControlContext == null) {
			return;
		}

		HttpServletRequest request = accessControlContext.getRequest();

		String hostsAllowedString = MapUtil.getString(
			accessControlContext.getSettings(), "hosts.allowed");

		String[] hostsAllowed = StringUtil.split(hostsAllowedString);

		Set<String> hostsAllowedSet = SetUtil.fromArray(hostsAllowed);

		if (!AuthSettingsUtil.isAccessAllowed(request, hostsAllowedSet)) {
			throw new SecurityException(
				"Access denied for " + request.getRemoteAddr());
		}
	}

}