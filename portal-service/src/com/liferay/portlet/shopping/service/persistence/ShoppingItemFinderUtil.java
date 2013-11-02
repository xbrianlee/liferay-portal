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

package com.liferay.portlet.shopping.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class ShoppingItemFinderUtil {
	public static int countByG_C(long groupId,
		java.util.List<java.lang.Long> categoryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_C(groupId, categoryIds);
	}

	public static int countByFeatured(long groupId, long[] categoryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByFeatured(groupId, categoryIds);
	}

	public static int countByKeywords(long groupId, long[] categoryIds,
		java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByKeywords(groupId, categoryIds, keywords);
	}

	public static int countBySale(long groupId, long[] categoryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countBySale(groupId, categoryIds);
	}

	public static int filterCountByG_C(long groupId,
		java.util.List<java.lang.Long> categoryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().filterCountByG_C(groupId, categoryIds);
	}

	public static java.util.List<com.liferay.portlet.shopping.model.ShoppingItem> findByFeatured(
		long groupId, long[] categoryIds, int numOfItems)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByFeatured(groupId, categoryIds, numOfItems);
	}

	public static java.util.List<com.liferay.portlet.shopping.model.ShoppingItem> findByKeywords(
		long groupId, long[] categoryIds, java.lang.String keywords, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(groupId, categoryIds, keywords, start, end);
	}

	public static java.util.List<com.liferay.portlet.shopping.model.ShoppingItem> findBySale(
		long groupId, long[] categoryIds, int numOfItems)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findBySale(groupId, categoryIds, numOfItems);
	}

	public static ShoppingItemFinder getFinder() {
		if (_finder == null) {
			_finder = (ShoppingItemFinder)PortalBeanLocatorUtil.locate(ShoppingItemFinder.class.getName());

			ReferenceRegistry.registerReference(ShoppingItemFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ShoppingItemFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ShoppingItemFinderUtil.class,
			"_finder");
	}

	private static ShoppingItemFinder _finder;
}