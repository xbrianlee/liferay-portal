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
import com.liferay.portlet.announcements.model.AnnouncementsFlag;
import com.liferay.portlet.announcements.service.base.AnnouncementsFlagServiceBaseImpl;

/**
 * @author Thiago Moreira
 * @author Raymond Augé
 */
public class AnnouncementsFlagServiceImpl
	extends AnnouncementsFlagServiceBaseImpl {

	@Override
	public void addFlag(long entryId, int value)
		throws PortalException, SystemException {

		announcementsFlagLocalService.addFlag(getUserId(), entryId, value);
	}

	@Override
	public void deleteFlag(long flagId)
		throws PortalException, SystemException {

		AnnouncementsFlag flag = announcementsFlagPersistence.findByPrimaryKey(
			flagId);

		if (flag.getUserId() != getUserId()) {
			throw new PrincipalException();
		}

		announcementsFlagLocalService.deleteFlag(flagId);
	}

	@Override
	public AnnouncementsFlag getFlag(long entryId, int value)
		throws PortalException, SystemException {

		return announcementsFlagLocalService.getFlag(
			getUserId(), entryId, value);
	}

}