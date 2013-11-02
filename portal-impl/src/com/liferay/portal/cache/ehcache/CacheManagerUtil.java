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

import com.liferay.portal.kernel.util.JavaDetector;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.util.PropsValues;

import java.lang.reflect.Field;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;

/**
 * @author Shuyang Zhou
 */
public class CacheManagerUtil {

	public static CacheManager createCacheManager(Configuration configuration) {
		CacheManager cacheManager = new CacheManager(configuration);

		try {
			ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
				(ScheduledThreadPoolExecutor)_statisticsExecutorField.get(
					cacheManager);

			BlockingQueue<Runnable> blockingQueue = null;

			// This odd logic is a workaround for
			// http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6522773

			if (JavaDetector.isJDK6()) {
				blockingQueue = (BlockingQueue<Runnable>)_workQueueField.get(
					scheduledThreadPoolExecutor);;

				_workQueueField.set(
					scheduledThreadPoolExecutor,
					new DelayQueue<RunnableScheduledFuture<?>>() {

						@Override
						public int remainingCapacity() {
							return 0;
						}

					});
			}

			scheduledThreadPoolExecutor.setCorePoolSize(
				PropsValues.EHCACHE_CACHE_MANAGER_STATISTICS_THREAD_POOL_SIZE);

			if (JavaDetector.isJDK6()) {
				while (
					scheduledThreadPoolExecutor.getPoolSize() >
						PropsValues.
							EHCACHE_CACHE_MANAGER_STATISTICS_THREAD_POOL_SIZE);

				_workQueueField.set(scheduledThreadPoolExecutor, blockingQueue);
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

		return cacheManager;
	}

	private static Field _statisticsExecutorField;
	private static Field _workQueueField;

	static {
		try {
			_statisticsExecutorField = ReflectionUtil.getDeclaredField(
				CacheManager.class, "statisticsExecutor");

			if (JavaDetector.isJDK6()) {
				_workQueueField = ReflectionUtil.getDeclaredField(
					ThreadPoolExecutor.class, "workQueue");
			}
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

}