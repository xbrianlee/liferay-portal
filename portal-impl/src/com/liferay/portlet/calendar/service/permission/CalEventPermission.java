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

package com.liferay.portlet.calendar.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class CalEventPermission {

	public static void check(
			PermissionChecker permissionChecker, CalEvent event,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, event, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long eventId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, eventId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, CalEvent event, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				event.getCompanyId(), CalEvent.class.getName(),
				event.getEventId(), event.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			event.getGroupId(), CalEvent.class.getName(), event.getEventId(),
			actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long eventId, String actionId)
		throws PortalException, SystemException {

		CalEvent event = CalEventLocalServiceUtil.getEvent(eventId);

		return contains(permissionChecker, event, actionId);
	}

}