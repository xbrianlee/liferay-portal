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
public class TeamFinderUtil {
	public static int countByG_N_D(long groupId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_N_D(groupId, name, description, params);
	}

	public static java.util.List<com.liferay.portal.model.Team> findByG_N_D(
		long groupId, java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByG_N_D(groupId, name, description, params, start, end,
			obc);
	}

	public static TeamFinder getFinder() {
		if (_finder == null) {
			_finder = (TeamFinder)PortalBeanLocatorUtil.locate(TeamFinder.class.getName());

			ReferenceRegistry.registerReference(TeamFinderUtil.class, "_finder");
		}

		return _finder;
	}

	public void setFinder(TeamFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TeamFinderUtil.class, "_finder");
	}

	private static TeamFinder _finder;
}