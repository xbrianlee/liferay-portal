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

package com.liferay.portal.cache.ehcache;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ThreadUtil;

/**
 * @author Shuyang Zhou
 */
public class ClearEhcacheThreadUtil {

	public static void clearEhcacheReplicationThread()
		throws InterruptedException {

		Thread[] threads = ThreadUtil.getThreads();

		for (Thread thread : threads) {
			if (thread == null) {
				continue;
			}

			String name = thread.getName();

			if (name.equals(_THREAD_NAME)) {
				thread.interrupt();

				thread.join(_WAIT_TIME);

				if (thread.isAlive() && _log.isWarnEnabled()) {
					_log.warn(
						"Give up waiting on thread " + thread +
							" after waiting for " + _WAIT_TIME + "ms");
				}
			}
		}
	}

	private static final String _THREAD_NAME = "Replication Thread";

	private static final long _WAIT_TIME = 1000;

	private static Log _log = LogFactoryUtil.getLog(
		ClearEhcacheThreadUtil.class);

}