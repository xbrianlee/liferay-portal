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

package com.liferay.portlet.announcements.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.permission.PortalPermissionUtil;
import com.liferay.portal.service.permission.UserPermissionUtil;
import com.liferay.portlet.announcements.model.AnnouncementsDelivery;
import com.liferay.portlet.announcements.service.base.AnnouncementsDeliveryServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class AnnouncementsDeliveryServiceImpl
	extends AnnouncementsDeliveryServiceBaseImpl {

	@Override
	public AnnouncementsDelivery updateDelivery(
			long userId, String type, boolean email, boolean sms,
			boolean website)
		throws PortalException, SystemException {

		if (!PortalPermissionUtil.contains(
				getPermissionChecker(), ActionKeys.ADD_USER) &&
			!UserPermissionUtil.contains(
				getPermissionChecker(), userId, ActionKeys.UPDATE)) {

			throw new PrincipalException();
		}

		return announcementsDeliveryLocalService.updateDelivery(
			userId, type, email, sms, website);
	}

}