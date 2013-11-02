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
public class PasswordPolicyFinderUtil {
	public static int countByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByC_N(companyId, name);
	}

	public static java.util.List<com.liferay.portal.model.PasswordPolicy> findByC_N(
		long companyId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByC_N(companyId, name, start, end, obc);
	}

	public static PasswordPolicyFinder getFinder() {
		if (_finder == null) {
			_finder = (PasswordPolicyFinder)PortalBeanLocatorUtil.locate(PasswordPolicyFinder.class.getName());

			ReferenceRegistry.registerReference(PasswordPolicyFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(PasswordPolicyFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(PasswordPolicyFinderUtil.class,
			"_finder");
	}

	private static PasswordPolicyFinder _finder;
}