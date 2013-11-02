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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.pwd.PasswordEncryptorUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Minhchau Dang
 * @author Tomas Polesovsky
 */
public class ParameterAutoLogin extends BaseAutoLogin implements AuthVerifier {

	@Override
	public String getAuthType() {
		return ParameterAutoLogin.class.getSimpleName();
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		try {
			AuthVerifierResult authVerifierResult = new AuthVerifierResult();

			String[] credentials = login(
				accessControlContext.getRequest(),
				accessControlContext.getResponse());

			if (credentials != null) {
				authVerifierResult.setPassword(credentials[1]);
				authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
				authVerifierResult.setUserId(Long.valueOf(credentials[0]));
			}

			return authVerifierResult;
		}
		catch (AutoLoginException ale) {
			throw new AuthException(ale);
		}
	}

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String login = ParamUtil.getString(request, getLoginParam());

		if (Validator.isNull(login)) {
			return null;
		}

		String password = ParamUtil.getString(request, getPasswordParam());

		if (Validator.isNull(password)) {
			return null;
		}

		Company company = PortalUtil.getCompany(request);

		String authType = company.getAuthType();

		long userId = 0;

		if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
			userId = UserLocalServiceUtil.getUserIdByEmailAddress(
				company.getCompanyId(), login);
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			userId = UserLocalServiceUtil.getUserIdByScreenName(
				company.getCompanyId(), login);
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			userId = GetterUtil.getLong(login);
		}
		else {
			return null;
		}

		if (userId > 0) {
			User user = UserLocalServiceUtil.getUserById(userId);

			String userPassword = user.getPassword();

			if (!user.isPasswordEncrypted()) {
				userPassword = PasswordEncryptorUtil.encrypt(userPassword);
			}

			String encPassword = PasswordEncryptorUtil.encrypt(
				password, userPassword);

			if (!userPassword.equals(password) &&
				!userPassword.equals(encPassword)) {

				return null;
			}
		}

		String[] credentials = new String[] {
			String.valueOf(userId), password, Boolean.FALSE.toString()
		};

		return credentials;
	}

	protected String getLoginParam() {
		return _LOGIN_PARAM;
	}

	protected String getPasswordParam() {
		return _PASSWORD_PARAM;
	}

	private static final String _LOGIN_PARAM = "parameterAutoLoginLogin";

	private static final String _PASSWORD_PARAM = "parameterAutoLoginPassword";

}