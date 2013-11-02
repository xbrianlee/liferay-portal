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
public class GroupFinderUtil {
	public static int countByLayouts(long companyId, long parentGroupId,
		boolean site)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByLayouts(companyId, parentGroupId, site);
	}

	public static int countByG_U(long groupId, long userId, boolean inherit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_U(groupId, userId, inherit);
	}

	public static int countByC_C_PG_N_D(long companyId, long[] classNameIds,
		long parentGroupId, java.lang.String[] names,
		java.lang.String[] descriptions,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_C_PG_N_D(companyId, classNameIds, parentGroupId,
			names, descriptions, params, andOperator);
	}

	public static java.util.List<com.liferay.portal.model.Group> findByLayouts(
		long companyId, long parentGroupId, boolean site, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByLayouts(companyId, parentGroupId, site, start, end);
	}

	public static java.util.List<com.liferay.portal.model.Group> findByLiveGroups()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByLiveGroups();
	}

	public static java.util.List<com.liferay.portal.model.Group> findByNoLayouts(
		long classNameId, boolean privateLayout, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByNoLayouts(classNameId, privateLayout, start, end);
	}

	public static java.util.List<com.liferay.portal.model.Group> findByNullFriendlyURL()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNullFriendlyURL();
	}

	public static java.util.List<com.liferay.portal.model.Group> findBySystem(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findBySystem(companyId);
	}

	public static java.util.List<com.liferay.portal.model.Group> findByCompanyId(
		long companyId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByCompanyId(companyId, params, start, end, obc);
	}

	public static java.util.List<java.lang.Long> findByC_P(long companyId,
		long parentGroupId, long previousGroupId, int size)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_P(companyId, parentGroupId, previousGroupId, size);
	}

	public static com.liferay.portal.model.Group findByC_N(long companyId,
		java.lang.String name)
		throws com.liferay.portal.NoSuchGroupException,
			com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByC_N(companyId, name);
	}

	public static java.util.List<com.liferay.portal.model.Group> findByC_C_PG_N_D(
		long companyId, long[] classNameIds, long parentGroupId,
		java.lang.String[] names, java.lang.String[] descriptions,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_C_PG_N_D(companyId, classNameIds, parentGroupId,
			names, descriptions, params, andOperator, start, end, obc);
	}

	public static GroupFinder getFinder() {
		if (_finder == null) {
			_finder = (GroupFinder)PortalBeanLocatorUtil.locate(GroupFinder.class.getName());

			ReferenceRegistry.registerReference(GroupFinderUtil.class, "_finder");
		}

		return _finder;
	}

	public void setFinder(GroupFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(GroupFinderUtil.class, "_finder");
	}

	private static GroupFinder _finder;
}