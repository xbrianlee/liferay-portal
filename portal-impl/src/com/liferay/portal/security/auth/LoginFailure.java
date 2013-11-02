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

import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Scott Lee
 */
public class LoginFailure implements AuthFailure {

	@Override
	public void onFailureByEmailAddress(
			long companyId, String emailAddress,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			UserLocalServiceUtil.checkLoginFailureByEmailAddress(
				companyId, emailAddress);
		}
		catch (Exception e) {
			throw new AuthException(e);
		}
	}

	@Override
	public void onFailureByScreenName(
			long companyId, String screenName, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			UserLocalServiceUtil.checkLoginFailureByScreenName(
				companyId, screenName);
		}
		catch (Exception e) {
			throw new AuthException(e);
		}
	}

	@Override
	public void onFailureByUserId(
			long companyId, long userId, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			UserLocalServiceUtil.checkLoginFailureById(userId);
		}
		catch (Exception e) {
			throw new AuthException(e);
		}
	}

}