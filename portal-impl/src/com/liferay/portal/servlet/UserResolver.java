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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalInstances;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Spasic
 */
public class UserResolver {

	public UserResolver(HttpServletRequest request)
		throws PortalException, SystemException {

		_companyId = ParamUtil.getLong(request, "companyId");

		String remoteUser = request.getRemoteUser();

		long userId = GetterUtil.getLong(remoteUser);

		if (userId == 0) {
			remoteUser = null;
		}

		if (remoteUser != null) {
			PrincipalThreadLocal.setName(remoteUser);

			_user = UserLocalServiceUtil.getUserById(userId);

			if (_companyId == 0) {
				_companyId = _user.getCompanyId();
			}
		}
		else {
			if (_companyId == 0) {
				_companyId = PortalInstances.getCompanyId(request);
			}

			if (_companyId != 0) {
				_user = UserLocalServiceUtil.getDefaultUser(_companyId);
			}
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public User getUser() {
		return _user;
	}

	private long _companyId;
	private User _user;

}