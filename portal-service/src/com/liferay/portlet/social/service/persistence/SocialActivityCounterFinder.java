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

package com.liferay.portlet.social.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface SocialActivityCounterFinder {
	public int countU_ByG_N(long groupId, java.lang.String[] names)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.social.model.SocialActivityCounter> findAC_ByG_N_S_E_1(
		long groupId, java.lang.String name, int startPeriod, int endPeriod,
		int periodLength)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.social.model.SocialActivityCounter> findAC_ByG_N_S_E_2(
		long groupId, java.lang.String counterName, int startPeriod,
		int endPeriod, int periodLength)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.social.model.SocialActivityCounter> findAC_By_G_C_C_N_S_E(
		long groupId, java.util.List<java.lang.Long> userIds,
		java.lang.String[] names, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Long> findU_ByG_N(long groupId,
		java.lang.String[] names, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}