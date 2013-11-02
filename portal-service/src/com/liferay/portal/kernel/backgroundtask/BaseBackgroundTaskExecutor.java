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

package com.liferay.portal.kernel.backgroundtask;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public abstract class BaseBackgroundTaskExecutor
	implements BackgroundTaskExecutor {

	@Override
	public BackgroundTaskStatusMessageTranslator
		getBackgroundTaskStatusMessageTranslator() {

		return _backgroundTaskStatusMessageTranslator;
	}

	@Override
	public String handleException(BackgroundTask backgroundTask, Exception e) {
		return "Unable to execute background task: " + e.getMessage();
	}

	@Override
	public boolean isSerial() {
		return _serial;
	}

	protected Locale getLocale(BackgroundTask backgroundTask) {
		Map<String, Serializable> taskContextMap =
			backgroundTask.getTaskContextMap();

		long userId = MapUtil.getLong(taskContextMap, "userId");

		if (userId > 0) {
			try {
				User user = UserLocalServiceUtil.fetchUser(userId);

				if (user != null) {
					return user.getLocale();
				}
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug("Unable to get the user's locale", e);
				}
			}
		}

		return LocaleUtil.getDefault();
	}

	protected void setBackgroundTaskStatusMessageTranslator(
		BackgroundTaskStatusMessageTranslator
			backgroundTaskStatusMessageTranslator) {

		_backgroundTaskStatusMessageTranslator =
			backgroundTaskStatusMessageTranslator;
	}

	protected void setSerial(boolean serial) {
		_serial = serial;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseBackgroundTaskExecutor.class);

	private BackgroundTaskStatusMessageTranslator
		_backgroundTaskStatusMessageTranslator;
	private boolean _serial;

}