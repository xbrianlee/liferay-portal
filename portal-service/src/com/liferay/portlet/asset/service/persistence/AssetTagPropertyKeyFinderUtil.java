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

package com.liferay.portlet.asset.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class AssetTagPropertyKeyFinderUtil {
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByGroupId(groupId);
	}

	public static java.lang.String[] findByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByGroupId(groupId);
	}

	public static java.lang.String[] findByGroupId(long groupId, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByGroupId(groupId, start, end);
	}

	public static AssetTagPropertyKeyFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetTagPropertyKeyFinder)PortalBeanLocatorUtil.locate(AssetTagPropertyKeyFinder.class.getName());

			ReferenceRegistry.registerReference(AssetTagPropertyKeyFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetTagPropertyKeyFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetTagPropertyKeyFinderUtil.class,
			"_finder");
	}

	private static AssetTagPropertyKeyFinder _finder;
}