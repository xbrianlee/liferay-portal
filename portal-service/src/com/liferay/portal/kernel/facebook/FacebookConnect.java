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

package com.liferay.portal.kernel.facebook;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;

import javax.portlet.PortletRequest;

/**
 * @author Wilson Man
 * @author Brian Wing Shun Chan
 * @author Mika Koivisto
 */
public interface FacebookConnect {

	public String getAccessToken(long companyId, String redirect, String code)
		throws SystemException;

	public String getAccessTokenURL(long companyId) throws SystemException;

	public String getAppId(long companyId) throws SystemException;

	public String getAppSecret(long companyId) throws SystemException;

	public String getAuthURL(long companyId) throws SystemException;

	public JSONObject getGraphResources(
		long companyId, String path, String accessToken, String fields);

	public String getGraphURL(long companyId) throws SystemException;

	public String getProfileImageURL(PortletRequest portletRequest);

	public String getRedirectURL(long companyId) throws SystemException;

	public boolean isEnabled(long companyId) throws SystemException;

	public boolean isVerifiedAccountRequired(long companyId)
		throws SystemException;

}