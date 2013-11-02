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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BrowserTracker;
import com.liferay.portal.service.base.BrowserTrackerLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class BrowserTrackerLocalServiceImpl
	extends BrowserTrackerLocalServiceBaseImpl {

	@Override
	public void deleteUserBrowserTracker(long userId) throws SystemException {
		BrowserTracker browserTracker = browserTrackerPersistence.fetchByUserId(
			userId);

		if (browserTracker != null) {
			browserTrackerPersistence.remove(browserTracker);
		}
	}

	@Override
	public BrowserTracker getBrowserTracker(long userId, long browserKey)
		throws SystemException {

		BrowserTracker browserTracker = browserTrackerPersistence.fetchByUserId(
			userId);

		if (browserTracker == null) {
			browserTracker = browserTrackerLocalService.updateBrowserTracker(
				userId, browserKey);
		}

		return browserTracker;
	}

	@Override
	public BrowserTracker updateBrowserTracker(long userId, long browserKey)
		throws SystemException {

		BrowserTracker browserTracker = browserTrackerPersistence.fetchByUserId(
			userId);

		if (browserTracker == null) {
			long browserTrackerId = counterLocalService.increment();

			browserTracker = browserTrackerPersistence.create(browserTrackerId);

			browserTracker.setUserId(userId);
		}

		browserTracker.setBrowserKey(browserKey);

		try {
			browserTrackerPersistence.update(browserTracker);
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn("Add failed, fetch {userId=" + userId + "}");
			}

			browserTracker = browserTrackerPersistence.fetchByUserId(
				userId, false);

			if (browserTracker == null) {
				throw se;
			}
		}

		return browserTracker;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BrowserTrackerLocalServiceImpl.class);

}