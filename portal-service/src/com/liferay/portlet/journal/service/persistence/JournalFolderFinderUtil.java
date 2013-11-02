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

package com.liferay.portlet.journal.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class JournalFolderFinderUtil {
	public static int countF_A_ByG_F(long groupId, long folderId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countF_A_ByG_F(groupId, folderId, queryDefinition);
	}

	public static int filterCountF_A_ByG_F(long groupId, long folderId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountF_A_ByG_F(groupId, folderId, queryDefinition);
	}

	public static java.util.List<java.lang.Object> filterFindF_A_ByG_F(
		long groupId, long folderId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindF_A_ByG_F(groupId, folderId, queryDefinition);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> findF_ByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findF_ByNoAssets();
	}

	public static java.util.List<java.lang.Object> findF_A_ByG_F(long groupId,
		long folderId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findF_A_ByG_F(groupId, folderId, queryDefinition);
	}

	public static JournalFolderFinder getFinder() {
		if (_finder == null) {
			_finder = (JournalFolderFinder)PortalBeanLocatorUtil.locate(JournalFolderFinder.class.getName());

			ReferenceRegistry.registerReference(JournalFolderFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(JournalFolderFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(JournalFolderFinderUtil.class,
			"_finder");
	}

	private static JournalFolderFinder _finder;
}