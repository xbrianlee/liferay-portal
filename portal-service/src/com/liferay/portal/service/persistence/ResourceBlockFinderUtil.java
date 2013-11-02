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
public class ResourceBlockFinderUtil {
	public static com.liferay.portal.security.permission.ResourceBlockIdsBag findByC_G_N_R(
		long companyId, long groupId, java.lang.String name, long[] roleIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByC_G_N_R(companyId, groupId, name, roleIds);
	}

	public static ResourceBlockFinder getFinder() {
		if (_finder == null) {
			_finder = (ResourceBlockFinder)PortalBeanLocatorUtil.locate(ResourceBlockFinder.class.getName());

			ReferenceRegistry.registerReference(ResourceBlockFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ResourceBlockFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ResourceBlockFinderUtil.class,
			"_finder");
	}

	private static ResourceBlockFinder _finder;
}