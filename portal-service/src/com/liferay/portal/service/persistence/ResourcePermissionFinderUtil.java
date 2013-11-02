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
public class ResourcePermissionFinderUtil {
	public static int countByR_S(long roleId, int[] scopes)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByR_S(roleId, scopes);
	}

	public static int countByC_N_S_P_R_A(long companyId, java.lang.String name,
		int scope, java.lang.String primKey, long[] roleIds, long actionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_N_S_P_R_A(companyId, name, scope, primKey,
			roleIds, actionId);
	}

	public static java.util.List<com.liferay.portal.model.ResourcePermission> findByResource(
		long companyId, long groupId, java.lang.String name,
		java.lang.String primKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByResource(companyId, groupId, name, primKey);
	}

	public static java.util.List<com.liferay.portal.model.ResourcePermission> findByR_S(
		long roleId, int[] scopes, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByR_S(roleId, scopes, start, end);
	}

	public static ResourcePermissionFinder getFinder() {
		if (_finder == null) {
			_finder = (ResourcePermissionFinder)PortalBeanLocatorUtil.locate(ResourcePermissionFinder.class.getName());

			ReferenceRegistry.registerReference(ResourcePermissionFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ResourcePermissionFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ResourcePermissionFinderUtil.class,
			"_finder");
	}

	private static ResourcePermissionFinder _finder;
}