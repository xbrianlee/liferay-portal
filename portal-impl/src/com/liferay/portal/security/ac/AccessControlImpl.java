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
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AccessControlContext;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthVerifierPipeline;
import com.liferay.portal.security.auth.AuthVerifierResult;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public class AccessControlImpl implements AccessControl {

	@Override
	public void initAccessControlContext(
		HttpServletRequest request, HttpServletResponse response,
		Map<String, Object> settings) {

		AccessControlContext accessControlContext =
			AccessControlUtil.getAccessControlContext();

		if (accessControlContext != null) {
			throw new IllegalStateException(
				"Authentication context is already initialized");
		}

		accessControlContext = new AccessControlContext();

		accessControlContext.setRequest(request);
		accessControlContext.setResponse(response);

		Map<String, Object> accessControlContextSettings =
			accessControlContext.getSettings();

		accessControlContextSettings.putAll(settings);

		AccessControlUtil.setAccessControlContext(accessControlContext);
	}

	@Override
	public void initContextUser(long userId) throws AuthException {
		try {
			User user = UserLocalServiceUtil.getUser(userId);

			CompanyThreadLocal.setCompanyId(user.getCompanyId());

			PrincipalThreadLocal.setName(userId);

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			AccessControlThreadLocal.setRemoteAccess(false);
		}
		catch (Exception e) {
			throw new AuthException(e.getMessage(), e);
		}
	}

	@Override
	public AuthVerifierResult.State verifyRequest()
		throws PortalException, SystemException {

		AccessControlContext accessControlContext =
			AccessControlUtil.getAccessControlContext();

		AuthVerifierResult authVerifierResult =
			AuthVerifierPipeline.verifyRequest(accessControlContext);

		Map<String, Object> authVerifierResultSettings =
			authVerifierResult.getSettings();

		if (authVerifierResultSettings != null) {
			Map<String, Object> settings = accessControlContext.getSettings();

			settings.putAll(authVerifierResultSettings);
		}

		accessControlContext.setAuthVerifierResult(authVerifierResult);

		return authVerifierResult.getState();
	}

}