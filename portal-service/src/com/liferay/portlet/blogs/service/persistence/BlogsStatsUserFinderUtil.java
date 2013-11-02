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

package com.liferay.portlet.blogs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class BlogsStatsUserFinderUtil {
	public static int countByOrganizationId(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByOrganizationId(organizationId);
	}

	public static int countByOrganizationIds(
		java.util.List<java.lang.Long> organizationIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByOrganizationIds(organizationIds);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> findByGroupIds(
		long companyId, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByGroupIds(companyId, groupId, start, end);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> findByOrganizationId(
		long organizationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByOrganizationId(organizationId, start, end, obc);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsStatsUser> findByOrganizationIds(
		java.util.List<java.lang.Long> organizationIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByOrganizationIds(organizationIds, start, end, obc);
	}

	public static BlogsStatsUserFinder getFinder() {
		if (_finder == null) {
			_finder = (BlogsStatsUserFinder)PortalBeanLocatorUtil.locate(BlogsStatsUserFinder.class.getName());

			ReferenceRegistry.registerReference(BlogsStatsUserFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(BlogsStatsUserFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(BlogsStatsUserFinderUtil.class,
			"_finder");
	}

	private static BlogsStatsUserFinder _finder;
}