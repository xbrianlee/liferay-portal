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
public class ClassLoaderAwareBackgroundTaskExecutor
	extends DelegatingBackgroundTaskExecutor {

	public ClassLoaderAwareBackgroundTaskExecutor(
		BackgroundTaskExecutor backgroundTaskExecutor,
		ClassLoader classLoader) {

		super(backgroundTaskExecutor);

		_classLoader = classLoader;
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (_classLoader != contextClassLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			BackgroundTaskExecutor backgroundTaskExecutor =
				getBackgroundTaskExecutor();

			return backgroundTaskExecutor.execute(backgroundTask);
		}
		finally {
			if (_classLoader != contextClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	private ClassLoader _classLoader;

}