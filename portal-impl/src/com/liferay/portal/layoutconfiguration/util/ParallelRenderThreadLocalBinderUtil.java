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

package com.liferay.portal.layoutconfiguration.util;

import com.liferay.portal.kernel.util.ThreadLocalBinder;

/**
 * @author Shuyang Zhou
 */
public class ParallelRenderThreadLocalBinderUtil {

	public static void bind() {
		_threadLocalBinder.bind();
	}

	public static void cleanUp() {
		_threadLocalBinder.cleanUp();
	}

	public static ThreadLocalBinder getThreadLocalBinder() {
		return _threadLocalBinder;
	}

	public static void record() {
		_threadLocalBinder.record();
	}

	public static void setThreadLocalBinder(
		ThreadLocalBinder threadLocalBinder) {

		_threadLocalBinder = threadLocalBinder;
	}

	private static ThreadLocalBinder _threadLocalBinder;

}