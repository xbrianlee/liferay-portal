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

package com.liferay.mail.service.persistence;

import com.liferay.mail.NoSuchCyrusUserException;
import com.liferay.mail.model.CyrusUser;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 */
public class CyrusUserUtil {

	public static CyrusUser findByPrimaryKey(long userId)
		throws NoSuchCyrusUserException, SystemException {

		return getPersistence().findByPrimaryKey(userId);
	}

	public static CyrusUserPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CyrusUserPersistence)PortalBeanLocatorUtil.locate(
				CyrusUserPersistence.class.getName());
		}

		return _persistence;
	}

	public static void remove(long userId)
		throws NoSuchCyrusUserException, SystemException {

		getPersistence().remove(userId);
	}

	public static void update(CyrusUser user) throws SystemException {
		getPersistence().update(user);
	}

	public void setPersistence(CyrusUserPersistence persistence) {
		_persistence = persistence;
	}

	private static CyrusUserPersistence _persistence;

}