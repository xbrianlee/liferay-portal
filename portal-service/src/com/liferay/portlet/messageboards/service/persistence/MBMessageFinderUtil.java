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

package com.liferay.portlet.messageboards.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class MBMessageFinderUtil {
	public static int countByC_T(java.util.Date createDate, long threadId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByC_T(createDate, threadId);
	}

	public static int countByG_U_C_S(long groupId, long userId,
		long[] categoryIds, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_U_C_S(groupId, userId, categoryIds, status);
	}

	public static int countByG_U_C_A_S(long groupId, long userId,
		long[] categoryIds, boolean anonymous, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByG_U_C_A_S(groupId, userId, categoryIds, anonymous,
			status);
	}

	public static int filterCountByG_U_C_S(long groupId, long userId,
		long[] categoryIds, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByG_U_C_S(groupId, userId, categoryIds, status);
	}

	public static int filterCountByG_U_MD_C_S(long groupId, long userId,
		java.util.Date modifiedDate, long[] categoryIds, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByG_U_MD_C_S(groupId, userId, modifiedDate,
			categoryIds, status);
	}

	public static int filterCountByG_U_C_A_S(long groupId, long userId,
		long[] categoryIds, boolean anonymous, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByG_U_C_A_S(groupId, userId, categoryIds,
			anonymous, status);
	}

	public static java.util.List<java.lang.Long> filterFindByG_U_C_S(
		long groupId, long userId, long[] categoryIds, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByG_U_C_S(groupId, userId, categoryIds, status,
			start, end);
	}

	public static java.util.List<java.lang.Long> filterFindByG_U_MD_C_S(
		long groupId, long userId, java.util.Date modifiedDate,
		long[] categoryIds, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByG_U_MD_C_S(groupId, userId, modifiedDate,
			categoryIds, status, start, end);
	}

	public static java.util.List<java.lang.Long> filterFindByG_U_C_A_S(
		long groupId, long userId, long[] categoryIds, boolean anonymous,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByG_U_C_A_S(groupId, userId, categoryIds,
			anonymous, status, start, end);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBMessage> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoAssets();
	}

	public static java.util.List<java.lang.Long> findByG_U_C_S(long groupId,
		long userId, long[] categoryIds, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByG_U_C_S(groupId, userId, categoryIds, status, start,
			end);
	}

	public static java.util.List<java.lang.Long> findByG_U_C_A_S(long groupId,
		long userId, long[] categoryIds, boolean anonymous, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByG_U_C_A_S(groupId, userId, categoryIds, anonymous,
			status, start, end);
	}

	public static MBMessageFinder getFinder() {
		if (_finder == null) {
			_finder = (MBMessageFinder)PortalBeanLocatorUtil.locate(MBMessageFinder.class.getName());

			ReferenceRegistry.registerReference(MBMessageFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(MBMessageFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(MBMessageFinderUtil.class, "_finder");
	}

	private static MBMessageFinder _finder;
}