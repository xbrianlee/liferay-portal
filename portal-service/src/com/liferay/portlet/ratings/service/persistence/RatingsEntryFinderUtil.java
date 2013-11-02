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

package com.liferay.portlet.ratings.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class RatingsEntryFinderUtil {
	public static java.util.List<com.liferay.portlet.ratings.model.RatingsEntry> findByU_C_C(
		long userId, long classNameId, java.util.List<java.lang.Long> classPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByU_C_C(userId, classNameId, classPKs);
	}

	public static RatingsEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (RatingsEntryFinder)PortalBeanLocatorUtil.locate(RatingsEntryFinder.class.getName());

			ReferenceRegistry.registerReference(RatingsEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(RatingsEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(RatingsEntryFinderUtil.class,
			"_finder");
	}

	private static RatingsEntryFinder _finder;
}