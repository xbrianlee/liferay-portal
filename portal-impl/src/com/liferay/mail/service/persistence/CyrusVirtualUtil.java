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

import com.liferay.mail.NoSuchCyrusVirtualException;
import com.liferay.mail.model.CyrusVirtual;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class CyrusVirtualUtil {

	public static CyrusVirtual findByPrimaryKey(String emailAddress)
		throws NoSuchCyrusVirtualException, SystemException {

		return getPersistence().findByPrimaryKey(emailAddress);
	}

	public static List<CyrusVirtual> findByUserId(long userId)
		throws SystemException {

		return getPersistence().findByUserId(userId);
	}

	public static CyrusVirtualPersistence getPersistence() {
		if (_persistence == null) {
			_persistence =
				(CyrusVirtualPersistence)PortalBeanLocatorUtil.locate(
					CyrusVirtualPersistence.class.getName());
		}

		return _persistence;
	}

	public static void remove(String emailAddress)
		throws NoSuchCyrusVirtualException, SystemException {

		getPersistence().remove(emailAddress);
	}

	public static void removeByUserId(long userId) throws SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void update(CyrusVirtual user) throws SystemException {
		getPersistence().update(user);
	}

	public void setPersistence(CyrusVirtualPersistence persistence) {
		_persistence = persistence;
	}

	private static CyrusVirtualPersistence _persistence;

}