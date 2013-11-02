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

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface MBMessageFinder {
	public int countByC_T(java.util.Date createDate, long threadId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByG_U_C_S(long groupId, long userId, long[] categoryIds,
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	public int countByG_U_C_A_S(long groupId, long userId, long[] categoryIds,
		boolean anonymous, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByG_U_C_S(long groupId, long userId,
		long[] categoryIds, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByG_U_MD_C_S(long groupId, long userId,
		java.util.Date modifiedDate, long[] categoryIds, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByG_U_C_A_S(long groupId, long userId,
		long[] categoryIds, boolean anonymous, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Long> filterFindByG_U_C_S(long groupId,
		long userId, long[] categoryIds, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Long> filterFindByG_U_MD_C_S(long groupId,
		long userId, java.util.Date modifiedDate, long[] categoryIds,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Long> filterFindByG_U_C_A_S(long groupId,
		long userId, long[] categoryIds, boolean anonymous, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.messageboards.model.MBMessage> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Long> findByG_U_C_S(long groupId,
		long userId, long[] categoryIds, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Long> findByG_U_C_A_S(long groupId,
		long userId, long[] categoryIds, boolean anonymous, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}