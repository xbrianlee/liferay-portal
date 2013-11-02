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
public class UserGroupRoleFinderUtil {
	public static java.util.List<com.liferay.portal.model.UserGroupRole> findByUserUserGroupGroupRole(
		long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByUserUserGroupGroupRole(userId, groupId);
	}

	public static UserGroupRoleFinder getFinder() {
		if (_finder == null) {
			_finder = (UserGroupRoleFinder)PortalBeanLocatorUtil.locate(UserGroupRoleFinder.class.getName());

			ReferenceRegistry.registerReference(UserGroupRoleFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(UserGroupRoleFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(UserGroupRoleFinderUtil.class,
			"_finder");
	}

	private static UserGroupRoleFinder _finder;
}