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

package com.liferay.portlet.wiki.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class WikiPageFinderUtil {
	public static int countByCreateDate(long groupId, long nodeId,
		java.util.Date createDate, boolean before)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByCreateDate(groupId, nodeId, createDate, before);
	}

	public static int countByCreateDate(long groupId, long nodeId,
		java.sql.Timestamp createDate, boolean before)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByCreateDate(groupId, nodeId, createDate, before);
	}

	public static int filterCountByCreateDate(long groupId, long nodeId,
		java.util.Date createDate, boolean before)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByCreateDate(groupId, nodeId, createDate, before);
	}

	public static int filterCountByCreateDate(long groupId, long nodeId,
		java.sql.Timestamp createDate, boolean before)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByCreateDate(groupId, nodeId, createDate, before);
	}

	public static java.util.List<com.liferay.portlet.wiki.model.WikiPage> filterFindByCreateDate(
		long groupId, long nodeId, java.util.Date createDate, boolean before,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByCreateDate(groupId, nodeId, createDate, before,
			start, end);
	}

	public static java.util.List<com.liferay.portlet.wiki.model.WikiPage> filterFindByCreateDate(
		long groupId, long nodeId, java.sql.Timestamp createDate,
		boolean before, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByCreateDate(groupId, nodeId, createDate, before,
			start, end);
	}

	public static com.liferay.portlet.wiki.model.WikiPage findByResourcePrimKey(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.wiki.NoSuchPageException {
		return getFinder().findByResourcePrimKey(resourcePrimKey);
	}

	public static java.util.List<com.liferay.portlet.wiki.model.WikiPage> findByCreateDate(
		long groupId, long nodeId, java.util.Date createDate, boolean before,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByCreateDate(groupId, nodeId, createDate, before,
			start, end);
	}

	public static java.util.List<com.liferay.portlet.wiki.model.WikiPage> findByCreateDate(
		long groupId, long nodeId, java.sql.Timestamp createDate,
		boolean before, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByCreateDate(groupId, nodeId, createDate, before,
			start, end);
	}

	public static java.util.List<com.liferay.portlet.wiki.model.WikiPage> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoAssets();
	}

	public static WikiPageFinder getFinder() {
		if (_finder == null) {
			_finder = (WikiPageFinder)PortalBeanLocatorUtil.locate(WikiPageFinder.class.getName());

			ReferenceRegistry.registerReference(WikiPageFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(WikiPageFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(WikiPageFinderUtil.class, "_finder");
	}

	private static WikiPageFinder _finder;
}