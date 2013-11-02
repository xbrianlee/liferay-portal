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

package com.liferay.portlet.calendar.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class CalEventFinderUtil {
	public static int countByG_SD_T(long groupId, java.util.Date startDateGT,
		java.util.Date startDateLT, boolean timeZoneSensitive,
		java.lang.String[] types)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByG_SD_T(groupId, startDateGT, startDateLT,
			timeZoneSensitive, types);
	}

	public static java.util.List<com.liferay.portlet.calendar.model.CalEvent> findByFutureReminders()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByFutureReminders();
	}

	public static java.util.List<com.liferay.portlet.calendar.model.CalEvent> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoAssets();
	}

	public static java.util.List<com.liferay.portlet.calendar.model.CalEvent> findByG_SD_T(
		long groupId, java.util.Date startDateGT, java.util.Date startDateLT,
		boolean timeZoneSensitive, java.lang.String[] types)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByG_SD_T(groupId, startDateGT, startDateLT,
			timeZoneSensitive, types);
	}

	public static java.util.List<com.liferay.portlet.calendar.model.CalEvent> findByG_SD_T(
		long groupId, java.util.Date startDateGT, java.util.Date startDateLT,
		boolean timeZoneSensitive, java.lang.String[] types, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByG_SD_T(groupId, startDateGT, startDateLT,
			timeZoneSensitive, types, start, end);
	}

	public static CalEventFinder getFinder() {
		if (_finder == null) {
			_finder = (CalEventFinder)PortalBeanLocatorUtil.locate(CalEventFinder.class.getName());

			ReferenceRegistry.registerReference(CalEventFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(CalEventFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(CalEventFinderUtil.class, "_finder");
	}

	private static CalEventFinder _finder;
}