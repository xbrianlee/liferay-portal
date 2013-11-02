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

package com.liferay.util;

import com.liferay.portal.kernel.util.InitialThreadLocal;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-7864.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class ThirdPartyThreadLocalRegistry {

	public static void registerThreadLocal(ThreadLocal<?> threadLocal) {
		Set<ThreadLocal<?>> threadLocalSet = _threadLocalSet.get();

		threadLocalSet.add(threadLocal);
	}

	public static void resetThreadLocals() {
		Set<ThreadLocal<?>> threadLocalSet = _threadLocalSet.get();

		if (threadLocalSet == null) {
			return;
		}

		for (ThreadLocal<?> threadLocal : threadLocalSet) {
			threadLocal.remove();
		}
	}

	private static ThreadLocal<Set<ThreadLocal<?>>> _threadLocalSet =
		new InitialThreadLocal<Set<ThreadLocal<?>>>(
			ThirdPartyThreadLocalRegistry.class + "._threadLocalSet",
			new HashSet<ThreadLocal<?>>());

}