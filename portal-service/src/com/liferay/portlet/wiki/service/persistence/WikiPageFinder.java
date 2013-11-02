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

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface WikiPageFinder {
	public int countByCreateDate(long groupId, long nodeId,
		java.util.Date createDate, boolean before)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCreateDate(long groupId, long nodeId,
		java.sql.Timestamp createDate, boolean before)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByCreateDate(long groupId, long nodeId,
		java.util.Date createDate, boolean before)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByCreateDate(long groupId, long nodeId,
		java.sql.Timestamp createDate, boolean before)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> filterFindByCreateDate(
		long groupId, long nodeId, java.util.Date createDate, boolean before,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> filterFindByCreateDate(
		long groupId, long nodeId, java.sql.Timestamp createDate,
		boolean before, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.wiki.model.WikiPage findByResourcePrimKey(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.wiki.NoSuchPageException;

	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> findByCreateDate(
		long groupId, long nodeId, java.util.Date createDate, boolean before,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> findByCreateDate(
		long groupId, long nodeId, java.sql.Timestamp createDate,
		boolean before, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.wiki.model.WikiPage> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException;
}