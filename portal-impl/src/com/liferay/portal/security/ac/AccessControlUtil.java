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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.security.auth.AccessControlContext;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthVerifierResult;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tomas Polesovsky
 * @author Michael C. Han
 * @author Raymond Augé
 */
public class AccessControlUtil {

	public static AccessControl getAccessControl() {
		if (_accessControl == null) {
			_accessControl = new AccessControlImpl();
		}

		return _accessControl;
	}

	public static AccessControlContext getAccessControlContext() {
		return _accessControlContext.get();
	}

	public static void initAccessControlContext(
		HttpServletRequest request, HttpServletResponse response,
		Map<String, Object> settings) {

		getAccessControl().initAccessControlContext(
			request, response, settings);
	}

	public static void initContextUser(long userId) throws AuthException {
		getAccessControl().initContextUser(userId);
	}

	public static void setAccessControlContext(
		AccessControlContext accessControlContext) {

		_accessControlContext.set(accessControlContext);
	}

	public static AuthVerifierResult.State verifyRequest()
		throws PortalException, SystemException {

		return getAccessControl().verifyRequest();
	}

	public void setAccessControl(AccessControl accessControl) {
		_accessControl = accessControl;
	}

	private static AccessControl _accessControl;
	private static ThreadLocal<AccessControlContext> _accessControlContext =
		new AutoResetThreadLocal<AccessControlContext>(
			AccessControlUtil.class + "._accessControlContext");

}