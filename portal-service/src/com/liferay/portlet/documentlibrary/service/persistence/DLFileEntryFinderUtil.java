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
public class DLFileEntryFinderUtil {
	public static int countByExtraSettings()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByExtraSettings();
	}

	public static int countByG_F(long groupId,
		java.util.List<java.lang.Long> folderIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_F(groupId, folderIds, queryDefinition);
	}

	public static int countByG_M_R(long groupId,
		com.liferay.portal.kernel.util.DateRange dateRange, long repositoryId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByG_M_R(groupId, dateRange, repositoryId,
			queryDefinition);
	}

	public static int countByG_U_F_M(long groupId, long userId,
		java.util.List<java.lang.Long> folderIds, java.lang.String[] mimeTypes,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByG_U_F_M(groupId, userId, folderIds, mimeTypes,
			queryDefinition);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry fetchByAnyImageId(
		long imageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().fetchByAnyImageId(imageId);
	}

	public static int filterCountByG_F(long groupId,
		java.util.List<java.lang.Long> folderIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().filterCountByG_F(groupId, folderIds, queryDefinition);
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> filterFindByG_F(
		long groupId, java.util.List<java.lang.Long> folderIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().filterFindByG_F(groupId, folderIds, queryDefinition);
	}

	public static com.liferay.portlet.documentlibrary.model.DLFileEntry findByAnyImageId(
		long imageId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.documentlibrary.NoSuchFileEntryException {
		return getFinder().findByAnyImageId(imageId);
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByCompanyId(
		long companyId,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByCompanyId(companyId, queryDefinition);
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByDDMStructureIds(
		long[] ddmStructureIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByDDMStructureIds(ddmStructureIds, start, end);
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByExtraSettings(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByExtraSettings(start, end);
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByMisversioned()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByMisversioned();
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoAssets();
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByOrphanedFileEntries()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByOrphanedFileEntries();
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_F(
		long groupId, java.util.List<java.lang.Long> folderIds,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByG_F(groupId, folderIds, queryDefinition);
	}

	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntry> findByG_U_F_M(
		long groupId, long userId, java.util.List<java.lang.Long> folderIds,
		java.lang.String[] mimeTypes,
		com.liferay.portal.kernel.dao.orm.QueryDefinition queryDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByG_U_F_M(groupId, userId, folderIds, mimeTypes,
			queryDefinition);
	}

	public static DLFileEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (DLFileEntryFinder)PortalBeanLocatorUtil.locate(DLFileEntryFinder.class.getName());

			ReferenceRegistry.registerReference(DLFileEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(DLFileEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(DLFileEntryFinderUtil.class,
			"_finder");
	}

	private static DLFileEntryFinder _finder;
}