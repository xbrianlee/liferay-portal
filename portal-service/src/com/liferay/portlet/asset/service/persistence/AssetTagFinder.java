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

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface AssetTagFinder {
	public int countByG_C_N(long groupId, long classNameId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByG_N_P(long groupId, java.lang.String name,
		java.lang.String[] tagProperties)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByG_N(long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByG_C_N(long groupId, long classNameId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByG_N_P(long groupId, java.lang.String name,
		java.lang.String[] tagProperties)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.asset.model.AssetTag filterFindByG_N(
		long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.asset.NoSuchTagException;

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> filterFindByG_C_N(
		long groupId, long classNameId, java.lang.String name, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> filterFindByG_N_P(
		long[] groupIds, java.lang.String name,
		java.lang.String[] tagProperties, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.asset.model.AssetTag findByG_N(long groupId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.asset.NoSuchTagException;

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> findByG_C_N(
		long groupId, long classNameId, java.lang.String name, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> findByG_N_P(
		long[] groupIds, java.lang.String name,
		java.lang.String[] tagProperties, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetTag> findByG_N_S_E(
		long groupId, java.lang.String name, int startPeriod, int endPeriod,
		int periodLength)
		throws com.liferay.portal.kernel.exception.SystemException;
}