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

package com.liferay.portlet.messageboards.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class MBCategoryFinderUtil {
	public static int countByS_G_U_P(long groupId, long userId,
		long[] parentCategoryIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByS_G_U_P(groupId, userId, parentCategoryIds,
			queryDefinition);
	}

	public static int filterCountByS_G_U_P(long groupId, long userId,
		long[] parentCategoryIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByS_G_U_P(groupId, userId, parentCategoryIds,
			queryDefinition);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBCategory> filterFindByS_G_U_P(
		long groupId, long userId, long[] parentCategoryIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByS_G_U_P(groupId, userId, parentCategoryIds,
			queryDefinition);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBCategory> findByS_G_U_P(
		long groupId, long userId, long[] parentCategoryIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByS_G_U_P(groupId, userId, parentCategoryIds,
			queryDefinition);
	}

	public static MBCategoryFinder getFinder() {
		if (_finder == null) {
			_finder = (MBCategoryFinder)PortalBeanLocatorUtil.locate(MBCategoryFinder.class.getName());

			ReferenceRegistry.registerReference(MBCategoryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(MBCategoryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(MBCategoryFinderUtil.class,
			"_finder");
	}

	private static MBCategoryFinder _finder;
}