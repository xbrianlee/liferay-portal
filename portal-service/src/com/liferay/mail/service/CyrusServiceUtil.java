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

package com.liferay.mail.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Alexander Chow
 */
public class CyrusServiceUtil {

	public static void addUser(
			long userId, String emailAddress, String password)
		throws SystemException {

		getService().addUser(userId, emailAddress, password);
	}

	public static void deleteEmailAddress(long companyId, long userId)
		throws SystemException {

		getService().deleteEmailAddress(companyId, userId);
	}

	public static void deleteUser(long userId) throws SystemException {
		getService().deleteUser(userId);
	}

	public static CyrusService getService() {
		if (_service == null) {
			_service = (CyrusService)PortalBeanLocatorUtil.locate(
				CyrusService.class.getName());

			ReferenceRegistry.registerReference(
				CyrusServiceUtil.class, "_service");
		}

		return _service;
	}

	public static void updateEmailAddress(
			long companyId, long userId, String emailAddress)
		throws SystemException {

		getService().updateEmailAddress(companyId, userId, emailAddress);
	}

	public static void updatePassword(
			long companyId, long userId, String password)
		throws SystemException {

		getService().updatePassword(companyId, userId, password);
	}

	public void setService(CyrusService service) {
		_service = service;

		ReferenceRegistry.registerReference(CyrusServiceUtil.class, "_service");
	}

	private static CyrusService _service;

}