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
import com.liferay.portlet.announcements.model.AnnouncementsFlag;
import com.liferay.portlet.announcements.service.base.AnnouncementsFlagLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Thiago Moreira
 * @author Raymond Augé
 */
public class AnnouncementsFlagLocalServiceImpl
	extends AnnouncementsFlagLocalServiceBaseImpl {

	@Override
	public AnnouncementsFlag addFlag(long userId, long entryId, int value)
		throws SystemException {

		long flagId = counterLocalService.increment();

		AnnouncementsFlag flag = announcementsFlagPersistence.create(flagId);

		flag.setUserId(userId);
		flag.setCreateDate(new Date());
		flag.setEntryId(entryId);
		flag.setValue(value);

		announcementsFlagPersistence.update(flag);

		return flag;
	}

	@Override
	public void deleteFlag(AnnouncementsFlag flag) throws SystemException {
		announcementsFlagPersistence.remove(flag);
	}

	@Override
	public void deleteFlag(long flagId)
		throws PortalException, SystemException {

		AnnouncementsFlag flag = announcementsFlagPersistence.findByPrimaryKey(
			flagId);

		deleteFlag(flag);
	}

	@Override
	public void deleteFlags(long entryId) throws SystemException {
		List<AnnouncementsFlag> flags =
			announcementsFlagPersistence.findByEntryId(entryId);

		for (AnnouncementsFlag flag : flags) {
			deleteFlag(flag);
		}
	}

	@Override
	public AnnouncementsFlag getFlag(long userId, long entryId, int value)
		throws PortalException, SystemException {

		return announcementsFlagPersistence.findByU_E_V(userId, entryId, value);
	}

}