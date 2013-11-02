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
public class JournalFeedFinderUtil {
	public static int countByKeywords(long companyId, long groupId,
		java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByKeywords(companyId, groupId, keywords);
	}

	public static int countByC_G_F_N_D(long companyId, long groupId,
		java.lang.String feedId, java.lang.String name,
		java.lang.String description, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_F_N_D(companyId, groupId, feedId, name,
			description, andOperator);
	}

	public static int countByC_G_F_N_D(long companyId, long groupId,
		java.lang.String[] feedIds, java.lang.String[] names,
		java.lang.String[] descriptions, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_F_N_D(companyId, groupId, feedIds, names,
			descriptions, andOperator);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFeed> findByKeywords(
		long companyId, long groupId, java.lang.String keywords, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, groupId, keywords, start, end, obc);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFeed> findByC_G_F_N_D(
		long companyId, long groupId, java.lang.String feedId,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_F_N_D(companyId, groupId, feedId, name,
			description, andOperator, start, end, obc);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFeed> findByC_G_F_N_D(
		long companyId, long groupId, java.lang.String[] feedIds,
		java.lang.String[] names, java.lang.String[] descriptions,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_F_N_D(companyId, groupId, feedIds, names,
			descriptions, andOperator, start, end, obc);
	}

	public static JournalFeedFinder getFinder() {
		if (_finder == null) {
			_finder = (JournalFeedFinder)PortalBeanLocatorUtil.locate(JournalFeedFinder.class.getName());

			ReferenceRegistry.registerReference(JournalFeedFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(JournalFeedFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(JournalFeedFinderUtil.class,
			"_finder");
	}

	private static JournalFeedFinder _finder;
}