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

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class NamedThreadFactory implements ThreadFactory {

	public NamedThreadFactory(
		String name, int priority, ClassLoader contextClassLoader) {

		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager != null) {
			_group = securityManager.getThreadGroup();
		}
		else {
			Thread currentThread = Thread.currentThread();

			_group = currentThread.getThreadGroup();
		}

		_name = name;
		_priority = priority;
		_contextClassLoader = contextClassLoader;
	}

	@Override
	public Thread newThread(Runnable runnable) {
		Thread thread = new Thread(
			_group, runnable,
			_name.concat(StringPool.MINUS).concat(
				String.valueOf(_counter.incrementAndGet())));

		thread.setDaemon(true);
		thread.setPriority(_priority);

		if (_contextClassLoader != null) {
			thread.setContextClassLoader(_contextClassLoader);
		}

		return thread;
	}

	private ClassLoader _contextClassLoader;
	private AtomicInteger _counter = new AtomicInteger();
	private ThreadGroup _group;
	private String _name;
	private int _priority;

}