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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class SocialActivityCounterFinderUtil {
	public static int countU_ByG_N(long groupId, java.lang.String[] names)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countU_ByG_N(groupId, names);
	}

	public static java.util.List<com.liferay.portlet.social.model.SocialActivityCounter> findAC_ByG_N_S_E_1(
		long groupId, java.lang.String name, int startPeriod, int endPeriod,
		int periodLength)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findAC_ByG_N_S_E_1(groupId, name, startPeriod, endPeriod,
			periodLength);
	}

	public static java.util.List<com.liferay.portlet.social.model.SocialActivityCounter> findAC_ByG_N_S_E_2(
		long groupId, java.lang.String counterName, int startPeriod,
		int endPeriod, int periodLength)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findAC_ByG_N_S_E_2(groupId, counterName, startPeriod,
			endPeriod, periodLength);
	}

	public static java.util.List<com.liferay.portlet.social.model.SocialActivityCounter> findAC_By_G_C_C_N_S_E(
		long groupId, java.util.List<java.lang.Long> userIds,
		java.lang.String[] names, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findAC_By_G_C_C_N_S_E(groupId, userIds, names, start, end);
	}

	public static java.util.List<java.lang.Long> findU_ByG_N(long groupId,
		java.lang.String[] names, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findU_ByG_N(groupId, names, start, end);
	}

	public static SocialActivityCounterFinder getFinder() {
		if (_finder == null) {
			_finder = (SocialActivityCounterFinder)PortalBeanLocatorUtil.locate(SocialActivityCounterFinder.class.getName());

			ReferenceRegistry.registerReference(SocialActivityCounterFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SocialActivityCounterFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SocialActivityCounterFinderUtil.class,
			"_finder");
	}

	private static SocialActivityCounterFinder _finder;
}