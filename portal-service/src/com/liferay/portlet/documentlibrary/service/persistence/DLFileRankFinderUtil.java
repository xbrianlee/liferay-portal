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

package com.liferay.portlet.documentlibrary.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class DLFileRankFinderUtil {
	public static java.util.List<java.lang.Object[]> findByStaleRanks(int count)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByStaleRanks(count);
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileRank> findByFolderId(
		long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByFolderId(folderId);
	}

	public static DLFileRankFinder getFinder() {
		if (_finder == null) {
			_finder = (DLFileRankFinder)PortalBeanLocatorUtil.locate(DLFileRankFinder.class.getName());

			ReferenceRegistry.registerReference(DLFileRankFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(DLFileRankFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(DLFileRankFinderUtil.class,
			"_finder");
	}

	private static DLFileRankFinder _finder;
}