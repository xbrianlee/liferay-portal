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

package com.liferay.portal.security.jaas;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Raymond Augé
 */
public class JAASHelper {

	public static JAASHelper getInstance() {
		return _instance;
	}

	public static long getJaasUserId(long companyId, String name)
		throws PortalException, SystemException {

		return _instance.doGetJaasUserId(companyId, name);
	}

	public static void setInstance(JAASHelper instance) {
		_instance = instance;
	}

	protected long doGetJaasUserId(long companyId, String name)
		throws PortalException, SystemException {

		String jaasAuthType = PropsValues.PORTAL_JAAS_AUTH_TYPE;

		if (jaasAuthType.equals("login")) {
			Company company = CompanyLocalServiceUtil.getCompany(companyId);
			String authType = company.getAuthType();

			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				jaasAuthType = "emailAddress";
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				jaasAuthType = "screenName";
			}
			else {
				jaasAuthType = "userId";
			}
		}

		long userId = 0;

		if (jaasAuthType.equals("emailAddress")) {
			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				companyId, name);

			if (user != null) {
				userId = user.getUserId();
			}
		}
		else if (jaasAuthType.equals("screenName")) {
			User user = UserLocalServiceUtil.fetchUserByScreenName(
				companyId, name);

			if (user != null) {
				userId = user.getUserId();
			}
		}
		else {
			userId = GetterUtil.getLong(name);
		}

		return userId;
	}

	private static JAASHelper _instance = new JAASHelper();

}