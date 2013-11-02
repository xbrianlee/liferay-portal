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

import com.liferay.portal.model.BackgroundTask;

/**
 * @author Michael C. Han
 */
public class DelegatingBackgroundTaskExecutor
	implements BackgroundTaskExecutor {

	public DelegatingBackgroundTaskExecutor(
		BackgroundTaskExecutor backgroundTaskExecutor) {

		_backgroundTaskExecutor = backgroundTaskExecutor;
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		return _backgroundTaskExecutor.execute(backgroundTask);
	}

	@Override
	public BackgroundTaskStatusMessageTranslator
		getBackgroundTaskStatusMessageTranslator() {

		return _backgroundTaskExecutor.
			getBackgroundTaskStatusMessageTranslator();
	}

	@Override
	public String handleException(BackgroundTask backgroundTask, Exception e) {
		return _backgroundTaskExecutor.handleException(backgroundTask, e);
	}

	@Override
	public boolean isSerial() {
		return _backgroundTaskExecutor.isSerial();
	}

	protected BackgroundTaskExecutor getBackgroundTaskExecutor() {
		return _backgroundTaskExecutor;
	}

	private BackgroundTaskExecutor _backgroundTaskExecutor;

}