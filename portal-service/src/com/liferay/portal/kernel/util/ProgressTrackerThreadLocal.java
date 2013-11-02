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

package com.liferay.portal.kernel.util;

/**
 * @author Jorge Ferrer
 */
public class ProgressTrackerThreadLocal {

	public static ProgressTracker getProgressTracker() {
		return _progressTracker.get();
	}

	public static void setProgressTracker(ProgressTracker progressTracker) {
		_progressTracker.set(progressTracker);
	}

	private static ThreadLocal<ProgressTracker> _progressTracker =
		new AutoResetThreadLocal<ProgressTracker>(
			ProgressTrackerThreadLocal.class + "._progressTracker");

}