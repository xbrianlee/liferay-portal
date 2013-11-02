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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ldap.PortalLDAPImporterUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mika Koivisto
 * @author Wesley Gong
 */
public class SiteMinderAutoLogin extends BaseAutoLogin {

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		Company company = PortalUtil.getCompany(request);

		long companyId = company.getCompanyId();

		if (!AuthSettingsUtil.isSiteMinderEnabled(companyId)) {
			return null;
		}

		String siteMinderUserHeader = request.getHeader(
			PrefsPropsUtil.getString(
				companyId, PropsKeys.SITEMINDER_USER_HEADER,
				PropsValues.SITEMINDER_USER_HEADER));

		if (Validator.isNull(siteMinderUserHeader)) {
			return null;
		}

		String authType = company.getAuthType();

		User user = null;

		if (PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.SITEMINDER_IMPORT_FROM_LDAP,
				PropsValues.SITEMINDER_IMPORT_FROM_LDAP)) {

			try {
				if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					user = PortalLDAPImporterUtil.importLDAPUser(
						companyId, siteMinderUserHeader, StringPool.BLANK);
				}
				else {
					user = PortalLDAPImporterUtil.importLDAPUser(
						companyId, StringPool.BLANK, siteMinderUserHeader);
				}
			}
			catch (SystemException se) {
			}
		}

		if (user == null) {
			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				user = UserLocalServiceUtil.fetchUserByEmailAddress(
					companyId, siteMinderUserHeader);
			}
			else {
				user = UserLocalServiceUtil.fetchUserByScreenName(
					companyId, siteMinderUserHeader);
			}
		}

		String[] credentials = new String[3];

		credentials[0] = String.valueOf(user.getUserId());
		credentials[1] = user.getPassword();
		credentials[2] = Boolean.TRUE.toString();

		return credentials;
	}

}