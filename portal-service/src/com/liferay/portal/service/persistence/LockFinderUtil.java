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

package com.liferay.portal.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class LockFinderUtil {
	public static com.liferay.portal.model.Lock fetchByC_K(
		java.lang.String className, java.lang.String key,
		com.liferay.portal.kernel.dao.orm.LockMode lockMode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().fetchByC_K(className, key, lockMode);
	}

	public static LockFinder getFinder() {
		if (_finder == null) {
			_finder = (LockFinder)PortalBeanLocatorUtil.locate(LockFinder.class.getName());

			ReferenceRegistry.registerReference(LockFinderUtil.class, "_finder");
		}

		return _finder;
	}

	public void setFinder(LockFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(LockFinderUtil.class, "_finder");
	}

	private static LockFinder _finder;
}