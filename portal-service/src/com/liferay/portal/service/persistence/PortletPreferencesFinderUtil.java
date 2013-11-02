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

package com.liferay.portal.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class PortletPreferencesFinderUtil {
	public static long countByO_O_P(long ownerId, int ownerType,
		java.lang.String portletId, boolean excludeDefaultPreferences)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByO_O_P(ownerId, ownerType, portletId,
			excludeDefaultPreferences);
	}

	public static long countByO_O_P_P_P(long ownerId, int ownerType, long plid,
		java.lang.String portletId, boolean excludeDefaultPreferences)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByO_O_P_P_P(ownerId, ownerType, plid, portletId,
			excludeDefaultPreferences);
	}

	public static java.util.List<com.liferay.portal.model.PortletPreferences> findByPortletId(
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByPortletId(portletId);
	}

	public static java.util.List<com.liferay.portal.model.PortletPreferences> findByC_G_O_O_P_P(
		long companyId, long groupId, long ownerId, int ownerType,
		java.lang.String portletId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_O_O_P_P(companyId, groupId, ownerId, ownerType,
			portletId, privateLayout);
	}

	public static PortletPreferencesFinder getFinder() {
		if (_finder == null) {
			_finder = (PortletPreferencesFinder)PortalBeanLocatorUtil.locate(PortletPreferencesFinder.class.getName());

			ReferenceRegistry.registerReference(PortletPreferencesFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(PortletPreferencesFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(PortletPreferencesFinderUtil.class,
			"_finder");
	}

	private static PortletPreferencesFinder _finder;
}