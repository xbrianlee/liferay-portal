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

package com.liferay.portlet.bookmarks.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class BookmarksEntryFinderUtil {
	public static java.util.List<com.liferay.portlet.bookmarks.model.BookmarksEntry> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoAssets();
	}

	public static BookmarksEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (BookmarksEntryFinder)PortalBeanLocatorUtil.locate(BookmarksEntryFinder.class.getName());

			ReferenceRegistry.registerReference(BookmarksEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(BookmarksEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(BookmarksEntryFinderUtil.class,
			"_finder");
	}

	private static BookmarksEntryFinder _finder;
}