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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.UserTracker;
import com.liferay.portal.model.UserTrackerPath;
import com.liferay.portal.service.base.UserTrackerLocalServiceBaseImpl;
import com.liferay.portal.util.PropsValues;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class UserTrackerLocalServiceImpl
	extends UserTrackerLocalServiceBaseImpl {

	@Override
	public UserTracker addUserTracker(
			long companyId, long userId, Date modifiedDate, String sessionId,
			String remoteAddr, String remoteHost, String userAgent,
			List<UserTrackerPath> userTrackerPaths)
		throws SystemException {

		if (PropsValues.SESSION_TRACKER_PERSISTENCE_ENABLED) {
			long userTrackerId = counterLocalService.increment(
				UserTracker.class.getName());

			UserTracker userTracker = userTrackerPersistence.create(
				userTrackerId);

			userTracker.setCompanyId(companyId);
			userTracker.setUserId(userId);
			userTracker.setModifiedDate(modifiedDate);
			userTracker.setSessionId(sessionId);
			userTracker.setRemoteAddr(remoteAddr);
			userTracker.setRemoteHost(remoteHost);
			userTracker.setUserAgent(userAgent);

			userTrackerPersistence.update(userTracker);

			for (UserTrackerPath userTrackerPath : userTrackerPaths) {
				long pathId = counterLocalService.increment(
					UserTrackerPath.class.getName());

				userTrackerPath.setUserTrackerPathId(pathId);
				userTrackerPath.setUserTrackerId(userTrackerId);

				userTrackerPathPersistence.update(userTrackerPath);
			}

			return userTracker;
		}
		else {
			return null;
		}
	}

	@Override
	public UserTracker deleteUserTracker(long userTrackerId)
		throws PortalException, SystemException {

		UserTracker userTracker = userTrackerPersistence.findByPrimaryKey(
			userTrackerId);

		return deleteUserTracker(userTracker);
	}

	@Override
	public UserTracker deleteUserTracker(UserTracker userTracker)
		throws SystemException {

		// Paths

		userTrackerPathPersistence.removeByUserTrackerId(
			userTracker.getUserTrackerId());

		// User tracker

		return userTrackerPersistence.remove(userTracker);
	}

	@Override
	public List<UserTracker> getUserTrackers(long companyId, int start, int end)
		throws SystemException {

		return userTrackerPersistence.findByCompanyId(companyId, start, end);
	}

}