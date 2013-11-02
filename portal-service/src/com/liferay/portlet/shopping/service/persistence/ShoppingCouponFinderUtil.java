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
public class ShoppingCouponFinderUtil {
	public static int countByG_C_C_A_DT(long groupId, long companyId,
		java.lang.String code, boolean active, java.lang.String discountType,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByG_C_C_A_DT(groupId, companyId, code, active,
			discountType, andOperator);
	}

	public static java.util.List<com.liferay.portlet.shopping.model.ShoppingCoupon> findByG_C_C_A_DT(
		long groupId, long companyId, java.lang.String code, boolean active,
		java.lang.String discountType, boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByG_C_C_A_DT(groupId, companyId, code, active,
			discountType, andOperator, start, end);
	}

	public static ShoppingCouponFinder getFinder() {
		if (_finder == null) {
			_finder = (ShoppingCouponFinder)PortalBeanLocatorUtil.locate(ShoppingCouponFinder.class.getName());

			ReferenceRegistry.registerReference(ShoppingCouponFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ShoppingCouponFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ShoppingCouponFinderUtil.class,
			"_finder");
	}

	private static ShoppingCouponFinder _finder;
}