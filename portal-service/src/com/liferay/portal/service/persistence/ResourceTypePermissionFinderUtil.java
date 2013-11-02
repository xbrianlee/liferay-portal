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
public class ResourceTypePermissionFinderUtil {
	public static java.util.List<com.liferay.portal.model.ResourceTypePermission> findByEitherScopeC_G_N(
		long companyId, long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByEitherScopeC_G_N(companyId, groupId, name);
	}

	public static java.util.List<com.liferay.portal.model.ResourceTypePermission> findByGroupScopeC_N_R(
		long companyId, java.lang.String name, long roleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByGroupScopeC_N_R(companyId, name, roleId);
	}

	public static ResourceTypePermissionFinder getFinder() {
		if (_finder == null) {
			_finder = (ResourceTypePermissionFinder)PortalBeanLocatorUtil.locate(ResourceTypePermissionFinder.class.getName());

			ReferenceRegistry.registerReference(ResourceTypePermissionFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ResourceTypePermissionFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ResourceTypePermissionFinderUtil.class,
			"_finder");
	}

	private static ResourceTypePermissionFinder _finder;
}