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
public class AssetTagPropertyFinderUtil {
	public static int countByG_K(long groupId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_K(groupId, key);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTagProperty> findByG_K(
		long groupId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByG_K(groupId, key);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetTagProperty> findByG_K(
		long groupId, java.lang.String key, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByG_K(groupId, key, start, end);
	}

	public static AssetTagPropertyFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetTagPropertyFinder)PortalBeanLocatorUtil.locate(AssetTagPropertyFinder.class.getName());

			ReferenceRegistry.registerReference(AssetTagPropertyFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetTagPropertyFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetTagPropertyFinderUtil.class,
			"_finder");
	}

	private static AssetTagPropertyFinder _finder;
}