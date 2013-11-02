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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Shuyang Zhou
 */
public class ClearTimerThreadUtil {

	public static void clearTimerThread() throws Exception {
		if (!_initialized) {
			return;
		}

		Thread[] threads = ThreadUtil.getThreads();

		for (Thread thread : threads) {
			if (thread == null) {
				continue;
			}

			Class<?> threadClass = thread.getClass();

			String threadClassName = threadClass.getName();

			if (!threadClassName.equals("java.util.TimerThread")) {
				continue;
			}

			Object queue = _queueField.get(thread);

			synchronized (queue) {
				_newTasksMayBeScheduledField.setBoolean(thread, false);

				_clearMethod.invoke(queue);

				queue.notify();
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ClearTimerThreadUtil.class);

	private static Method _clearMethod;
	private static boolean _initialized;
	private static Field _newTasksMayBeScheduledField;
	private static Field _queueField;

	static {
		try {
			Class<?> timeThreadClass = Class.forName("java.util.TimerThread");

			_newTasksMayBeScheduledField = ReflectionUtil.getDeclaredField(
				timeThreadClass, "newTasksMayBeScheduled");
			_queueField = ReflectionUtil.getDeclaredField(
				timeThreadClass, "queue");

			Class<?> taskQueueClass = Class.forName("java.util.TaskQueue");

			_clearMethod = ReflectionUtil.getDeclaredMethod(
				taskQueueClass, "clear");

			_initialized = true;
		}
		catch (Throwable t) {
			_initialized = false;

			if (_log.isWarnEnabled()) {
				_log.warn("Failed to initialize ClearTimerThreadUtil");
			}
		}
	}

}