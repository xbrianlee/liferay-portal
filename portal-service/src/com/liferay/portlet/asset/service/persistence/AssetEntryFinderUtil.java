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
public class AssetEntryFinderUtil {
	public static int countEntries(
		com.liferay.portlet.asset.service.persistence.AssetEntryQuery entryQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countEntries(entryQuery);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetEntry> findEntries(
		com.liferay.portlet.asset.service.persistence.AssetEntryQuery entryQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findEntries(entryQuery);
	}

	public static AssetEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetEntryFinder)PortalBeanLocatorUtil.locate(AssetEntryFinder.class.getName());

			ReferenceRegistry.registerReference(AssetEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetEntryFinderUtil.class,
			"_finder");
	}

	private static AssetEntryFinder _finder;
}