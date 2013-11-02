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
public class RatingsStatsFinderUtil {
	public static java.util.List<com.liferay.portlet.ratings.model.RatingsStats> findByC_C(
		long classNameId, java.util.List<java.lang.Long> classPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByC_C(classNameId, classPKs);
	}

	public static RatingsStatsFinder getFinder() {
		if (_finder == null) {
			_finder = (RatingsStatsFinder)PortalBeanLocatorUtil.locate(RatingsStatsFinder.class.getName());

			ReferenceRegistry.registerReference(RatingsStatsFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(RatingsStatsFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(RatingsStatsFinderUtil.class,
			"_finder");
	}

	private static RatingsStatsFinder _finder;
}